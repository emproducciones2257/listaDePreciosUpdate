package res;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import conexionBD.conectFirebase;
import control.controlGestionPrecios;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import modelo.constantes;
import modelo.modeloMSergio;
import modelo.modeloMSergioProductos;
import modelo.preciosCloud;
import modelo.preciosDocumento;
import modelo.produCloud;
import views.ventanasAvisos;

public class updateCamposNUbe {
	
    private static HashSet<produCloud> preNube;
    private static CollectionReference colecPrecios;
    private static ArrayList<preciosCloud> precios;
    private static controlGestionPrecios procesarFichero;
    static DocumentReference docRef;
    public static java.util.List<QueryDocumentSnapshot> documents;
    static ApiFuture<QuerySnapshot> firulais;
    static ApiFuture<WriteResult> borrar;
    

	public static void main(String[] args) {
		//procesarFichero = new controlGestionPrecios(null);
		//File archivo = new File("C:\\Users\\Emanuel\\Desktop\\kiosco.pdf");
		//CollectionReference cities = conectFirebase.getFirestore().collection(constantes.COLECCION_PRECIOS);
		
		docRef = conectFirebase.getFirestore().
				collection(constantes.COLECCION_MAURITO_SERGIO_PRODUCTO).
				document("0LyXR2TVrCSgi6hpFBYx");
		
		ApiFuture<DocumentSnapshot> te = docRef.get();
		
		try {
			DocumentSnapshot doco = te.get();
			
			
			modeloMSergioProductos temp = doco.toObject(modeloMSergioProductos.class);
			
			
			System.out.println(temp.toString());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
		//extraerDtosExcel(new File("C:\\Users\\EMProducciones\\Downloads\\ListadoDePrecios.xls"));
		
		/*for (preciosDocumento objeto : procesarFichero.precios) {
		
			try {
			
				firulais = conectFirebase.getFirestore().collection("precios").
					whereEqualTo("idPrecioBDLocal", String.valueOf(objeto.getCodigo())).
					whereEqualTo("precio", objeto.getPrecio()).get();
			
				documents = firulais.get().getDocuments();
			 
					for (DocumentSnapshot document : documents) {

				  borrar = conectFirebase.getFirestore()
						  .collection("precios")
						  .document(document.getId())
						  .delete();
				  
				  System.out.println("Update time : " + borrar.get().getUpdateTime());
				}
			
			} catch (Exception e) {
			// TODO: handle exception
				}
		}	
		
		System.out.println("Termine");*/
		
	
		
		/*//preNube = obtenerNube();
		
		/*for (produCloud e : preNube) {
			DocumentReference docRef = conectFirebase.getFirestore().collection(constantes.COLECCION_PRODUCTO).document(e.getIdNube());
			
			Map<String, Object> data = new HashMap<>();
			
			data.put("precio", FieldValue.delete());
			
			ApiFuture<WriteResult> writeResult = docRef.update(data);
			try {
				System.out.println("Update time : " + writeResult.get());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// (async) Update one field
			ApiFuture<WriteResult> future = docRef.update("precio", String.valueOf(e.getPrecio()));

			// ...
			WriteResult result;
			try {
				result = future.get();
				System.out.println("Write result: " + result);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}*/
	
	
	/*private static HashSet<produCloud> obtenerNube() {
		produCloud temp = new produCloud();
		preNube = new HashSet<>();
		
		colecPrecios = conectFirebase.getFirestore().collection(constantes.COLECCION_PRODUCTO);
		
		ApiFuture<QuerySnapshot> respuestaDeConsulta = colecPrecios.get();
		
		try {
			for (DocumentSnapshot e : respuestaDeConsulta.get().getDocuments()) {
				temp = e.toObject(produCloud.class);
				temp.setIdNube(e.getId());
				preNube.add(temp);
				temp = null;
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return preNube;
	}
	
	/*private static void extraerDtosExcel(File ruta) {
		
		try {
			FileInputStream documentoExcel = new FileInputStream(ruta);
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
			e.printStackTrace();
		}
	}

	private static void procesarCelda(ArrayList<Object> filasCelda) {
		if(filasCelda.size()==3) {
			preciosCloud pcTemp = new preciosCloud();
			pcTemp.setIdPrecioBDLocal(filasCelda.get(0).toString());
			pcTemp.setPrecio(Double.valueOf(filasCelda.get(2).toString()));
			precios.add(pcTemp);
		}
	}	*/
}

}

