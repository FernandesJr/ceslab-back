package br.com.ceslab.ceslab.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private LocalDate dateBirth;
    private String cpf;

    @ManyToMany
    @JoinTable(
            name = "tb_team_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private List<Team> teams = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    private List<MonthPayment> monthPayments = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    private List<Registration> registrations;

    public Student() {}

    public Student(Long id) {
        this.id = id;
    }

    public Student(Long id, String name, String phone, LocalDate dateBirth, String cpf) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.dateBirth = dateBirth;
        this.cpf = cpf;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<MonthPayment> getMonthPayments() {
        return monthPayments;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }
}
