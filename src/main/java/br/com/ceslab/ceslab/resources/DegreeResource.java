package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.DegreeDTO;
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

    @PostMapping
    public ResponseEntity<DegreeDTO> create(@RequestBody DegreeDTO dto) {
        DegreeDTO degreeDTO = service.create(dto);
        return ResponseEntity.ok(degreeDTO);
    }
}
