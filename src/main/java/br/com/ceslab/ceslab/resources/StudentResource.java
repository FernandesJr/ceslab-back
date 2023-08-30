package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.StudentDTO;
import br.com.ceslab.ceslab.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentResource {

    @Autowired
    private StudentService service;

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable Long id) {
        StudentDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }
}
