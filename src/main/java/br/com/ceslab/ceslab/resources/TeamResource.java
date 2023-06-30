package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.TeamDTO;
import br.com.ceslab.ceslab.services.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team")
public class TeamResource {

    @Autowired
    private TeamService service;

    @GetMapping
    public ResponseEntity<Page<TeamDTO>> findAll(Pageable pageable){
        Page<TeamDTO> page = service.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> findById(@PathVariable Long id){
        TeamDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> update(@PathVariable Long id, @RequestBody @Valid TeamDTO dto){
        TeamDTO teamDTO = service.update(id, dto);
        return ResponseEntity.ok(teamDTO);
    }
}
