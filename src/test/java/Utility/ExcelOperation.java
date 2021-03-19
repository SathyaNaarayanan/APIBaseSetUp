package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperation {


	static String fileName=System.getProperty("user.dir")+"\\src\\TestData\\TestData.xlsx";
	static File f;
	static FileInputStream fs;
	static XSSFWorkbook wb;
	static XSSFSheet ws;
	static FileOutputStream fos;
	Map<String, String> hm;


	public HashMap<String, String> ReadExcelData(String sheetName) throws IOException{
		f = new File(fileName);
		fs = new FileInputStream(f);
		wb = new XSSFWorkbook(fs);
		ws = wb.getSheet(sheetName);
		hm = new HashMap<String, String>();

		try {

			int row = ws.getLastRowNum();
			for(int i=0; i<=row; i++) 
			{ 
				String key = ws.getRow(i).getCell(0).getStringCellValue();
				String value = ws.getRow(i).getCell(1).getStringCellValue().toString();
				hm.put(key, value);
			}
		}
		catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
		finally {
			wb.close();
		}
		return (HashMap<String, String>) hm;
	}

	
	public String GetPayload(String sheetName, String resource) throws IOException {
		f = new File(fileName);
		fs = new FileInputStream(f);
		wb = new XSSFWorkbook(fs);
		ws = wb.getSheet(sheetName);
		String value = "";

		try {

			int row = ws.getLastRowNum();
			for(int i=1; i<=row; i++) 
			{ 
				String data = ws.getRow(i).getCell(0).getStringCellValue().toString();
				if(data.equals(resource)) {
					value = ws.getRow(i).getCell(1).getStringCellValue().toString();
					break;
				}
			}
		}
		catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
		finally {
			wb.close();
		}
		return value;
	}
	public void Save_responseDataToExcel(String sheetName, String rowName, String value) throws Exception {
		try {
			f = new File(fileName);
			fs = new FileInputStream(f);
			wb = new XSSFWorkbook(fs);
			ws = wb.getSheet(sheetName);
			//Row row;
			Cell cell;
			switch(rowName){
				case "otp":
					cell = ws.getRow(0).createCell(1);
					cell.setCellValue(value);
					break;
				case "uuid":
					cell = ws.getRow(1).createCell(1);
					cell.setCellValue(value);
					break;
				case "Bearer":
					/*row = ws.createRow(2);
					cell = row.createCell(0);
					cell.setCellValue("Bearer");*/
					cell = ws.getRow(2).createCell(1);
					cell.setCellValue(value);
					break;
				default:
					break;				
			}
			fs.close();
			fos = new FileOutputStream(f);
			wb.write(fos);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			fos.close();
			wb.close();
		}
	}
}
