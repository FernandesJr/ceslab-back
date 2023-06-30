package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.CourseDTO;
import br.com.ceslab.ceslab.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseResource {

    @Autowired
    private CourseService service;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> findAll(){
        List<CourseDTO> dtos = service.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> findAll(@PathVariable Long id){
        CourseDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@PathVariable Long id, @RequestBody @Valid CourseDTO dto) {
        CourseDTO courseDTO = service.update(dto, id);
        return ResponseEntity.ok(courseDTO);
    }
}
