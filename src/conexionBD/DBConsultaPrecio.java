package conexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelo.marca;
import modelo.produConPreci;
import modelo.producto;
import views.ventanasAvisos;

public class DBConsultaPrecio {
	
	private PreparedStatement pre;
    private ResultSet resu;
    private ventanasAvisos avisos;
    
    public DBConsultaPrecio() {
    	avisos = new ventanasAvisos(null);
	}
    
	public produConPreci obtenerPrecio(int idMarca, String codigoProducto, int codCat) {
		produConPreci produ = null;
		
		try {
			
			pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionConsultarPrecio
														+"'"+codigoProducto+"' AND idMarca =" + idMarca +" AND producto.idCat = "
														+ codCat);
            pre.execute();
            resu = pre.executeQuery();
            
            while (resu.next()) {
            	produ = new produConPreci();
				produ.setDescri(resu.getString(1));
				produ.setUniVta(resu.getInt(2));
				produ.setPrecio(resu.getDouble(3));
			}
            pre.close();
            resu.close();
            coneCone.connect().close();
            
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("NOOOO " + e.getMessage());
			avisos.errorConsulta(ventanasAvisos.ERROR_CONSULTA, e.getMessage());
		}

		return produ;
	} 
}
