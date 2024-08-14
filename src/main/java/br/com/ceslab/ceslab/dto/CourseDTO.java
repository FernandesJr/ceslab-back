package br.com.ceslab.ceslab.dto;

import br.com.ceslab.ceslab.entities.Course;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class CourseDTO implements Serializable {

    private Long id;

    @NotBlank
    private String name;

    private Integer hours;

    public CourseDTO() {}

    public CourseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CourseDTO(Course entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.hours = entity.getHours();
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

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }
}
