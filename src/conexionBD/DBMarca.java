package conexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.print.attribute.DocAttribute;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;

import modelo.marca;
import views.ventanasAvisos;

public class DBMarca {
    
    private PreparedStatement pre;
    private ResultSet resu;
    private ventanasAvisos avisos;

    public DBMarca() {
		// TODO Auto-generated constructor stub
    	avisos = new ventanasAvisos(null);
	}
	
	public void registrarMarca(marca marca) {
		
        try {
            pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionRegistrarMarca);
            pre.setString(1, marca.getNombreMarca());
            pre.setString(2, marca.getCodBarMarca());
            pre.execute();
            pre.close();
            coneCone.connect().close();
            avisos.cargaCorrecta(ventanasAvisos.CARGA_OK);
        } catch (Exception e) {
        	System.out.print("No se pudo cargar" + e.getMessage());
        	avisos.cargaFallida(ventanasAvisos.CARGA_ERROR, e.getMessage());
        }
    }
	public boolean verificarCodigo(String codigo) {
		
		boolean estado = false;
		
		try {
			pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionVerificarCodigoMarca + codigo);
			resu= pre.executeQuery();
			
			while (resu.next()) {estado=true;}

			resu.close();
			pre.close();
			coneCone.connect().close();
			
		} catch (Exception e) {
			avisos.errorConsulta(ventanasAvisos.ERROR_CONSULTA, e.getMessage());
		}

		return estado;
	}

	public marca obtenerMarca(String codigo) {
		
		marca martemp = null;
		
		try {
			pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionObtenerObjetoMarca + "'"+codigo+"'");
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
