package conexionBD;

import java.sql.PreparedStatement;

import modelo.marca;
import views.ventanasAvisos;

public class DBGestionCategorias {
	
	private ventanasAvisos avisos;
	private PreparedStatement pre;
	
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
}
