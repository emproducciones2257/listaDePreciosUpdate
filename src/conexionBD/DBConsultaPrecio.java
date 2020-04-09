package conexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelo.marca;
import modelo.produConPreci;
import modelo.producto;

public class DBConsultaPrecio {
	
	private PreparedStatement pre;
    private ResultSet resu;
    
    
	public produConPreci obtenerPrecio(int idMarca, int codigoProducto) {
		
		produConPreci produ = new produConPreci();
		
		try {
			
			pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionConsultarPrecio);
            pre.setInt(1, codigoProducto);
            pre.setInt(2, idMarca);
            pre.execute();
            resu = pre.executeQuery();
            
            while (resu.next()) {
				produ.setDescri(resu.getString(1));
				produ.setPrecio(resu.getDouble(2));

			}
            pre.close();
            resu.close();
            coneCone.connect().close();
            
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("NOOOO " + e.getMessage());
		}

		return produ;
	} 
    
    

}
