package br.com.ceslab.ceslab.projections;

import java.time.LocalDate;

public interface ProfitMonthGenericProjection {
    LocalDate getDuedate();
    Double getReceived();
}
