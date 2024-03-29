package conexionBD;

import java.sql.PreparedStatement;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import modelo.constantes;
import modelo.produCloud;
import modelo.producto;

public class DBGestionProductos {
	
	 private PreparedStatement pre;
	 private DocumentReference docRef;

	public void registrarProducto(producto proTemp) {
		
		 try {
	            pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionRegistrarProducto);
	            pre.setInt(1, proTemp.getMarca());
	            pre.setInt(2, proTemp.getIdPrecio());
	            pre.setInt(3, proTemp.getColor());
	            pre.setInt(4, 0);
	            pre.setString(5, proTemp.getDtosExtras());
	            pre.setInt(6, proTemp.getUnidadDeVenta());
	            pre.setInt(7, proTemp.getMedida());
	            pre.setString(8, proTemp.getCodBarr());
	            pre.setInt(9, proTemp.getIdCategoria());
	            pre.execute();
	            coneCone.connect().close();
	        } catch (Exception e) {
	        	System.out.print("No se pudo cargar producto" + e.getMessage());
	        }
	}


	public void registrarCloud(produCloud produCloud, String nombCat) {
		
		if(nombCat.equals("LIBRERIA")) {
			docRef = conectFirebase.getFirestore()
	    			.collection(constantes.COLECCION_PRODUCTO).document();
		}else {
			docRef = conectFirebase.getFirestore()
	    			.collection(constantes.COLECCION_PRODUCTO_PERFU).document();
		}

    	ApiFuture<WriteResult> result = docRef.create(produCloud);
    	
    	try {
			System.out.println("Update time : " + result.get().getUpdateTime());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
