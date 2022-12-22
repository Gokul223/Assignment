package findAndReplaceWord;

import java.io.IOException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFiles {

	
	static int rowCount;
	static int colCount;
	static String[][] englishNameData;
	
	
	public static Object[][] ReadEnglishData() throws IOException, InterruptedException {
		XSSFWorkbook book = new XSSFWorkbook("./ExcelFiles/frequency.xlsx");
		XSSFSheet sheetAt = book.getSheetAt(0);
		XSSFRow row = sheetAt.getRow(0);
		XSSFCell cell = row.getCell(0);
		rowCount = sheetAt.getLastRowNum();
		colCount = row.getLastCellNum();
		englishNameData = new String [rowCount+1][colCount];
		for(int i=0;i<=rowCount;i++) {
			for(int j =0; j<colCount; j++) {
			XSSFCell currentCell = sheetAt.getRow(i).getCell(j);
			CellType type = currentCell.getCellType();
			Object englishCellValue ;
			switch (type) {
			case NUMERIC:
				englishCellValue = currentCell.getNumericCellValue();
				break;
				
			case STRING:
				englishCellValue = currentCell.getStringCellValue();
				break;

			case BOOLEAN:
				Object booleanCellValue = currentCell.getBooleanCellValue();
				englishCellValue = booleanCellValue.toString();
				break;
			default:
				throw new RuntimeException("There is no support for this type of cell");
				
			}
			englishNameData[i][j] =  (String) englishCellValue;
//			System.out.println("English name --->  " + englishNameData[i][0]+"        FrenchName--->   "+englishNameData[i][1]);

			
	 }
	}
		book.close();
		return  englishNameData;
		  
		
		
	}
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		ReadDataFiles.ReadEnglishData();

		System.out.println(rowCount);
		System.out.println(colCount);
	}
	
	
}
	

