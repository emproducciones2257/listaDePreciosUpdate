package conexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelo.producto;

public class DBGestionProductos {
	
	 private PreparedStatement pre;
	

	public void registrarProducto(producto proTemp) {
		
		 try {
	            pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionRegistrarProducto);
	            pre.setInt(1, proTemp.getMarca());
	            pre.setInt(2, proTemp.getIdPrecio());
	            pre.setInt(3, 0);
	            pre.setString(4, proTemp.getDtosExtras());
	            pre.setInt(5, proTemp.getColor());
	            pre.setInt(6, proTemp.getUnidadDeVenta());
	            pre.setInt(7, 0);
	            pre.setInt(8, proTemp.getCodBarr());
	            pre.execute();
	            coneCone.connect().close();
	        } catch (Exception e) {
	        	System.out.print("No se pudo cargar producto" + e.getMessage());
	        }

	}

}
