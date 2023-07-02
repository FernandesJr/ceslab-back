package br.com.ceslab.ceslab.dto;

import br.com.ceslab.ceslab.entities.Team;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TeamDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private boolean completed;

    private CourseDTO courseDTO;

    public TeamDTO(){}

    public TeamDTO(Long id, String name, boolean completed) {
        this.id = id;
        this.name = name;
        this.completed = completed;
    }

    public TeamDTO(Team entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.completed = entity.isCompleted();
        this.courseDTO = new CourseDTO(entity.getCourse());
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

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }
}
