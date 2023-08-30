package br.com.ceslab.ceslab.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_month_payment")
public class MonthPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;
    private Double discount;
    private Double received;
    private boolean paid;
    private LocalDate payday;

    @ManyToOne
    private Team team;

    //prevent recursive
    @JsonIgnore
    @ManyToOne
    private Student student;

    public MonthPayment(){}

    public MonthPayment(Long id, Double price, Double discount, boolean paid, LocalDate payday) {
        this.id = id;
        this.price = price;
        this.discount = discount;
        this.paid = paid;
        this.payday = payday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public LocalDate getPayday() {
        return payday;
    }

    public void setPayday(LocalDate payday) {
        this.payday = payday;
    }

    public Double getReceived() {
        return received;
    }

    public void setReceived(Double received) {
        this.received = received;
    }
}
