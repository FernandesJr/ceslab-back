package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.monthPayment.MonthPaymentUpdateDto;
import br.com.ceslab.ceslab.services.MonthPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/monthsPayment")
public class MonthPaymentResource {

    @Autowired
    private MonthPaymentService service;

    @PutMapping("/{id}")
    public ResponseEntity<MonthPaymentUpdateDto> update(
            @RequestBody MonthPaymentUpdateDto dto,
            @PathVariable Long id) {
        MonthPaymentUpdateDto MonthPaymentUpdateDto = this.service.update(dto, id);
        return ResponseEntity.ok(MonthPaymentUpdateDto);
    }
}
