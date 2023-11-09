package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.paymentVoucher.PaymentVoucherMonthPaymentDTO;
import br.com.ceslab.ceslab.dto.paymentVoucher.PaymentVoucherRegistrationDTO;
import br.com.ceslab.ceslab.entities.PaymentVoucher;
import br.com.ceslab.ceslab.services.PaymentVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/paymentVoucher")
public class PaymentVoucherResource {

    @Autowired
    private PaymentVoucherService service;

    @PostMapping("/registration")
    public ResponseEntity<PaymentVoucher> createForRegistration(@RequestBody PaymentVoucherRegistrationDTO registration) {
        PaymentVoucher dto = this.service.createForRegistration(registration.registrationId());
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/monthPayment")
    public ResponseEntity<PaymentVoucher> createForMonthPayment(@RequestBody PaymentVoucherMonthPaymentDTO monthPayment) {
        PaymentVoucher dto = this.service.createForMonthPayment(monthPayment.monthPaymentId());
        return ResponseEntity.ok(dto);
    }
}
