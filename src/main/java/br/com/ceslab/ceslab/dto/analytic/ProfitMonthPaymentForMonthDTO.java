package br.com.ceslab.ceslab.dto.analytic;

import java.io.Serializable;

public class ProfitMonthPaymentForMonthDTO extends ProfitByMonthGeneric implements Serializable {
    public ProfitMonthPaymentForMonthDTO(String name, Double value) {
        super(name, value);
    }
}
