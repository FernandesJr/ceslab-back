package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.DegreeDTO;
import br.com.ceslab.ceslab.services.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
