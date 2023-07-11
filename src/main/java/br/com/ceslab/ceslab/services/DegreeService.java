package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.dto.DegreeDTO;
import br.com.ceslab.ceslab.dto.TeamDTO;
import br.com.ceslab.ceslab.entities.Degree;
import br.com.ceslab.ceslab.entities.Student;
import br.com.ceslab.ceslab.entities.Team;
import br.com.ceslab.ceslab.repositories.DegreeRepository;
import br.com.ceslab.ceslab.repositories.StudentRepository;
import br.com.ceslab.ceslab.repositories.TeamRepository;
import br.com.ceslab.ceslab.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public DegreeDTO create(DegreeDTO dto) {
        Degree degree = new Degree();
        degree.setStudent(new Student(dto.getStudentDTO().getId()));
        degree.setTeam(teamRepository.getReferenceById(dto.getTeamDTO().getId()));
        degree.setCode(generationCode(dto));
        degree.setGenerationDate(LocalDateTime.now());
        Degree entity = repository.save(degree);
        return new DegreeDTO(entity);
    }

    private String generationCode(DegreeDTO dto) {
        UUID uuid = UUID.randomUUID();
        String code = uuid.toString();

        Team team = teamRepository.getReferenceById(dto.getTeamDTO().getId());

        LocalDateTime dateNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String date = dateNow.format(formatter);

        code = team.getName() + "-" + code + "-" + date;
        System.out.println("String aleatória CODE: " + code);
        return code;
    }

}
