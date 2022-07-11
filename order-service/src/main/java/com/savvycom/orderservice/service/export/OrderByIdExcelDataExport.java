package com.savvycom.orderservice.service.export;

import com.savvycom.orderservice.domain.output.OrderOutput;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 11:49 AM
 * Project Name:  order-service
 */
public class OrderByIdExcelDataExport extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(
            Map<String, Object> model,
            Workbook workbook,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // define excel file name to be exported
        response.addHeader("Content-Disposition", "attachment;fileName=OrderData.xlsx");

        // read data provided by controller
        @SuppressWarnings("unchecked")
        List<OrderOutput> list = (List<OrderOutput>) model.get("list");

        // create one sheet
        Sheet sheet = workbook.createSheet("Orders of customer");

        // create row0 as a header
        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("ID");
        row0.createCell(1).setCellValue("NAME");
        row0.createCell(2).setCellValue("LOCATION");
        row0.createCell(3).setCellValue("PHONE");

        // create row1 onwards from List<T>
        int rowNum = 1;
        for (OrderOutput spec : list) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(spec.getOrderId());
            row.createCell(1).setCellValue(spec.getReceiverName());
            row.createCell(2).setCellValue(spec.getReceiverAddress());
            row.createCell(3).setCellValue(spec.getPhoneNumber());
        }
    }
}
