package conexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.categorias;
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
	
	public ArrayList<categorias> obtenerCategorias() {
		
		ArrayList<categorias> listaCategorias = new ArrayList<>();
		
		try {
			pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionObtenerCategorias);
			resu= pre.executeQuery();
			
			while (resu.next()) {
				categorias cat = new categorias();
				cat.setIdCategoria(resu.getInt(1));
				cat.setNomCat(resu.getString(2));
				listaCategorias.add(cat);
			}
			
			pre.close();
			resu.close();
			coneCone.connect().close();
			
		} catch (Exception e) {
			avisos.errorConsulta(ventanasAvisos.ERROR_CONSULTA, e.getMessage());
		}
		
		return listaCategorias;
	}
}
