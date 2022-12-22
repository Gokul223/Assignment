package findAndReplaceWord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class EnglishToFrench  extends ReadDataFiles{

	public static void main(String[] args) throws IOException, InterruptedException {
		
		long beforeRuntime = System.currentTimeMillis();
		long totalMemoryBefore = Runtime.getRuntime().totalMemory();
		long freeMemoryBefore = Runtime.getRuntime().freeMemory();
		long memoryBefore = totalMemoryBefore - freeMemoryBefore;
		
		Object[][] readEnglishData = ReadEnglishData();	
		File readfile = new File("./ExcelFiles/t8.shakespeare.txt");
		File writefile = new File("./ExcelFiles/t8.shakespeare.translated.txt");
		File performancefile = new File("./ExcelFiles/performance.txt");
		File gitfile = new File("./ExcelFiles/githublink.txt");
		
		FileWriter gitwriter = new FileWriter(gitfile);
		gitwriter.write("https://github.com/Gokul223/Assignment");
		gitwriter.close();
		String oldContent = "" ;
		String newContent = "" ;
		String replaceword ="";
		String replacement =""; 
		int repeatCount [] = new int[englishNameData.length];
		
		String line = "";
		BufferedReader reader = new BufferedReader(new FileReader(readfile));
		 line = reader.readLine();
		while(line != null) {
			oldContent = oldContent + line + System.lineSeparator();	
			line = reader.readLine();
			System.out.println(line);
		} 
		for(int i = 0; i < englishNameData.length;i++) {
			replaceword = englishNameData[i][0];
			replacement = englishNameData[i][1];
			
			int count =0 ;
			BufferedReader reader1 = new BufferedReader(new FileReader(readfile));
				String line2 = reader1.readLine();
				while(line2!=null) {
					if(line2.contains(replaceword)) {
				count++;
					}
				line2 = reader1.readLine();
					
				  }
			newContent = oldContent.replaceAll(replaceword,replacement);
			oldContent = newContent;
			repeatCount[i] = count;
			System.out.println("The word "+ replaceword + " replaced to " + replacement + " for " + count + " times ");
			reader1.close();
		}
		
		File bookfile = new File("./ExcelFiles/frequency.xlsx");
		FileInputStream fis = new FileInputStream(bookfile);
		Workbook book = WorkbookFactory.create(fis);
		Sheet sheetAt = book.getSheetAt(0);
		for (int i= 0; i<repeatCount.length;i++) {
			Row row = sheetAt.createRow(i);
			Cell cell1 = row.createCell(0);
			Cell cell2 = row.createCell(1);
			Cell cell3 = row.createCell(2);
			cell1.setCellValue(englishNameData[i][0]);
			cell2.setCellValue(englishNameData[i][1]);
			cell3.setCellValue(repeatCount[i]);
		}
		fis.close();
		FileOutputStream fos = new FileOutputStream(bookfile);
		book.write(fos);
		book.close();
		fos.close();
		
		FileWriter writer = new FileWriter(writefile);
		writer.write(newContent);
		reader.close();
		writer.close();
		FileWriter performWriter = new FileWriter(performancefile);
		long totalMemoryAfter = Runtime.getRuntime().totalMemory();
		long freeMemoryAfter = Runtime.getRuntime().freeMemory();
		long memoryAfter = totalMemoryAfter - freeMemoryAfter;
		System.out.println("Memory before run --- >  " + memoryBefore);
		System.out.println("Memory after run --->  " + memoryAfter);
		long memoryUsed = memoryAfter - memoryBefore;
		System.out.println("Memory used --->  " + memoryUsed);
		long memory = (long) (memoryUsed * 0.000001);
		Object memoryUsedMB = memory + "MB" ;
		System.out.println("Memory Used in MB --->  " + memoryUsedMB);
		System.out.println("Memory ---> " + memory);
		long afterRuntime = System.currentTimeMillis();
		long runtime = (afterRuntime - beforeRuntime) ;
		long minutes = (runtime / 1000 )/60;
		long seconds = (runtime / 1000) % 60 ;
		System.out.println("Runtime --->  " + runtime);
		performWriter.write("Time to process: "+ minutes + " minutes "+ seconds +" seconds "+"\n");
		performWriter.write("Memory used: "+ memory + " MB "+"\n");
		performWriter.close();
	}

}

