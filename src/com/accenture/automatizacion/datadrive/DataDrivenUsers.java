package com.accenture.automatizacion.datadrive;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
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
////            
//            System.out.println(rows);
//            for(int i = 1; i <= rows; i++)
//            {
////                if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
////                    col_Num = i;
//            	row = sheet.getRow(i);
//            }
//            System.out.println(col_Num);
            row = sheet.getRow(rowNum + 1);
            colName_user = row.getCell(0).getStringCellValue();
            colName_pass = row.getCell(1).getStringCellValue();
            
            credencial.setUsername(colName_user);
            credencial.setPassword(colName_pass);
            return credencial;
//            if(cell.getCellTypeEnum() == CellType.STRING)
//                return cell.getStringCellValue();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
			return null;
        }
    }
	
	public void setStatus(int rowNum, String xlFilePath, String status) throws Exception{
		
		String sheetName = "Hoja 1";
		
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum+1);
        cell = row.createCell(2);
        cell.setCellValue(status);
         
        FileOutputStream fos = new FileOutputStream(xlFilePath);
        workbook.write(fos);
        fos.close();
	}
	
	
	

}
