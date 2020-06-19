package conexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

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
		// TODO Auto-generated method stub
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

	public void cargaInicialDtos(String fecha, int por) {
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
		// TODO Auto-generated method stub
		
			dtosNecesarios temp = new dtosNecesarios(fecha,por);
			
			docRef = conectFirebase.getFirestore().collection(constantes.COLECCION_DTO_NEC).document();
			
			ApiFuture<WriteResult> insertar = docRef.create(temp);
			
			try {
				System.out.println("Update time : " + insertar.get().getUpdateTime());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}

	public void actualiazarPorcentaje(int porcen) {
		dtosNecesarios dtoTemp = obtenerRegistro();
		obtenerRegistroCloud();
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
		// TODO Auto-generated method stub
		if(registroFechaPor.getPorcentaje()!=porcen) {

			docRef = conectFirebase.getFirestore().collection(constantes.COLECCION_DTO_NEC).document(idModificar);
			
			ApiFuture<WriteResult> update = docRef.update(constantes.CAMPO_PORCEN,porcen);
			
			System.out.println("ACTUALIZO CODIGO: ");
		}
	}

	private void obtenerRegistroCloud() {
		// TODO Auto-generated method stub
		colecPrecios = conectFirebase.getFirestore().collection(constantes.COLECCION_DTO_NEC);
		
		ApiFuture<QuerySnapshot> response = colecPrecios.get();
		
		try {
			for (DocumentSnapshot e : response.get().getDocuments()) {
				registroFechaPor = e.toObject(dtosNecesarios.class);
				idModificar=(e.getId());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
