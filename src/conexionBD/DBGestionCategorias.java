package conexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.marca;
import views.ventanasAvisos;

public class DBGestionCategorias {
	
	private ventanasAvisos avisos;
	private PreparedStatement pre;
	private ResultSet resu;
	
	public DBGestionCategorias() {
		avisos=new ventanasAvisos(null);
	}
	

	public void cargarCategoria(String cat) {
			
		try {
			pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionRegistroCategoria);
	        pre.setString(1, cat);
	        pre.execute();
	        pre.close();
	        coneCone.connect().close();
	        avisos.cargaCorrecta(ventanasAvisos.CARGA_OK);
	        } catch (Exception e) {
	        	System.out.print("No se pudo cargar" + e.getMessage());
	        	avisos.cargaFallida(ventanasAvisos.CARGA_ERROR, e.getMessage());
	        }
		
		}
	
	public ArrayList<String> obtenerCategorias() {
		
		ArrayList<String> listaCategorias = new ArrayList<>();
		
		try {
			pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionObtenerCategorias);
			resu= pre.executeQuery();
			
			while (resu.next()) {
				martemp = new marca();
				martemp.setCodBarMarca(resu.getString("codMarca"));
				martemp.setIdMarca(resu.getInt("idMarca"));
				martemp.setNombreMarca(resu.getString("nombre"));
				
			pre.close();
			resu.close();
			coneCone.connect().close();
			}
			
		} catch (Exception e) {
			avisos.errorConsulta(ventanasAvisos.ERROR_CONSULTA, e.getMessage());
		}
		
		return martemp;
	}
	
	
}
