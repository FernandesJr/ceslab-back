package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.dto.analytic.ProfitMonthPaymentForMonth;
import br.com.ceslab.ceslab.enums.MonthsPtBr;
import br.com.ceslab.ceslab.projections.ProfitMonthPaymentYeahProjection;
import br.com.ceslab.ceslab.repositories.MonthPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticService {

    @Autowired
    private MonthPaymentRepository monthPaymentRepository;

    @Transactional
    public List<ProfitMonthPaymentYeahProjection> monthPaymentsInOneYeah() {
        return monthPaymentRepository.findByOneYeahAgo();
    }

    public List<ProfitMonthPaymentForMonth> getProfitMonthPaymentByMonth() {
        return this.groupMonthPaymentsByMonth();
    }

    private List<ProfitMonthPaymentForMonth> createAllMonthsOfYeah() {
        List<ProfitMonthPaymentForMonth> profitMonthPaymentForMonths = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            profitMonthPaymentForMonths.add(new ProfitMonthPaymentForMonth(MonthsPtBr.of(LocalDate.now().plusMonths(i+1).getMonth().getValue()).name(),0.0));
        }
        return profitMonthPaymentForMonths;
    }

    private List<ProfitMonthPaymentForMonth> groupMonthPaymentsByMonth() {
        List<ProfitMonthPaymentYeahProjection> monthPayments = this.monthPaymentsInOneYeah();
        List<ProfitMonthPaymentForMonth> dto = this.createAllMonthsOfYeah();

        monthPayments.forEach(m -> {
            switch (m.getPayday().getMonth()) {
                case JANUARY -> dto.forEach(pm -> {if (pm.getName().equals("JANEIRO")) pm.setValue(pm.getValue() + m.getReceived());});
                case FEBRUARY -> dto.forEach(pm -> {if (pm.getName().equals("FEVEREIRO")) pm.setValue(pm.getValue() + m.getReceived());});
                case MARCH -> dto.forEach(pm -> {if (pm.getName().equals("MARÇO")) pm.setValue(pm.getValue() + m.getReceived());});
                case APRIL -> dto.forEach(pm -> {if (pm.getName().equals("ABRIL")) pm.setValue(pm.getValue() + m.getReceived());});
                case MAY -> dto.forEach(pm -> {if (pm.getName().equals("MAIO")) pm.setValue(pm.getValue() + m.getReceived());});
                case JUNE -> dto.forEach(pm -> {if (pm.getName().equals("JUNHO")) pm.setValue(pm.getValue() + m.getReceived());});
                case JULY -> dto.forEach(pm -> {if (pm.getName().equals("JULHO")) pm.setValue(pm.getValue() + m.getReceived());});
                case AUGUST -> dto.forEach(pm -> {if (pm.getName().equals("AGOSTO")) pm.setValue(pm.getValue() + m.getReceived());});
                case SEPTEMBER -> dto.forEach(pm -> {if (pm.getName().equals("SETEMBRO")) pm.setValue(pm.getValue() + m.getReceived());});
                case OCTOBER -> dto.forEach(pm -> {if (pm.getName().equals("OUTUBRO")) pm.setValue(pm.getValue() + m.getReceived());});
                case NOVEMBER -> dto.forEach(pm -> {if (pm.getName().equals("NOVEMBRO")) pm.setValue(pm.getValue() + m.getReceived());});
                case DECEMBER -> dto.forEach(pm -> {if (pm.getName().equals("DEZEMBRO")) pm.setValue(pm.getValue() + m.getReceived());});
            }
        });
        return dto;
    }
}
