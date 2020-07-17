package modelo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import conexionBD.conectFirebase;

public class actualizarDocumento {

	public static void main(String[] args) {
		
		HashSet<preCloLibre> preNube =obtenerNube();
		
		/*for ( preCloLibre obj : preNube) {
			
			preciosCloud temp = new preciosCloud();
			temp.setIdPrecio(null);
			temp.setIdPrecioBDLocal(String.valueOf(obj.getIdPrecioBDLocal()));
			temp.setPrecio(obj.getPrecio());
			
			DocumentReference docRef = conectFirebase.getFirestore().collection(constantes.COLECCION_PRECIOS).document(obj.getIdPrecio());
			
			
			
		}*/
		
		DocumentReference docRef = conectFirebase.getFirestore().collection(constantes.COLECCION_PRECIOS).document("079HGbapWdacnuyH3oiL");
		
		Map<String, Object> updates = new HashMap<>();
		updates.put("idPrecioBDLocal", FieldValue.delete());
		// Update and delete the "capital" field in the document
		ApiFuture<WriteResult> writeResult = docRef.update(updates);
		try {
			System.out.println("Update time : " + writeResult.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ApiFuture<WriteResult> future = docRef.update("idPrecioBDLocal", "2957");
		
		WriteResult result;
		try {
			result = future.get();
			System.out.println("Write result: " + result);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static HashSet<preCloLibre> obtenerNube() {
		
		preCloLibre temp = new preCloLibre();
		HashSet<preCloLibre> preNube = new HashSet<>();
		
		CollectionReference colecPrecios;
		
		colecPrecios = conectFirebase.getFirestore().collection(constantes.COLECCION_PRECIOS);

		ApiFuture<QuerySnapshot> respuestaDeConsulta = colecPrecios.get();
		
		
		try {
			for (DocumentSnapshot e : respuestaDeConsulta.get().getDocuments()) {
				temp = e.toObject(preCloLibre.class);
				temp.setIdPrecio(e.getId());
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
}
