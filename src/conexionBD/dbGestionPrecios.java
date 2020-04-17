package conexionBD;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.preciosDocumento;
import views.ventanasAvisos;

public class dbGestionPrecios {
	
	private PreparedStatement pre;
    private ResultSet resu; 
    private ventanasAvisos avisos;
    
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
		
		//Cargo los productos por primera vez
		
		if(listaPreciosBase.isEmpty()) {
			try {
	            pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionCargaProductoPrecio);
	            for (int i = 0; i < preciosNuevos.size(); i++) {
	            	pre.setInt(1, preciosNuevos.get(i).getCodigo());
	            	pre.setString(2, preciosNuevos.get(i).getProd());
	            	pre.setDouble(3, preciosNuevos.get(i).getPrecio());
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
			
			try {
				pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionActualizarProductoPrecio);
				
	            for (preciosDocumento nuevo : preciosNuevos) {
	            	
	            	for (preciosDocumento base : listaPreciosBase) {
						
	            		if ((base.getCodigo()==nuevo.getCodigo())&&!(nuevo.getPrecio().equals(base.getPrecio()))) {
							pre.setDouble(1, nuevo.getPrecio());
							pre.setInt(2, nuevo.getCodigo());
							pre.executeUpdate();
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
}
