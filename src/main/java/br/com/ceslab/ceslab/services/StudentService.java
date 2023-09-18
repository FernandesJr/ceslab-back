package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.dto.StudentDTO;
import br.com.ceslab.ceslab.entities.Student;
import br.com.ceslab.ceslab.entities.Team;
import br.com.ceslab.ceslab.repositories.StudentRepository;
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
    public StudentDTO update(StudentDTO dto) {
        Student entity = this.repository.getReferenceById(dto.getId());
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setPhone(dto.getPhone());
        entity.setDateBirth(dto.getDateBirth());
        return new StudentDTO(entity);
    }
}
