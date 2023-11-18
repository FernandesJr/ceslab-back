package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.paymentVoucher.PaymentVoucherMonthPaymentDTO;
import br.com.ceslab.ceslab.dto.paymentVoucher.PaymentVoucherRegistrationDTO;
import br.com.ceslab.ceslab.entities.PaymentVoucher;
import br.com.ceslab.ceslab.services.JasperService;
import br.com.ceslab.ceslab.services.PaymentVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paymentVoucher")
public class PaymentVoucherResource {

    @Autowired
    private PaymentVoucherService service;

    @Autowired
    private JasperService jasperService;

    private static final boolean SEND_SMS_TO_STUDENT = true;

    @PostMapping("/registration")
    public ResponseEntity<PaymentVoucher> createForRegistration(@RequestBody PaymentVoucherRegistrationDTO registration) {
        PaymentVoucher dto = this.service.createForRegistration(registration.registrationId(), SEND_SMS_TO_STUDENT);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/monthPayment")
    public ResponseEntity<PaymentVoucher> createForMonthPayment(@RequestBody PaymentVoucherMonthPaymentDTO monthPayment) {
        PaymentVoucher dto = this.service.createForMonthPayment(monthPayment.monthPaymentId(), SEND_SMS_TO_STUDENT);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/registration/{registrationId}/pdf")
    public ResponseEntity<?> createPdfForRegistration(@PathVariable Long registrationId) {
        byte[] bytes = this.service.createPdfByRegistration(registrationId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_PDF)
                .header("Content-disposition", "inline; filename=payment-voucher.pdf")
                //.header("Content-disposition", "attachment; filename=payment-voucher.pdf") to download
                .body(bytes);
    }

    @GetMapping("/monthPayment/{monthPaymentId}/pdf")
    public ResponseEntity<?> createPdfForMonthPayment(@PathVariable Long monthPaymentId) {
        byte[] bytes = this.service.createPdfByMonthPayment(monthPaymentId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_PDF)
                .header("Content-disposition", "inline; filename=payment-voucher.pdf")
                // .header("Content-disposition", "attachment; filename=payment-voucher.pdf") //to download
                .body(bytes);
    }
}
