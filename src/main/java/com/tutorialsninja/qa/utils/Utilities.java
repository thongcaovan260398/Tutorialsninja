package com.tutorialsninja.qa.utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Objects;

public class Utilities {

    public static final int IMPLICIT_WAIT_TIME = 10;
    public static final int PAGE_WAIT_TIME =5;
    public static String generateEmailWithTimeStamp(){
        Date date = new Date();
        String timestamp = date.toString().replace(" ", "_").replace(":","_");
        return "thongcaovan260398" + timestamp + "@gmail.com";
    }

    public static Object[][] getTestDataFromExcel(String sheetName){
        File excelFile = new File("src/test/resources/TutorialsNinjaTestData.xlsx");
        XSSFWorkbook workbook = null;
        try {
            FileInputStream fisExcel = new FileInputStream(excelFile);
            workbook = new XSSFWorkbook(fisExcel);
        } catch (Throwable e){
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object [][] data = new Object[rows][cols];

        for(int i=0;i<rows;i++){
            XSSFRow row = sheet.getRow(i+1);

            for(int j=0;j<cols;j++){

                XSSFCell cell= row.getCell(j);
                CellType cellType = cell.getCellType();

                switch (cellType){
                    case STRING:
                        data[i][j] = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        data[i][j] = Integer.toString((int) cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        data[i][j] = cell.getBooleanCellValue();
                        break;
                }
            }
        }
        return data;
    }

}
