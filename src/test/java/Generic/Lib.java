package Generic;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Lib {

//	XSSFWorkbook wb;

//	public static XSSFCell cell;

	public static String GetExcelData(String path, String sheet, int r, int c) throws Exception {
		String v = null;
		long t = 0;

//		File F = new File(path); Not requires *****// path=./TestDataAndResult/data.xlsx

		
		
		try (FileInputStream FI = new FileInputStream(path); XSSFWorkbook wb = new XSSFWorkbook(FI);)
		// its is requires to close or elese it will cause proble for any further Excel
		// sheet reading issue
		// WE Achieves using try
		{

			XSSFCell cell = wb.getSheet(sheet).getRow(r).getCell(c);
                  
                    
			if (cell.getCellType() == CellType.STRING) {
				v = cell.getStringCellValue();
			} else if (cell.getCellType() == CellType.NUMERIC) {
				t = (long) cell.getNumericCellValue();
				v = String.valueOf(t);
			} else {
				v = "";
			}

			return v;

		}
	}

	public static void WAIT(WebDriver driver, WebElement element) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
		if (!element.isEnabled()) {
			throw new Exception("Element not avaialble ");
		}

		System.out.println("Element is visible & Clickable");
	}

	public static void SetCelldata(String excelPath, String sheetname, int r, int c, String result) throws Exception {

		// FileInputStream FI = new FileInputStream(excelPath);
		try (FileInputStream FI = new FileInputStream(excelPath); XSSFWorkbook wb = new XSSFWorkbook(FI)) {

			XSSFCell cell = wb.getSheet(sheetname).getRow(r).getCell(c);

			if (cell == null) {
				cell = wb.getSheet(sheetname).getRow(r).createCell(c);
			}

			cell.setCellValue(result);
			System.out.println("Result is set to: " + result);

			FileOutputStream FO = new FileOutputStream(excelPath);
			wb.write(FO);
			FO.flush();
			FO.close();

		}

	}

}
