package conexionBD;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import modelo.constantes;
import modelo.dtosNecesarios;
import modelo.preciosCloud;
import views.ventanasAvisos;

public class DBDtos {
	
	private PreparedStatement pre;
	private ResultSet resu;
	private ventanasAvisos avisos;
	private DocumentReference docRef;
	private CollectionReference colecPrecios;
	private dtosNecesarios registroFechaPor;
	private String idModificar;
	
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
				dtoTemp.setFechaBD(resu.getString(2));
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

	public void actualiazarDtos(String fecha, int por) {
		
		dtosNecesarios dtoTemp = obtenerRegistro();
		if(dtoTemp==null) {

			cargaInicialDtos(fecha, por);
			
		}else {
			try {
				obtenerRegistroCloud();
				pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionUpdateDtosNece);
				pre.setString(1, fecha);
				pre.setInt(2, por);
				pre.setInt(3, dtoTemp.getIdDtos());
				pre.executeUpdate();
				
				pre.close();
				coneCone.connect().close();
				updateCloud(fecha,por);
			} catch (Exception e) {
				avisos.errorUpdate(ventanasAvisos.ERROR_UPDATE, e.getMessage());
			}
		}
	}

	private void updateCloud(String fecha, int por) {
		Map<String, Object> temporal = new HashMap<>();
		temporal.put("fechaBD", fecha);
		temporal.put("porcentaje", por);
		docRef = conectFirebase.getFirestore().collection(constantes.COLECCION_DTO_NEC).document(idModificar);
		
		if(!(registroFechaPor.getFechaBD().equals(fecha)) && !(registroFechaPor.getPorcentaje() == por)) {
			
			ApiFuture<WriteResult> writeResult = docRef.update(temporal);
			
		}else if (!(registroFechaPor.getFechaBD().equals(fecha)) && (registroFechaPor.getPorcentaje() == por)){
			
			ApiFuture<WriteResult> writeResult = docRef.update(constantes.CAMPO_FECHA_DB,fecha);
			
		}else if ((registroFechaPor.getFechaBD().equals(fecha)) && !(registroFechaPor.getPorcentaje() == por)){
			
			ApiFuture<WriteResult> writeResult = docRef.update(constantes.CAMPO_PORCEN,por);
		}
	}

	private void cargaInicialDtos(String fecha, int por) {
		try {
			pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionRegistroInicialDtos);
			pre.setString(1, fecha);
			pre.setInt(2, por);
			pre.execute();
			pre.close();
			coneCone.connect().close();
			registrarCloud(fecha,por);
		} catch (Exception e) {
			avisos.errorCargaDtos(ventanasAvisos.CARGA_ERROR, e.getMessage());
		}
	}
	
	private void registrarCloud(String fecha, int por) {
		
			dtosNecesarios temp = new dtosNecesarios(fecha,por);
			
			docRef = conectFirebase.getFirestore().collection(constantes.COLECCION_DTO_NEC).document();
			
			ApiFuture<WriteResult> insertar = docRef.create(temp);
			
			try {
				System.out.println("Update time : " + insertar.get().getUpdateTime());
			} catch (Exception e) {
				e.printStackTrace();
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
				updatePorcenCloud(porcen);
				avisos.updateCorrecta(ventanasAvisos.UPDATE_OK);
			} catch (SQLException e) {
				avisos.errorUpdate(ventanasAvisos.ERROR_UPDATE, e.getMessage());
			}
		}else {
			cargaInicialDtos("Fecha Base de datos: ", porcen);
		}
	}

	private void updatePorcenCloud(int porcen) {
		if(registroFechaPor.getPorcentaje()!=porcen) {

			docRef = conectFirebase.getFirestore().collection(constantes.COLECCION_DTO_NEC).document(idModificar);
			
			ApiFuture<WriteResult> update = docRef.update(constantes.CAMPO_PORCEN,porcen);
		}
	}

	private void obtenerRegistroCloud() {
		colecPrecios = conectFirebase.getFirestore().collection(constantes.COLECCION_DTO_NEC);
		
		ApiFuture<QuerySnapshot> response = colecPrecios.get();
		
		try {
			for (DocumentSnapshot e : response.get().getDocuments()) {
				registroFechaPor = e.toObject(dtosNecesarios.class);
				idModificar=(e.getId());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
