package br.com.ceslab.ceslab.services;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@Service
public class JasperService {

    @Autowired
    private Connection connection;

    private static final String DIRECTORY_JASPER = "classpath:jasper/";

    private Map<String, Object> params = new HashMap<>();

    public byte[] createPdfPaymentVoucher(Long idPaymentVoucher) {
        this.params.put("PAYMENT_VOUCHER_ID", idPaymentVoucher);
        this.params.put("DIRECTORY_IMAGES", DIRECTORY_JASPER);
        try {
            File file = ResourceUtils.getFile(DIRECTORY_JASPER + "payment_voucher.jasper");
            JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params, connection);
           return JasperExportManager.exportReportToPdf(print);
        } catch (FileNotFoundException | JRException e) {
            e.printStackTrace();
        }
        return null;
    }

}
