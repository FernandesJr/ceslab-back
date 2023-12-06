package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.entities.MonthPayment;
import br.com.ceslab.ceslab.entities.PaymentVoucher;
import br.com.ceslab.ceslab.entities.Registration;
import br.com.ceslab.ceslab.repositories.MonthPaymentRepository;
import br.com.ceslab.ceslab.repositories.PaymentVoucherRepository;
import br.com.ceslab.ceslab.repositories.RegistrationRepository;
import br.com.ceslab.ceslab.services.exceptions.ResourceNotFound;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class PaymentVoucherService {

    @Autowired
    private Environment environment;

    @Value("${twilio.sms.account.sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.sms.auth.token}")
    private String AUTH_TOKEN;

    @Value("${twilio.phone.number}")
    private String MY_NUMBER_PHONE;

    @Autowired
    private PaymentVoucherRepository repository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private MonthPaymentRepository monthPaymentRepository;

    @Autowired
    private JasperService jasperService;

    @Transactional(readOnly = true)
    public PaymentVoucher findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Payment Voucher not found with id " + id));
    }

    @Transactional
    public PaymentVoucher createForRegistration(Long registrationId, boolean... sendSms) {
        Registration registration = this.registrationRepository.findById(registrationId)
                .orElseThrow(() -> new ResourceNotFound("Registration not found with id " + registrationId));

        PaymentVoucher paymentVoucher = new PaymentVoucher();
        paymentVoucher.setRegistrationId(registration.getId());
        paymentVoucher.setStudent(registration.getStudent());
        paymentVoucher.setTeam(registration.getTeam());
        paymentVoucher.setPrice(registration.getPrice());
        paymentVoucher.setDiscount(registration.getDiscount());
        paymentVoucher.setReceived(registration.getReceived());
        paymentVoucher.setGenerationDate(LocalDateTime.now());
        PaymentVoucher entity = repository.save(paymentVoucher);
        if (sendSms.length > 0 && sendSms[0]) this.sendSms(entity, null);
        return entity;
    }

    @Transactional
    public PaymentVoucher createForMonthPayment(Long monthPaymentId, boolean... sendSms) {
        MonthPayment monthPayment = this.monthPaymentRepository.findById(monthPaymentId)
                .orElseThrow(() -> new ResourceNotFound("Month Payment not found with id " + monthPaymentId));

        PaymentVoucher paymentVoucher = new PaymentVoucher();
        paymentVoucher.setMonthPaymentId(monthPayment.getId());
        paymentVoucher.setStudent(monthPayment.getStudent());
        paymentVoucher.setTeam(monthPayment.getTeam());
        paymentVoucher.setPrice(monthPayment.getPrice());
        paymentVoucher.setDiscount(monthPayment.getDiscount());
        paymentVoucher.setReceived(monthPayment.getReceived());
        paymentVoucher.setGenerationDate(LocalDateTime.now());
        PaymentVoucher entity = repository.save(paymentVoucher);
        if (sendSms.length > 0 && sendSms[0]) this.sendSms(entity, monthPayment);
        return entity;
    }

    private void sendSms(PaymentVoucher paymentVoucher, MonthPayment monthPayment) {

        if (!this.isModeTest()) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            String bodyText =
                    "Olá, " + paymentVoucher.getStudent().getName() +
                    " aqui está seu COMPROVANTE DE PAGAMENTO CESLAB \n" +
                    this.createDescription(paymentVoucher, monthPayment) +
                    "Valor total: R$ " + String.valueOf(paymentVoucher.getPrice()).replace(".", ",") + "\n" +
                    this.hasDiscount(paymentVoucher.getDiscount()) +
                    "Valor pago: R$ " + String.valueOf(paymentVoucher.getReceived()).replace(".", ",") + "\n" +
                    paymentVoucher.getTeam().getName() + "\n" +
                    "code " + paymentVoucher.getId() + " " + paymentVoucher.getGenerationDate();

            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("+55" + paymentVoucher.getStudent().getPhone()),
                    new com.twilio.type.PhoneNumber(MY_NUMBER_PHONE),
                    bodyText
            ).create();
        }
    }

    private boolean isModeTest() {
        return Arrays.asList(environment.getActiveProfiles()).contains("test");
    }

    private String hasDiscount(Double discount) {
        return discount != 0 ?
            "Desconto: R$ " + String.valueOf(discount).replace(".", ",") + "\n"
            : "";
    }

    private String createDescription(PaymentVoucher voucher,  MonthPayment monthPayment) {
        if (voucher.getMonthPaymentId() == null) return "Matrícula \n";
        return "Mensalidade: " + monthPayment.getDueDate().format(DateTimeFormatter.ofPattern("MM/yyyy")) + "\n";
    }

    public byte[] createPdfByRegistration(Long registrationId) {
        PaymentVoucher paymentVoucher = this.createForRegistration(registrationId);
        return this.jasperService.createPdfPaymentVoucher(paymentVoucher.getId());
    }

    public byte[] createPdfByMonthPayment(Long monthPaymentId) {
        PaymentVoucher paymentVoucher = this.createForMonthPayment(monthPaymentId);
        return this.jasperService.createPdfPaymentVoucher(paymentVoucher.getId());
    }
}
