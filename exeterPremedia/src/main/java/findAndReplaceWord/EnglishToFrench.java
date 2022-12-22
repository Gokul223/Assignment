package findAndReplaceWord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class EnglishToFrench  extends ReadDataFiles{

	public static void main(String[] args) throws IOException, InterruptedException {
		Object[][] readEnglishData = ReadEnglishData();
		File readfile = new File("./ExcelFiles/t8.shakespeare.txt");
		File writefile = new File("./ExcelFiles/shakespeare2.txt");
		String oldContent = "" ;
		String newContent = "" ;
		String replaceword ="";
		String replacement ="";
		
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
			System.out.println("The word "+ replaceword + " replaced to " + replacement + " for " + count + " times ");
			reader1.close();
		}
		FileWriter writer = new FileWriter(writefile);
		writer.write(newContent);
		reader.close();
		writer.close();
	}

}

