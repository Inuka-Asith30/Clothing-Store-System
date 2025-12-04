package controller;

import javafx.scene.control.Alert;
import model.InvoiceMainDetails;
import model.entity.AddToCardEntity;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvoiceController {

    public void generateOrderInvoice(InvoiceMainDetails invoiceMainDetails,List<AddToCardEntity> addToCardEntityList){
        try {
            JasperReport jasperReport= (JasperReport) JRLoader.loadObjectFromFile("C:\\Users\\inuka\\Documents\\JavaFx-projects\\CothingStoreSystem\\src\\main\\resources\\reports\\demo1.jasper");

            List<InvoiceMainDetails> invoiceMainDetailsList=new ArrayList<>();

            invoiceMainDetailsList.add(invoiceMainDetails);

            JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(invoiceMainDetailsList);

            JRBeanCollectionDataSource tableDataSource=new JRBeanCollectionDataSource(addToCardEntityList);

            Map<String, Object> parameters=new HashMap<String,Object>();
            parameters.put("TABLE_DATA_SOURCE",tableDataSource);


            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters,dataSource);

            JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\inuka\\Documents\\JavaFx-projects\\CothingStoreSystem\\src\\main\\resources\\invoices\\invoice.pdf");

            System.out.println("Report Generated Successfully");

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

}
