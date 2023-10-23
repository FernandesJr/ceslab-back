package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.dto.StudentDTO;
import br.com.ceslab.ceslab.entities.MonthPayment;
import br.com.ceslab.ceslab.entities.Registration;
import br.com.ceslab.ceslab.entities.Student;
import br.com.ceslab.ceslab.entities.Team;
import br.com.ceslab.ceslab.repositories.MonthPaymentRepository;
import br.com.ceslab.ceslab.repositories.RegistrationRepository;
import br.com.ceslab.ceslab.repositories.StudentRepository;
import br.com.ceslab.ceslab.repositories.TeamRepository;
import br.com.ceslab.ceslab.services.exceptions.DataBaseViolationException;
import br.com.ceslab.ceslab.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private TeamRepository repositoryTeam;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private MonthPaymentRepository monthPaymentRepository;

    @Transactional(readOnly = true)
    public List<StudentDTO> findByTeam(Team team){
        List<Student> students = repository.findByTeams(team);
        return students.stream().map(student -> new StudentDTO(student)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StudentDTO findById(Long id) {
        Student entity = this.repository.findById(id).orElseThrow(() -> new ResourceNotFound("User not found"));
        return new StudentDTO(entity);
    }

    @Transactional(readOnly = true)
    public StudentDTO findByCpf(String cpf) {
        Student entity = this.repository.findByCpf(cpf);
        if (entity != null) return new StudentDTO(entity);
        return null;
    }

    @Transactional
    public StudentDTO update(StudentDTO dto, Long id) {

        Student entityByCpf = this.repository.findByCpf(dto.getCpf());
        if (entityByCpf != null && !entityByCpf.getId().equals(id))
            throw new DataBaseViolationException("CPF already in use");

        try {
            Student entity = this.repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity.setCpf(dto.getCpf());
            entity.setPhone(dto.getPhone());
            entity.setDateBirth(dto.getDateBirth());
            return new StudentDTO(entity);
        } catch (Exception e) {
           throw new ResourceNotFound("Student not found with id: " + id);
        }
    }

    @Transactional
    public Student create(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setCpf(dto.getCpf());
        student.setDateBirth(dto.getDateBirth());
        student.setPhone(dto.getPhone());
        return repository.save(student);
    }

    @Transactional
    public StudentDTO addStudentOfTeam(StudentDTO dto, Long teamId) {

        Team team = this.repositoryTeam.findById(teamId)
                .orElseThrow(() -> new ResourceNotFound("Team does not exist with id " + teamId));

        if (dto.getId() != null) {
            //student already exist
            Student student = this.repository.findById(dto.getId())
                    .orElseThrow(() -> new ResourceNotFound("Student not found with id " + dto.getId()));
            //verify if student already is on the team
            if (student.getTeams().contains(team))
                throw new DataBaseViolationException("Student already contain at the team");
            student.getTeams().add(team);
            //Create registration + monthPayments
            createRegistrationToStudent(student, team);
            createMonthPaymentsToStudent(student, team, team.getQuantityMonths());
            return new StudentDTO(student);
        }
        //new student
        Student student = this.create(dto);
        student.getTeams().add(team);
        Student entity = this.repository.save(student);

        //Create registration + monthPayments
        createRegistrationToStudent(entity, team);
        createMonthPaymentsToStudent(entity, team, team.getQuantityMonths());
        return new StudentDTO(entity);
    }

    private void createRegistrationToStudent(Student student, Team team) {
        Registration registration = new Registration();
        registration.setStudent(student);
        registration.setTeam(team);
        registration.setPrice(team.getPriceRegistration());
        registration.setReceived(0.0);
        registration.setDiscount(0.0);
        registration.setPaid(false);
        registration.setDueDate(team.getStartDate().minusDays(7));
        this.registrationRepository.save(registration);
    }

    private void createMonthPaymentsToStudent(Student student, Team team, Integer qt) {
        for (long i = 0; i < qt; i++) {
            MonthPayment monthPayment = new MonthPayment();
            monthPayment.setStudent(student);
            monthPayment.setTeam(team);
            monthPayment.setPrice(team.getPriceMonthPayments());
            monthPayment.setReceived(0.0);
            monthPayment.setDiscount(0.0);
            monthPayment.setPaid(false);
            monthPayment.setDueDate(team.getFirstMonthPayment().plusMonths(i));
            this.monthPaymentRepository.save(monthPayment);
        }
    }

    @Transactional
    public void removeStudentOfTeam(Long studentId, Long teamId) {
        //Validation entities
        Team team = repositoryTeam.findById(teamId)
                .orElseThrow(() -> new ResourceNotFound("Team not found with id " + teamId));
        Student student = repository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFound("Student not found with id " + studentId));

        if (student.getTeams().contains(team)) {
            student.getTeams().remove(team);

            //delete registrations and month payments of relationship
            Registration registration = this.registrationRepository.findByStudentAndTeam(student, team);
            this.registrationRepository.delete(registration);

            List<MonthPayment> months = this.monthPaymentRepository.findByStudentAndTeam(student, team);
            months.forEach(m -> this.monthPaymentRepository.delete(m));
        }

    }
}
