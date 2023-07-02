package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.TeamDTO;
import br.com.ceslab.ceslab.services.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

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

    @PostMapping("/course/{idCourse}")
    public ResponseEntity<TeamDTO> create(@PathVariable Long idCourse,  @RequestBody @Valid TeamDTO dto) throws URISyntaxException {
        TeamDTO teamDTO = service.create(idCourse, dto);
        //Quando se cria um novo recurso deve-se devolver um status 201
        //E no head da response por convenção declara o location do recurso criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(teamDTO.getId()).toUri();
        //Ajuste de URI
        String uriString = uri.toString();
        int sizeUri = uriString.length();
        String uriAdjusted = uriString.substring(0, sizeUri - 10) + teamDTO.getId();
        URI uriNew = new URI(uriAdjusted);
        return ResponseEntity.created(uriNew).body(teamDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> update(@PathVariable Long id, @RequestBody @Valid TeamDTO dto){
        TeamDTO teamDTO = service.update(id, dto);
        return ResponseEntity.ok(teamDTO);
    }
}
