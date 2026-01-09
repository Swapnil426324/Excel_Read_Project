package com.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	// for reading all excel sheet using POI
		public static String readExcel(String fileName, String sheet) throws Exception {
			String data = null;
			DataFormatter df = new DataFormatter();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/" + fileName + ".xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheet);
			int rows = sh.getLastRowNum();
			for (int i = 0; i <= rows; i++) {
				int col = sh.getRow(i).getLastCellNum();
				for (int j = 0; j < col; j++) {
					Cell c = sh.getRow(i).getCell(j);
					data = df.formatCellValue(c);
					System.out.print(data + " ");
				}
				//System.out.println();
			}
			return data;
		}

		// for read by row and column number using POI
		public static String excelRead(String fileName, String sheet, int row, int col) {
			DataFormatter df = new DataFormatter();
			FileInputStream fis = null;
			Workbook wb = null;
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/" + fileName + ".xlsx");
				wb = WorkbookFactory.create(fis);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Sheet sh = wb.getSheet(sheet);
			Cell c = sh.getRow(row).getCell(col);
			return df.formatCellValue(c);
		}
		

		// for write by row and column number using POI
		public static void writeExcel(String fileName, String sheet, int row, int col, String result) {

			FileInputStream fis = null;
			FileOutputStream fos = null;
			Workbook wb = null;
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/" + fileName + ".xlsx");
				wb = WorkbookFactory.create(fis);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Sheet sh = wb.getSheet(sheet);
			Cell c = sh.getRow(row).createCell(col);
			c.setCellValue(result);
			try {
				fos = new FileOutputStream(System.getProperty("user.dir") + "/src/test/resources/" + fileName + ".xlsx");
				wb.write(fos);
				wb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
