package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.DegreeDTO;
import br.com.ceslab.ceslab.dto.degree.DegreeCreateDTO;
import br.com.ceslab.ceslab.entities.Degree;
import br.com.ceslab.ceslab.services.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/degree")
public class DegreeResource {

    @Autowired
    private DegreeService service;

    @GetMapping("/{code}")
    public ResponseEntity<DegreeDTO> findByCode(@PathVariable String code) {
        DegreeDTO dto = service.findByCode(code);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/student/{idStudent}/team/{idTeam}")
    public ResponseEntity<DegreeDTO> findByStudentAndTeam(@PathVariable(name = "idStudent") Long studentId,
                                                          @PathVariable(name = "idTeam") Long teamId) {
        DegreeDTO dto = service.findByStudentAndTeam(studentId, teamId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DegreeDTO> create(@RequestBody DegreeCreateDTO dto) {
        DegreeDTO degreeDTO = service.create(dto);
        return ResponseEntity.ok(degreeDTO);
    }
}
