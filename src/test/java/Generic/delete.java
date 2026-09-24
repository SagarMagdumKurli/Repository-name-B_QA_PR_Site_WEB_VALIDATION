package Generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class delete {

	XSSFWorkbook wb;

	Cell cell; //Cell==its an interface of XSSFCell 

	public String Test(String sheetname, int r, int c,String value) throws Exception {
		String V = "";
		FileInputStream FI = new FileInputStream("");
		wb = new XSSFWorkbook(FI);

		 cell = wb.getSheet(sheetname).getRow(r).getCell(c);		 

		if (cell.getCellType() == CellType.STRING) {
			V = cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC) {
			long t = (long) cell.getNumericCellValue();
             
			V=String.valueOf(t);
		}
		
		return V;
	}

	public int RowCount(String path,String sheet) throws Exception, IOException 
	{

		FileInputStream FR = new FileInputStream(path);

		XSSFWorkbook xf = new XSSFWorkbook(FR);
        
		int lastRow = xf.getSheet(sheet).getLastRowNum();
		
		return 0;

	}
	
	public void setCellValue()
	{
		
		File F= new File("");
		
		
		
	}

}
