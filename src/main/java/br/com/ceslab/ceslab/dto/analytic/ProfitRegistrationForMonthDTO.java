package br.com.ceslab.ceslab.dto.analytic;

import java.io.Serializable;

public class ProfitRegistrationForMonthDTO extends ProfitByMonthGeneric implements Serializable {
    public ProfitRegistrationForMonthDTO(String name, Double value) {
        super(name, value);
    }
}
