package press3Initialzers_Processors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;

public class GenericExcelProcesser {

	public String Path = "";
	public FileInputStream file;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public String[] coloumnArrays;

	public GenericExcelProcesser(String ExcelFilePath) throws IOException {

		this.Path = ExcelFilePath;
		this.file = new FileInputStream(this.Path);
		this.workbook = new XSSFWorkbook(this.file);
		this.sheet = this.workbook.getSheetAt(0);
	}

	public String[] getColoumnsArray() {
		coloumnArrays = new String[readColsCount()];

		Row row = this.sheet.getRow(0);
		int colsCount = row.getPhysicalNumberOfCells();

		for (int colIndex = 0; colIndex < colsCount; colIndex++) {
			Cell cell = row.getCell(colIndex);
            
			System.out.println(cell.getStringCellValue());
			System.out.println(colIndex);
			coloumnArrays[colIndex] = cell.getStringCellValue();

		}
		System.out.println("read Data From Excel");
		return coloumnArrays;

	}

	public String readcellValue(int rowIndex, int coloumnIndex) {
		String CellData = "";
		Row row = this.sheet.getRow(rowIndex);
		
		Cell cell = row.getCell(coloumnIndex);
		if (cell != null) {
			CellData = cell.getStringCellValue();
		}

		return CellData;
	}

	public int readRowsCount() {
		int rowCount = this.sheet.getLastRowNum();
		return rowCount;
	}

	public int readColsCount() {
		Row row = this.sheet.getRow(0);
		int colsCount = row.getPhysicalNumberOfCells();
		return colsCount;
	}
	
	public String formatCell(int rowIndex, int coloumnIndex)
	{
		
		DataFormatter formatter = new DataFormatter();
		String val = formatter.formatCellValue(sheet.getRow(rowIndex).getCell(coloumnIndex));
		
		return val;
	}
	

}
