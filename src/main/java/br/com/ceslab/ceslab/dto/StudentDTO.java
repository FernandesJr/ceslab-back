package br.com.ceslab.ceslab.dto;

import br.com.ceslab.ceslab.entities.MonthPayment;
import br.com.ceslab.ceslab.entities.Registration;
import br.com.ceslab.ceslab.entities.Student;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDTO implements Serializable{

    private Long id;
    private String name;
    private String phone;
    private LocalDate dateBirth;
    private String cpf;

    private List<MonthPayment> monthPayments = new ArrayList<>();
    private List<Registration> registrations = new ArrayList<>();

    public StudentDTO(){}

    public StudentDTO(Long id, String name, String phone, LocalDate dateBirth, String cpf) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.dateBirth = dateBirth;
        this.cpf = cpf;
    }

    public StudentDTO(Student entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.phone = entity.getPhone();
        this.dateBirth = entity.getDateBirth();
        this.cpf = entity.getCpf();
        this.addMonthPayment(entity.getMonthPayments());
        this.addRegistration(entity.getRegistrations());
    }

    private void addMonthPayment(List<MonthPayment> monthPayments) {
        if (monthPayments != null)
            monthPayments.forEach(monthPayment -> this.monthPayments.add(monthPayment));
    }

    private void addRegistration(List<Registration> registrations) {
        if (registrations != null)
        registrations.forEach(registration -> this.registrations.add(registration));
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

    public List<MonthPayment> getMonthPayments() {
        return monthPayments;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setMonthPayments(List<MonthPayment> monthPayments) {
        this.monthPayments = monthPayments;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }
}
