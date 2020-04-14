package conexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.dtosNecesarios;
import views.ventanasAvisos;

public class DBDtos {
	
	private PreparedStatement pre;
	private ResultSet resu;
	private ventanasAvisos avisos;
	
	public DBDtos() {
		avisos = new ventanasAvisos(null);
	}
	 
	public dtosNecesarios obtenerRegistro() {
		dtosNecesarios dtoTemp = null;
		
		try {
			pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionObtenerRegistrosDtos);
			resu= pre.executeQuery();
			
			while (resu.next()) {
				dtoTemp = new dtosNecesarios();
				dtoTemp.setIdDtos(resu.getInt(1));
				dtoTemp.setFechaDB(resu.getString(2));
				dtoTemp.setPorcentaje(resu.getInt(3));
			}
			pre.close();
			resu.close();
			coneCone.connect().close();
			
		} catch (Exception e) {
			avisos.errorConsulta(ventanasAvisos.ERROR_CONSULTA, e.getMessage());
		}
		return dtoTemp;
	}

	public void actualiazarDtos(String string, int por) {
		
		dtosNecesarios dtoTemp = obtenerRegistro();
		
		if(dtoTemp==null) {
			
			cargaInicialDtos(string, por);
			
		}else {
			try {
				pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionUpdateDtosNece);
				pre.setString(1, string);
				pre.setInt(2, por);
				pre.setInt(3, dtoTemp.getIdDtos());
				pre.executeUpdate();
				
				pre.close();
				coneCone.connect().close();
			} catch (Exception e) {
				avisos.errorUpdate(ventanasAvisos.ERROR_UPDATE, e.getMessage());
				System.out.print("YOOOOOOOOOOOOOOOOOOOOO");
			}
		}
	}

	public void cargaInicialDtos(String string, int por) {
		try {
			pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionRegistroInicialDtos);
			pre.setString(1, string);
			pre.setInt(2, por);
			pre.execute();
			pre.close();
			coneCone.connect().close();
		} catch (Exception e) {
			avisos.errorCargaDtos(ventanasAvisos.CARGA_ERROR, e.getMessage());
		}
	}
	
	public void actualiazarPorcentaje(int porcen) {
		dtosNecesarios dtoTemp = obtenerRegistro();
		
		if(dtoTemp!=null) {
			try {
				pre = coneCone.connect().prepareStatement(instruccionesSQL.instruccionUpdatePorcentaje);
				pre.setInt(1, porcen);
				pre.setInt(2, dtoTemp.getIdDtos());
				pre.executeUpdate();
				
				pre.close();
				resu.close();
				coneCone.connect().close();
				avisos.updateCorrecta(ventanasAvisos.UPDATE_OK);
			} catch (SQLException e) {
				avisos.errorUpdate(ventanasAvisos.ERROR_UPDATE, e.getMessage());
			}
		}else {
			cargaInicialDtos("Fecha Base de datos: ", porcen);
		}
	
	}
}
