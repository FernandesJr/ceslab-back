package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.analytic.ExpenseByYearDTO;
import br.com.ceslab.ceslab.dto.analytic.LineChart;
import br.com.ceslab.ceslab.dto.analytic.ProfitMonthPaymentForMonthDTO;
import br.com.ceslab.ceslab.dto.analytic.ProfitRegistrationForMonthDTO;
import br.com.ceslab.ceslab.services.AnalyticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/analytic")
public class AnalyticResource {

    @Autowired
    private AnalyticService service;

    @GetMapping("/monthPayment/year")
    private ResponseEntity<List<ProfitMonthPaymentForMonthDTO>> monthPaymentForYeah() {
        List<ProfitMonthPaymentForMonthDTO> dto = service.getProfitMonthPaymentByMonth();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/registration/year")
    private ResponseEntity<List<ProfitRegistrationForMonthDTO>> registrationForYeah() {
        List<ProfitRegistrationForMonthDTO> dto = service.getProfitRegistrationByMonth();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/expense/year")
    private ResponseEntity<ExpenseByYearDTO> expenseForYeah() {
        ExpenseByYearDTO dto = service.getExpenseValueByYear();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/chart/line")
    private ResponseEntity<List<LineChart>> getLinesChart() {
        List<LineChart> linesDTO = service.getLinesChart();
        return ResponseEntity.ok(linesDTO);
    }
}
