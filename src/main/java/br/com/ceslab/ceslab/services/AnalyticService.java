package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.dto.analytic.ExpenseByYearDTO;
import br.com.ceslab.ceslab.dto.analytic.ProfitByMonthGeneric;
import br.com.ceslab.ceslab.dto.analytic.ProfitMonthPaymentForMonthDTO;
import br.com.ceslab.ceslab.dto.analytic.ProfitRegistrationForMonthDTO;
import br.com.ceslab.ceslab.enums.MonthsPtBr;
import br.com.ceslab.ceslab.projections.ProfitMonthGenericProjection;
import br.com.ceslab.ceslab.projections.ProfitMonthPaymentYeahProjection;
import br.com.ceslab.ceslab.projections.ProfitRegistrationYearProjection;
import br.com.ceslab.ceslab.repositories.ExpenseRepository;
import br.com.ceslab.ceslab.repositories.MonthPaymentRepository;
import br.com.ceslab.ceslab.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalyticService {

    @Autowired
    private MonthPaymentRepository monthPaymentRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Transactional(readOnly = true)
    public ExpenseByYearDTO getExpenseValueByYear() {
        return new ExpenseByYearDTO(expenseRepository.findValueByOneYearAgo());
    }

    @Transactional(readOnly = true)
    public List<ProfitMonthPaymentYeahProjection> monthPaymentsInOneYeah() {
        return monthPaymentRepository.findByOneYeahAgo();
    }

    @Transactional(readOnly = true)
    public List<ProfitRegistrationYearProjection> registrationInOneYeah() {
        return registrationRepository.findByOneYearAgo();
    }

    public List<ProfitMonthPaymentForMonthDTO> getProfitMonthPaymentByMonth() {
        return (List<ProfitMonthPaymentForMonthDTO>)
                this.groupReceivedByMonth("MONTHPAYMENT")
                        .stream()
                        .map(r -> new ProfitMonthPaymentForMonthDTO(r.getName(), r.getValue()))
                        .collect(Collectors.toList());
    }

    public List<ProfitRegistrationForMonthDTO> getProfitRegistrationByMonth() {
        return (List<ProfitRegistrationForMonthDTO>)
                this.groupReceivedByMonth("REGISTRATION")
                        .stream()
                        .map(r -> new ProfitRegistrationForMonthDTO(r.getName(), r.getValue()))
                        .collect(Collectors.toList());
    }

    private List<ProfitByMonthGeneric> createAllMonthsOfYeah() {
        List<ProfitByMonthGeneric> profitGenericsForMonths = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            profitGenericsForMonths.add(new ProfitByMonthGeneric(MonthsPtBr.of(LocalDate.now().plusMonths(i+1).getMonth().getValue()).name(),0.0));
        }
        return profitGenericsForMonths;
    }

    private List<ProfitByMonthGeneric> groupReceivedByMonth(String filter) {
        final List<ProfitMonthGenericProjection> list = new ArrayList<>();

        if (filter.equals("REGISTRATION")) list.addAll(registrationInOneYeah());
        else list.addAll(monthPaymentsInOneYeah());

        List<ProfitByMonthGeneric> dto = this.createAllMonthsOfYeah();

        list.forEach(m -> {
            switch (m.getDuedate().getMonth()) {
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
