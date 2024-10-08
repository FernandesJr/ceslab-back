package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.analytic.*;
import br.com.ceslab.ceslab.services.AnalyticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/chart/line")
    private ResponseEntity<List<LineChart>> getLinesChart(@RequestBody DatesChartLineDTO dto) {
        List<LineChart> linesDTO = service.getLinesChart(dto);
        return ResponseEntity.ok(linesDTO);
    }
}
