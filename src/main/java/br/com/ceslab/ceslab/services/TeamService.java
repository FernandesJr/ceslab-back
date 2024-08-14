package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.dto.StudentDTO;
import br.com.ceslab.ceslab.dto.TeamDTO;
import br.com.ceslab.ceslab.entities.Course;
import br.com.ceslab.ceslab.entities.Team;
import br.com.ceslab.ceslab.repositories.CourseRepository;
import br.com.ceslab.ceslab.repositories.TeamRepository;
import br.com.ceslab.ceslab.services.exceptions.DataBaseViolationException;
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
    private CourseRepository courseRepository;

    @Autowired
    private StudentService studentService;

    @Transactional(readOnly = true)
    public Page<TeamDTO> findAllByCourse(int pageNumber, Long idCourse){
        //first page is 0
        Pageable pageable = PageRequest.of(pageNumber, 6, Sort.Direction.DESC, "id");
        Page<Team> page = repository.findByCourse(new Course(idCourse), pageable);
        return page.map(p -> new TeamDTO(p));
    }

    @Transactional(readOnly = true)
    public TeamDTO findById(Long id){
        Team entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Class not found"));
        return new TeamDTO(entity);
    }

    @Transactional(readOnly = true)
    public TeamDTO findByName(String name){
        Team entity = repository.findByName(name.toUpperCase());
        if (entity == null) return null;
        return new TeamDTO(entity);
    }

    @Transactional
    public TeamDTO create(TeamDTO dto){

        //Verify if course exist
        Course course = courseRepository.findById(dto.getCourseDTO().getId())
                .orElseThrow(() -> new ResourceNotFound("Course not found with id " + dto.getCourseDTO().getId()));

        //Verify if name of team exist
        Team teamByName = repository.findByName(dto.getName());
        if (teamByName != null) throw new DataBaseViolationException("Name for Team already exist");

        Team team = new Team();
        team.setName(dto.getName().toUpperCase());
        team.setCompleted(false);
        team.setCourse(course);
        team.setStartDate(dto.getStartDate());
        team.setPriceRegistration(dto.getPriceRegistration());
        team.setQuantityMonths(dto.getQuantityMonths());
        team.setPriceMonthPayments(dto.getPriceMonthPayments());
        team.setFirstMonthPayment(dto.getFirstMonthPayment());
        Team entity = repository.save(team);
        return new TeamDTO(entity);
    }

    @Transactional
    public TeamDTO update(Long id, TeamDTO dto){

        Team entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Class not found"));

        if (!entity.getName().equals(dto.getName())) {
            // Name is unique so need to check
            Team entityByName = repository.findByName(dto.getName());
            if (entityByName != null && entityByName.getId() != id)
                throw new DataBaseViolationException("Name already in use");
        }
        entity.setName(dto.getName());
        entity.setCompleted(dto.isCompleted());
        entity.setEndDate(dto.getEndDate());
        return new TeamDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        Team entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Team not found"));
        if (!entity.getStudents().isEmpty()) throw new DataBaseViolationException("Team contain students");
        repository.delete(entity);
    }

    public List<StudentDTO> findStudentsByTeam(Long id){
        return this.studentService.findByTeam(new Team(id));
    }
}
