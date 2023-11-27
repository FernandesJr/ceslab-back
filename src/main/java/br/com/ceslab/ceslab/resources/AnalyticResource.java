package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.analytic.ProfitMonthPaymentForMonth;
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

    @GetMapping("/monthPayment/yeah")
    private ResponseEntity<List<ProfitMonthPaymentForMonth>> monthPaymentForYeah() {
        List<ProfitMonthPaymentForMonth> dto = service.getProfitMonthPaymentByMonth();
        return ResponseEntity.ok(dto);
    }
}
