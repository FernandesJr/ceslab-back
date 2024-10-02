package br.com.ceslab.ceslab.dto.analytic;

import br.com.ceslab.ceslab.projections.AmountNameAndValue;

import java.util.ArrayList;
import java.util.List;

public class LineChart {

    private String name;
    private List<AmountNameAndValue> series = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<AmountNameAndValue> getSeries() {
        return series;
    }
}
