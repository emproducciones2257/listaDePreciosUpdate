package res;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class borrame {

	public static void main(String[] args) {
try {
			
			FileInputStream documentoExcel = new FileInputStream("C:\\PERFUMERIA.xls");
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
		} catch (Exception e) {
			//avisos.CargaErronea(ventanasAvisos.ERROR_CARGA_ARCHIVO);
		}

	}
	
	private static void procesarCelda(ArrayList<Object> filasCelda) {
		if(filasCelda.size()==3) {
			System.out.println("COD " + filasCelda.get(0) + " " + filasCelda.get(1) + " " + filasCelda.get(2));
			/*preciosDocumento pTemp = new preciosDocumento((int)filasCelda.get(0),
					(String)filasCelda.get(1),
					(Double)filasCelda.get(2),
					indiceCatSelec.getIdCategoria());
			precios.add(pTemp);*/
		}
	}	

}
