package conexionBD;

import java.sql.*;
import java.util.ArrayList;

import modelo.color;
import views.ventanasAvisos;

public class DBColor {
	
	 private PreparedStatement pre;
	 private ResultSet resu;
	 private ventanasAvisos avisos;
	 
	 public DBColor() {
		 avisos = new ventanasAvisos(null);
	 }
	 
	 public void registrarColor(color color) {
			
	        try {
	            pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionRegistrarColor);
	            pre.setInt(1, color.getMarca().getIdMarca());
	            pre.setInt(2, color.getNroColor());
	            pre.setString(3, color.getNombreColor());
	            pre.execute();
	            avisos.cargaCorrecta(ventanasAvisos.CARGA_OK);
	            pre.close();
	            resu.close();
	            coneCone.connect().close();
	        } catch (Exception e) {
	        	avisos.cargaFallida(ventanasAvisos.CARGA_ERROR,e.getMessage());
	        }
	    }

	@SuppressWarnings("null")
	public ArrayList<color> obtenerColoresMarca(int idMarca) {
		// TODO Auto-generated method stub
		
		ArrayList<color> coloresMarca = new ArrayList<>();
		
		try {
			pre=coneCone.connect().prepareStatement(instruccionesSQL.instruccionRecupereListaColores + idMarca);
			resu=pre.executeQuery();
			
			while (resu.next()) {
				color cTemp = new color();
				cTemp.setIdColor(resu.getInt("idColor"));
				cTemp.setNombreColor(resu.getString("nombreColor"));
				coloresMarca.add(cTemp);
			}
			  pre.close();
	           resu.close();
	           coneCone.connect().close();
		} catch (Exception e) {
			// TODO: handle exception
			avisos.errorConsulta(ventanasAvisos.ERROR_CONSULTA,e.getMessage());
		}

		return coloresMarca;
	}


}
