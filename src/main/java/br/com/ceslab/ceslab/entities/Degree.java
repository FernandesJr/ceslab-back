package br.com.ceslab.ceslab.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_degree")
public class Degree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private LocalDate generationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Team team;

    @ManyToOne(fetch = FetchType.EAGER)
    private Student student;

    public Degree(){}

    public Degree(Long id, String code, Team team, Student student) {
        this.id = id;
        this.code = code;
        this.team = team;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
