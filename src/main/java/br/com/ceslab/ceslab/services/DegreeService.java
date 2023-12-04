package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.dto.DegreeDTO;
import br.com.ceslab.ceslab.dto.degree.DegreeCreateDTO;
import br.com.ceslab.ceslab.entities.Degree;
import br.com.ceslab.ceslab.entities.Student;
import br.com.ceslab.ceslab.entities.Team;
import br.com.ceslab.ceslab.repositories.DegreeRepository;
import br.com.ceslab.ceslab.repositories.StudentRepository;
import br.com.ceslab.ceslab.repositories.TeamRepository;
import br.com.ceslab.ceslab.services.exceptions.DataBaseViolationException;
import br.com.ceslab.ceslab.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class DegreeService {

    @Autowired
    private DegreeRepository repository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public DegreeDTO findByCode(String code) {
        Degree entity = repository.findByCode(code);
        if (entity == null) throw new ResourceNotFound("Code invalid");
        return new DegreeDTO(entity);
    }

    @Transactional
    public DegreeDTO create(DegreeCreateDTO dto) {

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFound("Student not found with id: " + dto.getStudentId()));
        Team  team = teamRepository.findById(dto.getTeamId())
                .orElseThrow(() -> new ResourceNotFound("Team not found with id: " + dto.getTeamId()));

        isStudentDebit(student);

        if (!degreeAlreadyCreate(student, team)) {
            Degree degree = new Degree();
            degree.setStudent(student);
            degree.setTeam(team);
            degree.setCode(generationCode(team));
            degree.setGenerationDate(LocalDateTime.now());
            Degree entity = repository.save(degree);
            return new DegreeDTO(entity);
        }
        throw new DataBaseViolationException("Degree already create");
    }

    private void isStudentDebit(Student student) {
        student.getMonthPayments().forEach(monthPayment -> {
            if (!monthPayment.isPaid()) throw new DataBaseViolationException("Student with debit");;
        });
    }

    private boolean degreeAlreadyCreate(Student student, Team team) {
        Optional<Degree> entity = repository.findByStudentAndTeam(student, team);
        return entity.isPresent();
    }

    private String generationCode(Team team) {
        UUID uuid = UUID.randomUUID();
        String code = uuid.toString();

        LocalDateTime dateNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String date = dateNow.format(formatter);

        code = team.getName() + "-" + code + "-" + date;
        System.out.println("String aleatória CODE: " + code);
        return code;
    }

    @Transactional(readOnly = true)
    public DegreeDTO findByStudentAndTeam(Long studentId, Long teamId) {
        Optional<Degree> optional = repository.findByStudentAndTeam(new Student(studentId), new Team(teamId));
        return optional.map(d -> new DegreeDTO(d)).orElse(null);
    }
}
