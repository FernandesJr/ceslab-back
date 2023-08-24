package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.StudentDTO;
import br.com.ceslab.ceslab.dto.TeamDTO;
import br.com.ceslab.ceslab.services.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamResource {

    @Autowired
    private TeamService service;

    @GetMapping
    public ResponseEntity<Page<TeamDTO>> findAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int pageNumber){
        Page<TeamDTO> page = service.findAll(pageNumber);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> findById(@PathVariable Long id){
        TeamDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping()
    public ResponseEntity<TeamDTO> create(@RequestBody @Valid TeamDTO dto) {
        TeamDTO teamDTO = service.create(dto);
        //Quando se cria um novo recurso deve-se devolver um status 201
        //E no head da response por convenção declara o location do recurso criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(teamDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(teamDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> update(@PathVariable Long id, @RequestBody @Valid TeamDTO dto){
        TeamDTO teamDTO = service.update(id, dto);
        return ResponseEntity.ok(teamDTO);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentDTO>> findStudentsByTeam(@PathVariable Long id){
        List<StudentDTO> dtos = service.findStudentsByTeam(id);
        return ResponseEntity.ok(dtos);
    }
}
