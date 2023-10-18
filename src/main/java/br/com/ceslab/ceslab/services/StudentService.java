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
