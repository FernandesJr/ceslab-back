package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.dto.StudentDTO;
import br.com.ceslab.ceslab.entities.Student;
import br.com.ceslab.ceslab.entities.Team;
import br.com.ceslab.ceslab.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Transactional(readOnly = true)
    public List<StudentDTO> findByTeam(Team team){
        List<Student> students = repository.findByTeams(team);
        return students.stream().map(student -> new StudentDTO(student)).collect(Collectors.toList());
    }
}
