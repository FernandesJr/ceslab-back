package br.com.ceslab.ceslab.projections;

import java.time.LocalDate;

public interface ProfitMonthPaymentYeahProjection {
    LocalDate getDuedate();
    Double getReceived();
}
