package com.savvycom.orderservice.service.export;

import com.savvycom.orderservice.domain.entity.Order;
import com.savvycom.orderservice.domain.output.OrderOutput;
import com.savvycom.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Create by Nam Ga Sky
 * Date: 7/5/2022
 * Time: 3:39 PM
 * Project Name:  order-service
 */
@Component
@RequiredArgsConstructor
public class OrderWriteExcel {
    private final OrderService orderService;
    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_RECEIVER_NAME = 1;
    public static final int COLUMN_INDEX_RECEIVER_ADDRESS = 2;
    public static final int COLUMN_INDEX_PHONE = 3;
    public static final int COLUMN_INDEX_TOTAL_PRICE = 4;
    public static final int COLUMN_INDEX_PAYMENT = 5;
    public static final int COLUMN_INDEX_STATUS = 6;
    public static final int COLUMN_INDEX_STAFF = 7;
    private static CellStyle cellStyleFormatNumber = null;

    public void export() throws IOException {

        final List<OrderOutput> allOrders = orderService.getAllOrders();
        final String excelFilePath = "C:\\Users\\laptop88\\OneDrive\\order.xlsx";
        writeExcel(allOrders, excelFilePath);
    }

    public static void writeExcel(List<OrderOutput> books, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook = getWorkbook(excelFilePath);

        // Create sheet
        Sheet sheet = workbook.createSheet("Books"); // Create sheet with sheet name

        int rowIndex = 0;

        // Write header
        writeHeader(sheet, rowIndex);

        // Write data
        rowIndex++;
        for (OrderOutput book : books) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeBook(book, row);
            rowIndex++;
        }

        // Write footer
        writeFooter(sheet, rowIndex);

        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        // Create file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }


    // Create workbook
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create row
        Row row = sheet.createRow(rowIndex);

        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Order Id");

        cell = row.createCell(COLUMN_INDEX_RECEIVER_NAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Receiver Name");

        cell = row.createCell(COLUMN_INDEX_RECEIVER_ADDRESS);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Receiver Address");

        cell = row.createCell(COLUMN_INDEX_PHONE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Phone Number");

        cell = row.createCell(COLUMN_INDEX_TOTAL_PRICE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Total price");

        cell = row.createCell(COLUMN_INDEX_PAYMENT);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Payment");

        cell = row.createCell(COLUMN_INDEX_STATUS);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Status");

        cell = row.createCell(COLUMN_INDEX_STAFF);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Staff name");
    }

    // Write data
    private static void writeBook(OrderOutput orderOutput, Row row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }

        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(orderOutput.getOrderId());

        cell = row.createCell(COLUMN_INDEX_RECEIVER_NAME);
        cell.setCellValue(orderOutput.getReceiverName());

        cell = row.createCell(COLUMN_INDEX_RECEIVER_ADDRESS);
        cell.setCellValue(orderOutput.getReceiverAddress());
        cell.setCellStyle(cellStyleFormatNumber);

        cell = row.createCell(COLUMN_INDEX_PHONE);
        cell.setCellValue(orderOutput.getPhoneNumber());

        cell = row.createCell(COLUMN_INDEX_TOTAL_PRICE);
        cell.setCellValue(orderOutput.getTotalPrice());

        cell = row.createCell(COLUMN_INDEX_PAYMENT);
        cell.setCellValue(orderOutput.getPaymentId());

        cell = row.createCell(COLUMN_INDEX_STATUS);
        cell.setCellValue(orderOutput.getStatus().getDeliveryStatus());

        cell = row.createCell(COLUMN_INDEX_STAFF);
        cell.setCellValue(orderOutput.getStaff().getStaffName());

        // Create cell formula
        // totalMoney = price * quantity
//        cell = row.createCell(COLUMN_INDEX_TOTAL, CellType.FORMULA);
//        cell.setCellStyle(cellStyleFormatNumber);
//        int currentRow = row.getRowNum() + 1;
//        String columnPrice = CellReference.convertNumToColString(COLUMN_INDEX_PRICE);
//        String columnQuantity = CellReference.convertNumToColString(COLUMN_INDEX_QUANTITY);
//        cell.setCellFormula(columnPrice + currentRow + "*" + columnQuantity + currentRow);
    }

    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    // Write footer
    private static void writeFooter(Sheet sheet, int rowIndex) {
        // Create row
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(COLUMN_INDEX_TOTAL_PRICE, CellType.FORMULA);
        cell.setCellFormula("SUM(E2:E6)");
    }

    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }

}
