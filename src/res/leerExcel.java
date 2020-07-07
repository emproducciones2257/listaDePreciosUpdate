package res;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class leerExcel {
	
	static XSSFRow row;
	
		public static void main(String[] args) throws Exception {
			
		FileInputStream documentoExcel = new FileInputStream(new File("C:\\PERFUMERIA.xls"));
		
		//creo libro 
		Workbook libroExcell = WorkbookFactory.create(documentoExcel);
		
		//obtengo la primero hoja del libro
		Sheet HojaLibro = libroExcell.getSheetAt(0);
		
		ArrayList<Object> filasCelda;
		
		Cell celdaTemporal = null;
		
		Iterator<Row> filaIterator = HojaLibro.rowIterator();
		filaIterator.next();
		
        while (filaIterator.hasNext()) {
        	
            Row fila = filaIterator.next();

            Iterator<Cell> celdalIterator = fila.cellIterator();
            
            filasCelda = new ArrayList<>();

            while (celdalIterator.hasNext()) {
                celdaTemporal = celdalIterator.next();
                
                if(celdaTemporal.getCellType()==CellType.NUMERIC) {
			    	filasCelda.add(celdaTemporal.getNumericCellValue());
            	}
            	if (celdaTemporal.getCellType()==CellType.STRING) {
			    	filasCelda.add(celdaTemporal.getStringCellValue());
				}
            }
            procesarCelda(filasCelda);
        }
		
		documentoExcel.close();
		libroExcell.close();
	}
		private static void procesarCelda(ArrayList<Object> filasCelda) {
			if(filasCelda.size()==3) {
				System.out.println("CODIGO: " + filasCelda.get(0)+", DESC.: " + filasCelda.get(1) + " VALOR: $ " + filasCelda.get(2));
			}

		}	
}



