package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.dto.TeamDTO;
import br.com.ceslab.ceslab.entities.Team;
import br.com.ceslab.ceslab.repositories.TeamRepository;
import br.com.ceslab.ceslab.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository repository;


    @Transactional(readOnly = true)
    public Page<TeamDTO> findAll(Pageable pageable){
        Page<Team> page = repository.findAll(pageable);
        return page.map(p -> new TeamDTO(p));
    }

    @Transactional(readOnly = true)
    public TeamDTO findById(Long id){
        Team entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Class not found"));
        return new TeamDTO(entity);
    }

    @Transactional
    public TeamDTO update(Long id, TeamDTO dto){
        Team entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Class not found"));
        entity.setName(dto.getName());
        entity.setCompleted(dto.isCompleted());
        return new TeamDTO(entity);
    }
}
