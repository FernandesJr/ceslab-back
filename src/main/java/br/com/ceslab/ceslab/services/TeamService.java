package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.dto.StudentDTO;
import br.com.ceslab.ceslab.dto.TeamDTO;
import br.com.ceslab.ceslab.entities.Course;
import br.com.ceslab.ceslab.entities.Team;
import br.com.ceslab.ceslab.repositories.TeamRepository;
import br.com.ceslab.ceslab.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository repository;

    @Autowired
    private StudentService studentService;


    @Transactional(readOnly = true)
    public Page<TeamDTO> findAll(int pageNumber){
        //first page is 0
        Pageable pageable = PageRequest.of(pageNumber, 6, Sort.Direction.DESC, "id");
        Page<Team> page = repository.findAll(pageable);
        return page.map(p -> new TeamDTO(p));
    }

    @Transactional(readOnly = true)
    public TeamDTO findById(Long id){
        Team entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Class not found"));
        return new TeamDTO(entity);
    }

    @Transactional
    public TeamDTO create(TeamDTO dto){
        Team team = new Team();
        team.setName(dto.getName());
        team.setCompleted(false);
        team.setCourse(new Course(dto.getCourseDTO().getId()));
        Team entity = repository.save(team);
        return new TeamDTO(entity);
    }

    @Transactional
    public TeamDTO update(Long id, TeamDTO dto){
        Team entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Class not found"));
        entity.setName(dto.getName());
        entity.setCompleted(dto.isCompleted());
        return new TeamDTO(entity);
    }

    public List<StudentDTO> findStudentsByTeam(Long id){
        return this.studentService.findByTeam(new Team(id));
    }
}
