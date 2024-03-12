package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.entities.PaymentVoucher;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@Service
public class JasperService {

    @Autowired
    private Connection connection;

    @Autowired
    private ResourceLoader resourceLoader;

    private static final String DIRECTORY_JASPER = "classpath:/jasper/";

    private Map<String, Object> params = new HashMap<>();

    public byte[] createPdfPaymentVoucher(PaymentVoucher paymentVoucher) {
        this.prepareParams(paymentVoucher);
        try {
            Resource resource = resourceLoader.getResource(DIRECTORY_JASPER + "payment_voucher_params.jasper");
            InputStream stream = resource.getInputStream();
            JasperPrint print = JasperFillManager.fillReport(stream, params);
            //JasperPrint print = JasperFillManager.fillReport(stream, params, connection);
           return JasperExportManager.exportReportToPdf(print);
        } catch (FileNotFoundException | JRException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void prepareParams(PaymentVoucher paymentVoucher) {
        this.params.put("DIRECTORY_IMAGES", DIRECTORY_JASPER);
        this.params.put("V_STUDENT_NAME", paymentVoucher.getStudent().getName());
        this.params.put("V_PRICE", paymentVoucher.getPrice());
        this.params.put("V_DISCOUNT", paymentVoucher.getDiscount());
        this.params.put("V_RECEIVED", paymentVoucher.getReceived());
        this.params.put("V_TEAM_NAME", paymentVoucher.getTeam().getName());
        this.params.put("V_ID", paymentVoucher.getId());
        this.params.put("V_GENERATION_DATE", paymentVoucher.getGenerationDate());
        if (paymentVoucher.getMonthPaymentId() != null)
            this.params.put("V_DUE_DATE_MONTH_PAYMENT", paymentVoucher.getDueDateMonthPayment());
    }

}
