package br.com.ceslab.ceslab.dto.registration;

import br.com.ceslab.ceslab.entities.Registration;

import java.io.Serializable;
import java.time.LocalDate;

public class RegistrationUpdate implements Serializable {

    private Long id;
    private Double price;
    private Double discount;
    private boolean paid;
    private LocalDate payday;
    private Double received;

    public RegistrationUpdate() {}

    public RegistrationUpdate(Long id, Double price, Double discount, boolean paid, LocalDate payday, Double received) {
        this.id = id;
        this.price = price;
        this.discount = discount;
        this.paid = paid;
        this.payday = payday;
        this.received = received;
    }

    public RegistrationUpdate(Registration entity) {
        this.id = entity.getId();
        this.price = entity.getPrice();
        this.discount = entity.getDiscount();
        this.paid = entity.isPaid();
        this.payday = entity.getPayday();
        this.received = entity.getReceived();
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
