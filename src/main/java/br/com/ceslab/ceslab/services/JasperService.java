package br.com.ceslab.ceslab.services;

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

    public byte[] createPdfPaymentVoucher(Long idPaymentVoucher) {
        this.params.put("PAYMENT_VOUCHER_ID", idPaymentVoucher);
        this.params.put("DIRECTORY_IMAGES", DIRECTORY_JASPER);
        try {
            Resource resource = resourceLoader.getResource(DIRECTORY_JASPER + "payment_voucher.jasper");
            InputStream stream = resource.getInputStream();
            JasperPrint print = JasperFillManager.fillReport(stream, params, connection);
           return JasperExportManager.exportReportToPdf(print);
        } catch (FileNotFoundException | JRException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
