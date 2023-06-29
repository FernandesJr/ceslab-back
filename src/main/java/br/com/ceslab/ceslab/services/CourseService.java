package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.dto.CourseDTO;
import br.com.ceslab.ceslab.entities.Course;
import br.com.ceslab.ceslab.repositories.CourseRepository;
import br.com.ceslab.ceslab.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    @Transactional(readOnly = true)
    public List<CourseDTO> findAll() {
        return repository.findAll().stream().map(c -> new CourseDTO(c)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CourseDTO findById(Long id){
        Course entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("course not exist"));
        return new CourseDTO(entity);
    }

    @Transactional
    public CourseDTO update(CourseDTO dto, Long id){
        Course entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("course not exist"));
        entity.setName(dto.getName());
        return new CourseDTO(entity);
    }
}
