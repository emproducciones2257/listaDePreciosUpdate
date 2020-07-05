package res;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class leerExcel {
	
		   static XSSFRow row;
		   public static void main(String[] args) throws Exception {
		      FileInputStream fis = new FileInputStream(new File("C:\\PERFUMERIA.xlsx"));
		      
		      try (XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
				XSSFSheet spreadsheet = workbook.getSheetAt(0);
				  Iterator < Row >  rowIterator = spreadsheet.iterator();
				  
				  while (rowIterator.hasNext()) {
				     row = (XSSFRow) rowIterator.next();
				     Iterator < Cell >  cellIterator = row.cellIterator();
				     
				     while ( cellIterator.hasNext()) {
				        Cell cell = cellIterator.next();
				        
				        switch (cell.getCellType()) {
				           case NUMERIC:
				              System.out.println(cell.getNumericCellValue() + " \t\t ");
				              break;
				           
				           case STRING:
				              System.out.println(
				              cell.getStringCellValue() + " \t\t ");
				              break;
				        }
				     }
				     //System.out.println();
				  }
			}
		      fis.close();
}
}


