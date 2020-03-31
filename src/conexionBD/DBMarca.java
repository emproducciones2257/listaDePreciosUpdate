package conexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.marca;

public class DBMarca {
    
    private PreparedStatement pre;
    private ResultSet resu;

	
	public void registrarMarca(marca marca) {
		
        try {
            pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionRegistrarMarca);
            pre.setString(1, marca.getNombreMarca());
            pre.setInt(2, marca.getCodBarMarca());
            pre.execute();
            coneCone.connect().close();
        } catch (Exception e) {
        	System.out.print("No se pudo cargar" + e.getMessage());
        }
    }


	public boolean verificarCodigo(int codigo) {
		
		boolean estado = false;
		
		try {
			pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionVerificarCodigoMarca + codigo);
			resu= pre.executeQuery();
			
			while (resu.next()) {estado=true;}

			resu.close();
			coneCone.connect().close();
			
		} catch (Exception e) {
			System.out.print("No se consultar el codigo" + e.getMessage());
		}

		return estado;
	}


	public marca obtenerMarca(int codigo) {
		
		marca martemp = null;
		
		try {
			pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionObtenerObjetoMarca + codigo);
			resu= pre.executeQuery();
			
			while (resu.next()) {
				martemp = new marca();
				martemp.setCodBarMarca(resu.getInt("codMarca"));
				martemp.setIdMarca(resu.getInt("idMarca"));
				martemp.setNombreMarca(resu.getString("nombre"));
				
			resu.close();
			coneCone.connect().close();
			}
			
		} catch (Exception e) {
			System.out.print("No se puedo recuperar la marca" + e.getMessage());
		}
		
		// TODO Auto-generated method stub
		return martemp;
	}
	
}
