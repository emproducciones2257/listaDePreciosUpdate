package conexionBD;

import java.util.concurrent.ExecutionException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import modelo.constantes;
import modelo.modeloMSergio;
import modelo.modeloMSergioProductos;
import views.PnlMauroSergio;
import views.ventanasAvisos;

public class dBMSergio {
	
	private PnlMauroSergio pnlMaurito;
	private DocumentReference docRef;
	private CollectionReference colecPrecios;
	private ventanasAvisos avisos;
	private String idDoc;
	private modeloMSergio articuloBuscado;
	
	public dBMSergio(PnlMauroSergio pnlMaurito) {
		this.pnlMaurito=pnlMaurito;
		avisos = new ventanasAvisos(null);
		articuloBuscado=null;
	}
	
	
	//cargo los articulos con los precios
	public void cargarRegistroPrecio(modeloMSergio sergito) {
		
		if(buscarPorArticulo(sergito.getArticulo(),false)==null) {
			
			docRef = conectFirebase.getFirestore().
					collection(constantes.COLECCION_MAURITO_SERGIO).
					document();

	    	docRef.create(sergito);
	    	
	    	try {
	    		avisos.cargaCorrecta(ventanasAvisos.CARGA_OK);
	    		pnlMaurito.limpiarCargaPrecio();
			} catch (Exception e) {
				avisos.CargaErronea(e.getMessage());
			} 
		}else avisos.datoExistente(ventanasAvisos.ARTICULO_EXISTENTE);		
	}

	
	//busco si esta el articulo registrado
	public modeloMSergio buscarPorArticulo(String art, Boolean mostrarMsj) {
		
		colecPrecios = conectFirebase.
				getFirestore().
				collection(constantes.COLECCION_MAURITO_SERGIO);
			
		Query response = colecPrecios.whereEqualTo(constantes.CAMPO_ARTICULO, art);
		
		ApiFuture<QuerySnapshot> resultado = response.get();
		
		try {
			if(!(resultado.get().getDocuments().isEmpty())) {
				articuloBuscado = resultado.get().getDocuments().get(0).toObject(modeloMSergio.class);
				idDoc = resultado.get().getDocuments().get(0).getId();
			}else {
				if(mostrarMsj)avisos.datoExistente(ventanasAvisos.NO_ESTA_EL_ARTICULO);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return articuloBuscado;
	}

	
	//registro producto con codigo de barras MSergio
	public void cargarProductoMSergio(modeloMSergioProductos productoMSergio) {
		
		modeloMSergio temp = buscarPorArticulo(productoMSergio.getArticulo(),true);
		
		if(temp!=null) {
			productoMSergio.setId(idDoc);
			
			docRef = conectFirebase.getFirestore().
					collection(constantes.COLECCION_MAURITO_SERGIO_PRODUCTO).
					document();

	    	docRef.create(productoMSergio);
	    	
	    	try {
	    		avisos.cargaCorrecta(ventanasAvisos.CARGA_OK);
	    		pnlMaurito.limpiarCargaProducto();
			} catch (Exception e) {
				avisos.CargaErronea(e.getMessage());
			} 
		}	
	}

	
	//actualizo registro de precios
	public void updateRegistro(modeloMSergio precioMSergio) {
		
		docRef = conectFirebase.getFirestore().
				collection(constantes.COLECCION_MAURITO_SERGIO).
				document(idDoc);
		
		ApiFuture<WriteResult> future = docRef.update(constantes.CAMPO_PRECIO, precioMSergio.getPrecio());
		
		WriteResult result;
		
		try {
			result = future.get();
			avisos.updateCorrecta(ventanasAvisos.UPDATE_OK);
		} catch (InterruptedException e) {
			avisos.errorUpdate(ventanasAvisos.ERROR_UPDATE, e.getMessage());
		} catch (ExecutionException e) {
			avisos.errorUpdate(ventanasAvisos.ERROR_UPDATE, e.getMessage());
		}	
	}	
}
