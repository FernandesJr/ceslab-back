package br.com.ceslab.ceslab.dto.monthPayment;

import br.com.ceslab.ceslab.entities.MonthPayment;

import java.io.Serializable;
import java.time.LocalDate;

public class MonthPaymentUpdateDto implements Serializable {

    private Long id;
    private Double price;
    private Double discount;
    private Double received;
    private boolean paid;
    private LocalDate payday;

    public MonthPaymentUpdateDto() {}

    public MonthPaymentUpdateDto(Long id, Double price, Double discount, Double received, boolean paid, LocalDate payday) {
        this.id = id;
        this.price = price;
        this.discount = discount;
        this.received = received;
        this.paid = paid;
        this.payday = payday;
    }

    public MonthPaymentUpdateDto(MonthPayment entity) {
        this.id = entity.getId();
        this.price = entity.getPrice();
        this.discount = entity.getDiscount();
        this.received = entity.getReceived();
        this.paid = entity.isPaid();
        this.payday = entity.getPayday();
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

    public Double getReceived() {
        return received;
    }

    public void setReceived(Double received) {
        this.received = received;
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
}
