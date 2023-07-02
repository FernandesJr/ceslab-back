package br.com.ceslab.ceslab.entities;

import br.com.ceslab.ceslab.dto.TeamDTO;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean completed;

    @ManyToOne
    private Course course;

    @ManyToMany(mappedBy = "teams")
    private Set<Student> students = new HashSet<>();

    public Team(){}

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Team(TeamDTO dto) {
        this.name = dto.getName();
        this.course = new Course(dto.getCourseDTO().getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
