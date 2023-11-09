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

    @Transactional(readOnly = true)
    public PaymentVoucher findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Payment Voucher not found with id " + id));
    }

    @Transactional
    public PaymentVoucher createForRegistration(Long registrationId) {
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
        this.sendSms(entity);
        return entity;
    }

    @Transactional
    public PaymentVoucher createForMonthPayment(Long monthPaymentId) {
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
        this.sendSms(entity);
        return entity;
    }

    private void sendSms(PaymentVoucher paymentVoucher) {

        if (!Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            String discount = (paymentVoucher.getDiscount() != 0) ?
                "Desconto: R$ " + String.valueOf(paymentVoucher.getDiscount()).replace(".", ",") + "\n"
                : "";

            String bodyText =
                "Olá, " + paymentVoucher.getStudent().getName() +
                " aqui está seu COMPROVANTE DE PAGAMENTO CESLAB:\n" +
                "Valor total: R$ " + String.valueOf(paymentVoucher.getPrice()).replace(".", ",") + "\n" +
                discount +
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
}
