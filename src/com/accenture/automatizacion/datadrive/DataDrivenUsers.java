package com.accenture.automatizacion.datadrive;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.accenture.automatizacion.dto.Credenciales;

public class DataDrivenUsers {
	
	public FileInputStream fis = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;
	
	
	public DataDrivenUsers(String exelfilepath) throws Exception {
		
		fis = new FileInputStream(exelfilepath);
        workbook = new XSSFWorkbook(fis);
        fis.close();
		
	}
	
	public Credenciales getCellData(int rowNum)
    {
		
        try
        {
        	Credenciales credencial = new Credenciales();
        	String sheetName = "Hoja 1";
            sheet = workbook.getSheet(sheetName);
            String colName_user = "";
            String colName_pass = "";
            row = sheet.getRow(rowNum + 1);
            colName_user = row.getCell(0).getStringCellValue();
            colName_pass = row.getCell(1).getStringCellValue();
            
            credencial.setUsername(colName_user);
            credencial.setPassword(colName_pass);
            
            return credencial;     
        }
        catch(Exception e)
        {
        	System.out.print("\n\t Se presento Excepción: Data Driven is finished" + e.getStackTrace());
			return null;
        }
    }
	
	public void setStatus(int rowNum, String xlFilePath, String status){
		
		String sheetName = "Hoja 1";
		XSSFFont font = workbook.createFont();
        XSSFCellStyle style = workbook.createCellStyle();
        
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum+1);
        cell = row.createCell(2);
     
        if(status.equals("Correct"))
        	font.setColor(HSSFColor.GREEN.index);
        else if(status.equals("Incorrect"))
        	font.setColor(HSSFColor.RED.index);
        style.setFont(font);
        cell.setCellStyle(style);
        cell.setCellValue(status);
        
        try {
        	FileOutputStream fos = new FileOutputStream(xlFilePath);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			System.out.print("\n\t Se presento Excepción: Status Data Driven is not written" + e.getStackTrace());
		}
        
	}
	
	
	

}
