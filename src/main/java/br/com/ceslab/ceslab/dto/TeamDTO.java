package br.com.ceslab.ceslab.dto;

import br.com.ceslab.ceslab.entities.Team;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

public class TeamDTO implements Serializable {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private boolean completed;

    private LocalDate startDate;
    private LocalDate firstMonthPayment;
    private Integer quantityMonths;
    private Double priceRegistration;
    private Double priceMonthPayments;

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
        this.startDate = entity.getStartDate();
        this.firstMonthPayment = entity.getFirstMonthPayment();
        this.quantityMonths = entity.getQuantityMonths();
        this.priceMonthPayments = entity.getPriceMonthPayments();
        this.priceRegistration = entity.getPriceRegistration();
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFirstMonthPayment() {
        return firstMonthPayment;
    }

    public void setFirstMonthPayment(LocalDate firstMonthPayment) {
        this.firstMonthPayment = firstMonthPayment;
    }

    public Integer getQuantityMonths() {
        return quantityMonths;
    }

    public void setQuantityMonths(Integer quantityMonths) {
        this.quantityMonths = quantityMonths;
    }

    public Double getPriceRegistration() {
        return priceRegistration;
    }

    public void setPriceRegistration(Double priceRegistration) {
        this.priceRegistration = priceRegistration;
    }

    public Double getPriceMonthPayments() {
        return priceMonthPayments;
    }

    public void setPriceMonthPayments(Double priceMonthPayments) {
        this.priceMonthPayments = priceMonthPayments;
    }
}
