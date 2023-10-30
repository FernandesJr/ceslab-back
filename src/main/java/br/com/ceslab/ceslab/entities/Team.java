package br.com.ceslab.ceslab.entities;

import br.com.ceslab.ceslab.dto.TeamDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private boolean completed;
    private LocalDate startDate;
    private LocalDate firstMonthPayment;
    private Integer quantityMonths;
    private Double priceRegistration;
    private Double priceMonthPayments;

    @ManyToOne
    private Course course;

    @JsonIgnore
    @ManyToMany(mappedBy = "teams")
    private Set<Student> students = new HashSet<>();

    public Team(){}

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Team(Long id) {
        this.id = id;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getQuantityMonths() {
        return quantityMonths;
    }

    public void setQuantityMonths(Integer quantityMonths) {
        this.quantityMonths = quantityMonths;
    }

    public LocalDate getFirstMonthPayment() {
        return firstMonthPayment;
    }

    public void setFirstMonthPayment(LocalDate firstMonthPayment) {
        this.firstMonthPayment = firstMonthPayment;
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

    public Set<Student> getStudents() {
        return students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return getId().equals(team.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
