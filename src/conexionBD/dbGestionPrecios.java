package conexionBD;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.common.collect.Iterators;

import modelo.preciosCloud;
import modelo.preciosDocumento;
import modelo.produCloud;
import views.ventanasAvisos;

public class dbGestionPrecios {
	
	private PreparedStatement pre;
    private ResultSet resu; 
    private ventanasAvisos avisos;
    private DocumentReference docRef;
    private CollectionReference colecPrecios;
    private HashSet<preciosCloud> preNube;
    private Iterator<preciosCloud> it;
    
    public dbGestionPrecios() {
		// TODO Auto-generated constructor stub
    	avisos = new ventanasAvisos(null);	
	}
    
    public List<preciosDocumento> obtenerListadoProductosPrecios() {
    	
    	List<preciosDocumento> temp = new ArrayList<preciosDocumento>();
    	
    	try {
    		pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionRecuperarTodosProductosPrecios);
    		resu = pre.executeQuery();
    		
    		while (resu.next()) {
				preciosDocumento p = new preciosDocumento(
						resu.getInt("idPreSer"),
						resu.getInt("codigoPoducto"),
						resu.getString("descArt"),
						resu.getDouble("precio"));
				
				temp.add(p);	
			}
    		
    		resu.close();
    		pre.close();
    		coneCone.connect().close();
    		
		} catch (Exception e) {
			// TODO: handle exception
			avisos.errorConsulta(ventanasAvisos.ERROR_CONSULTA, e.getMessage());
		}
    	
		return temp;
	}

	public void cargarADB(List<preciosDocumento> preciosNuevos) {
		
		List<preciosDocumento> listaPreciosBase = obtenerListadoProductosPrecios();
		preciosCloud preCloud = new preciosCloud();
		
		//Cargo los productos por primera vez
		
		if(listaPreciosBase.isEmpty()) {

			try {
	            pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionCargaProductoPrecio);
	            for (int i = 0; i < preciosNuevos.size(); i++) {
	            	
	            	preciosDocumento temp = preciosNuevos.get(i);
	            	preCloud.setIdPrecioBDLocal(temp.getCodigo());
	            	preCloud.setPrecio(temp.getPrecio());
	            	
	            	pre.setInt(1, temp.getCodigo());
	            	pre.setString(2, temp.getProd());
	            	pre.setDouble(3, temp.getPrecio());
	            	
	            	registrarCloud(preCloud);
	            	pre.execute();
				}
	            
	            pre.close();
	            coneCone.connect().close();
	            avisos.cargaCorrecta(ventanasAvisos.CARGA_OK);
	        } catch (Exception e) {
	        	avisos.errorConsulta(ventanasAvisos.ERROR_CONSULTA, e.getMessage());
	       }
			
		}else {
			// actualizo los productos
			obtenerNube();
			try {
				pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionActualizarProductoPrecio);
				
	            for (preciosDocumento nuevo : preciosNuevos) {
	            	
	            	for (preciosDocumento base : listaPreciosBase) {
						
	            		if ((base.getCodigo()==nuevo.getCodigo())&&!(nuevo.getPrecio().equals(base.getPrecio()))) {
							pre.setDouble(1, nuevo.getPrecio());
							pre.setInt(2, nuevo.getCodigo());
							pre.executeUpdate();
							updateCloud(nuevo.getPrecio(), nuevo.getCodigo());
							break;
						}
					}
				}
	            pre.close();
	            coneCone.connect().close();
	            avisos.updateCorrecta(ventanasAvisos.UPDATE_OK);
			} catch (Exception e) {
				// TODO: handle exception
				avisos.errorUpdate(ventanasAvisos.ERROR_UPDATE, e.getMessage());
			}
		}
	}

	public List<preciosDocumento> obtenerListadoProductosPreciosFiltrados(String text) {
		List<preciosDocumento> temp = new ArrayList<preciosDocumento>();
    	
    	try {
    		pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionRecuperarProductosFiltrados + "'%" + text +"%'");
    		resu = pre.executeQuery();
    		
    		while (resu.next()) {
				preciosDocumento p = new preciosDocumento();
				p.setIdPrecio(resu.getInt(1));
				p.setProd(resu.getString(3));
				p.setPrecio(resu.getDouble(4));
				temp.add(p);	
			}
    		
    		resu.close();
    		pre.close();
    		coneCone.connect().close();
    		
		} catch (Exception e) {
			// TODO: handle exception
			avisos.errorConsulta(ventanasAvisos.ERROR_CONSULTA, e.getMessage());
		}
		return temp;
	}

	public void actualizarRegistro(preciosDocumento productoActualizar) {
		try {
			pre = coneCone.connect().prepareStatement(instruccionesSQL.instruccionActualizarProducto);
			pre.setString(1, productoActualizar.getProd());
			pre.setDouble(2, productoActualizar.getPrecio());
			pre.setInt(3, productoActualizar.getCodigo());
			pre.setInt(4, productoActualizar.getIdPrecio());
			pre.executeUpdate();
			pre.close();
			coneCone.connect().close();
		} catch (SQLException e) {
			avisos.errorUpdate(ventanasAvisos.ERROR_UPDATE, e.getMessage());
		}
	}
	
	public void registrarCloud(preciosCloud preciCloud) {
		// TODO Auto-generated method stub

    	docRef = conectFirebase.getFirestore().collection("precios").document();
		
    	ApiFuture<WriteResult> result = docRef.create(preciCloud);
    	
    	try {
			System.out.println("Update time : " + result.get().getUpdateTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private void obtenerNube() {
		preciosCloud temp = new preciosCloud();
		preNube = new HashSet<>();
		colecPrecios = conectFirebase.getFirestore().collection("precios");
		
		ApiFuture<QuerySnapshot> respuestaDeConsulta = colecPrecios.get();
		
		try {
			for (DocumentSnapshot e : respuestaDeConsulta.get().getDocuments()) {
				temp = e.toObject(preciosCloud.class);
				temp.setIdPrecio(e.getId());
				preNube.add(temp);
				temp = null;
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateCloud(Double preciCloud, int codigoBuscar) {
		
		try {
			//obtengo la referencia al documento del precio
			
			String idProdUpdate = obtenerIdProducto(codigoBuscar);
			
			docRef = conectFirebase.getFirestore().collection("precios").document(idProdUpdate);
			
			ApiFuture<WriteResult> future = docRef.update("precio", preciCloud);

			WriteResult result;
			
			result = future.get();
			
			System.out.println("ACTUALIZO CODIGO: "  + codigoBuscar + " idDocumento : " + idProdUpdate);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String obtenerIdProducto(int codigoBuscar) {
		// TODO Auto-generated method stub
		preciosCloud comparar = null;
		it = preNube.iterator();
		while (it.hasNext()) {
			comparar = it.next();
			if (comparar.getIdPrecioBDLocal()==codigoBuscar) {
				break;
			}
		}
		return comparar.getIdPrecio();
	}
}