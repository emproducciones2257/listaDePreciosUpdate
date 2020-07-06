package res;

import java.io.*;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class leerExcel {
	
	static XSSFRow row;
		public static void main(String[] args) throws Exception {
			
		FileInputStream fis = new FileInputStream(new File("C:\\PERFUMERIA.xls"));
		
		//creo libro 
		Workbook workbook = WorkbookFactory.create(fis);
		
		//obtengo la primero hoja del libro
		Sheet sheet = workbook.getSheetAt(0);
		
		ArrayList<Object> filasCelda;	
	
		for (Row row: sheet) {
			filasCelda = new ArrayList<>();
			
            for(Cell cell: row) {
            	
            	if(cell.getCellType()==CellType.NUMERIC) {
			    	filasCelda.add(cell.getNumericCellValue());
            	}
            	if (cell.getCellType()==CellType.STRING) {
			    	filasCelda.add(cell.getStringCellValue());
				}
            }
            procesarCelda(filasCelda);
        }
		
		fis.close();
		workbook.close();
	}
		private static void procesarCelda(ArrayList<Object> filasCelda) {
			if(filasCelda.size()==3) {
				
				boolean estado = true;
				char[] caracteres = filasCelda.get(0).toString().toCharArray();
				
				for (int i = 0; i < caracteres.length; i++) {
					if (!Character.isDigit(caracteres[i])) {
						estado=false;
					}
				}
				
				if(estado) {
					System.out.println("CODIGO: " + filasCelda.get(0)+", DESC.: " + filasCelda.get(1) + " VALOR: $ " + filasCelda.get(2));
				}	
				
			}
			
		}	
}



