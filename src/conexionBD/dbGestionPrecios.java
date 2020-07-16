package conexionBD;


import java.sql.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import modelo.constantes;
import modelo.preciosCloud;
import modelo.preciosDocumento;
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
    	avisos = new ventanasAvisos(null);	
	}
    
    public List<preciosDocumento> obtenerListadoProductosPrecios(int codCat) {
    	
    	List<preciosDocumento> temp = new ArrayList<preciosDocumento>();
    	
    	try {
    		pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionRecuperarTodosProductosPrecios);
    		pre.setInt(1, codCat);
    		resu = pre.executeQuery();
    		
    		while (resu.next()) {
				preciosDocumento p = new preciosDocumento(
						resu.getInt(1),
						resu.getString(2),
						resu.getString(3),
						resu.getDouble(4));
						resu.getInt(5);
				temp.add(p);	
			}
    		
    		resu.close();
    		pre.close();
    		coneCone.connect().close();
    		
		} catch (Exception e) {
			avisos.errorConsulta(ventanasAvisos.ERROR_CONSULTA, e.getMessage());
		}
		return temp;
	}

	public void cargarADB(List<preciosDocumento> preciosNuevos, String categoriaSeleccionada) {
		
		List<preciosDocumento> listaPreciosBase = obtenerListadoProductosPrecios(preciosNuevos.get(0).getCategoria());
		
		Connection conecone = null;
		
		//Cargo los productos por primera vez
		
		if(listaPreciosBase.isEmpty()) {

			cargarArticulosNuevos(preciosNuevos,categoriaSeleccionada);
			
		}else {
			
			int res;
			// actualizo los productos
			
			obtenerNube(categoriaSeleccionada);
			
			List<preciosDocumento> preciosNuevosParaCargar = new ArrayList<>();
			
			try {
				conecone = coneCone.connect();
				for (preciosDocumento e : preciosNuevos) {
					
					preciosDocumento p = obtenerProductoXCodProd(e.getCodigo(),e.getCategoria(),conecone);
					
					if (p==null) {

						preciosNuevosParaCargar.add(e);

					}else {
						res = Double.compare(e.getPrecio(), p.getPrecio());
						
						if (res!=0) {
							
							actualizarPrecio(conecone,e, categoriaSeleccionada);	
						}
					}
				}
				
				if(!preciosNuevosParaCargar.isEmpty()) {
					
					cargarArticulosNuevos(preciosNuevosParaCargar, categoriaSeleccionada);
				}
				
				conecone.close();
	            avisos.updateCorrecta(ventanasAvisos.UPDATE_OK);
			} catch (Exception e) {
				avisos.errorUpdate(ventanasAvisos.ERROR_UPDATE, e.getMessage());
			}
		}
	}

	private void cargarArticulosNuevos(List<preciosDocumento> preciosNuevos, String categoriaSeleccionada) {
		
		preciosCloud preCloud = new preciosCloud();
		
		try {
			Connection co = coneCone.connect();
			
			pre = co.prepareStatement(instruccionesSQL.instruccionCargaProductoPrecio);
			
			for (int i = 0; i < preciosNuevos.size(); i++) {
				preciosDocumento temp = preciosNuevos.get(i);
				preCloud.setIdPrecioBDLocal(temp.getCodigo());
				preCloud.setPrecio(temp.getPrecio());
				
				pre.setString(1, temp.getCodigo());
				pre.setString(2, temp.getProd());
				pre.setDouble(3, temp.getPrecio());
				pre.setInt(4, temp.getCategoria());
				
				registrarCloud(preCloud, categoriaSeleccionada);
				pre.execute();
			}
			avisos.cargaCorrecta(ventanasAvisos.CARGA_OK);
			co.close();
		} catch (SQLException e) {
			avisos.errorConsulta(ventanasAvisos.ERROR_CONSULTA, e.getMessage());
		}
	}

	public List<preciosDocumento> obtenerListadoProductosPreciosFiltrados(String text, int idCat) {
		List<preciosDocumento> temp = new ArrayList<preciosDocumento>();
    	
    	try {
    		pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionRecuperarProductosFiltrados 
    													+ "'%" + text +"%' AND idCat = " + idCat);
    		resu = pre.executeQuery();
    		
    		while (resu.next()) {
				preciosDocumento p = new preciosDocumento();
				p.setIdPrecio(resu.getInt(1));
				p.setCodigo(resu.getString(2));
				p.setProd(resu.getString(3));
				p.setPrecio(resu.getDouble(4));
				temp.add(p);	
			}
    		
    		resu.close();
    		pre.close();
    		coneCone.connect().close();
    		
		} catch (Exception e) {
			avisos.errorConsulta(ventanasAvisos.ERROR_CONSULTA, e.getMessage());
		}
		return temp;
	}

	public void actualizarRegistro(preciosDocumento productoActualizar) {
		try {
			pre = coneCone.connect().prepareStatement(instruccionesSQL.instruccionActualizarProducto);
			pre.setString(1, productoActualizar.getProd());
			pre.setDouble(2, productoActualizar.getPrecio());
			pre.setString(3, productoActualizar.getCodigo());
			pre.setInt(4, productoActualizar.getIdPrecio());
			pre.executeUpdate();
			pre.close();
			coneCone.connect().close();
		} catch (SQLException e) {
			avisos.errorUpdate(ventanasAvisos.ERROR_UPDATE, e.getMessage());
		}
	}
	
	public void registrarCloud(preciosCloud preciCloud, String categoriaSeleccionada) {
		
		if (categoriaSeleccionada.equals("LIBRERIA")) {
			
			docRef = conectFirebase.getFirestore().collection(constantes.COLECCION_PRECIOS).document();
		}else {
			docRef = conectFirebase.getFirestore().collection(constantes.COLECCION_PRECIOS_PERFU).document();
		}

    	ApiFuture<WriteResult> result = docRef.create(preciCloud);
    	
    	try {
			// ACA OBTENGO EL ID DEL REGISTRO NUEVO QUE CARGO System.out.println("CARGUE A LA BASE: " + docRef.getId());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private void obtenerNube(String categoriaSeleccionada) {
		preciosCloud temp = new preciosCloud();
		preNube = new HashSet<>();
		
		if (categoriaSeleccionada.equals("LIBRERIA")) {
			colecPrecios = conectFirebase.getFirestore().collection(constantes.COLECCION_PRECIOS);
		}else {
			colecPrecios = conectFirebase.getFirestore().collection(constantes.COLECCION_PRECIOS_PERFU);
		}

		ApiFuture<QuerySnapshot> respuestaDeConsulta = colecPrecios.get();
		
		try {
			for (DocumentSnapshot e : respuestaDeConsulta.get().getDocuments()) {
				temp = e.toObject(preciosCloud.class);
				temp.setIdPrecio(e.getId());
				preNube.add(temp);
				temp = null;
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	private void updateCloud(Double preciCloud, String codigoBuscar, String categoriaSeleccionada) {
		
		try {
			//obtengo la referencia al documento del precio
			
			String idProdUpdate = obtenerIdProducto(codigoBuscar);
			
			if (categoriaSeleccionada.equals("LIBRERIA")) {

				docRef = conectFirebase.getFirestore().collection(constantes.COLECCION_PRECIOS).document(idProdUpdate);
			}else {
				docRef = conectFirebase.getFirestore().collection(constantes.COLECCION_PRECIOS_PERFU).document(idProdUpdate);
			}

			ApiFuture<WriteResult> future = docRef.update(constantes.CAMPO_PRECIO, preciCloud);

			WriteResult result;
			
			result = future.get();
			
			System.out.println("ACTUALIZO CODIGO: "  + codigoBuscar + " idDocumento : " + idProdUpdate);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private String obtenerIdProducto(String codigoBuscar) {
		
		preciosCloud comparar = null;
		it = preNube.iterator();
		while (it.hasNext()) {
			comparar = it.next();
			if (comparar.getIdPrecioBDLocal().equals(codigoBuscar)) {
				break;
			}
		}
		return comparar.getIdPrecio();
	}
	
	public preciosDocumento obtenerProductoXCodProd(String codProd,int codCat,Connection con) {
		preciosDocumento p = null;
    	
    	try {
    		pre= con.prepareStatement(instruccionesSQL.instruccionRecuperarProductoXId);
    		pre.setString(1, codProd);
    		pre.setInt(2, codCat);
    		resu = pre.executeQuery();
    		
    		while (resu.next()) {
    			p = new preciosDocumento();
    			p.setIdPrecio(resu.getInt(1));
    			p.setCodigo(resu.getString(2));
    			p.setProd(resu.getString(3));
    			p.setPrecio(resu.getDouble(4));
    			p.setCategoria(resu.getInt(5));
			}
    		
		} catch (Exception e) {
			avisos.errorConsulta(ventanasAvisos.ERROR_CONSULTA, e.getMessage());
		}
    	
		return p;
	}

	private void actualizarPrecio(Connection tet,preciosDocumento e, String categoriaSeleccionada) {
		
		try {

			pre= tet.prepareStatement(instruccionesSQL.instruccionActualizarProductoPrecio);
			pre.setDouble(1, e.getPrecio());
			pre.setString(2, e.getCodigo());
			pre.setInt(3, e.getCategoria());
			pre.executeUpdate();
			updateCloud(e.getPrecio(), e.getCodigo(), categoriaSeleccionada);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}