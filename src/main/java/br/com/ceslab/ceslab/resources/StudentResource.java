package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.StudentDTO;
import br.com.ceslab.ceslab.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(
            @Valid @RequestBody StudentDTO dto, @PathVariable Long id) {
        StudentDTO studentDTO = this.service.update(dto, id);
        return ResponseEntity.ok(studentDTO);
    }

    @DeleteMapping("/{id}/team/{teamId}")
    public ResponseEntity<Void> removeStudentTeam(@PathVariable Long id, @PathVariable Long teamId) {
        this.service.removeStudentOfTeam(id, teamId);
        return ResponseEntity.noContent().build();
    }
}
