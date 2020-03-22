package conexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.paraBorrar;

public class prueba {
	
    private String instruccionRecuperarListadoEquiposPorCampeonato = "SELECT * FROM lotosTodos WHERE \"id\" = ";
    
    PreparedStatement pre;
    ResultSet resu;

	
	public paraBorrar recuperarEquiposPorCampeonato(Connection c, int indice) {
		
		paraBorrar temp = new paraBorrar();
        
        try {
            pre= c.prepareStatement(instruccionRecuperarListadoEquiposPorCampeonato + indice);
            resu=pre.executeQuery();

            while (resu.next()) {
               temp.setNombre(resu.getString(("nombre")));
               temp.setNumA(resu.getInt("numA"));
               temp.setNumB(resu.getInt("numB"));
               temp.setNumC(resu.getInt("numC"));
            }
            resu.close();
            c.close();
        } catch (Exception e) {
        }
        return temp;
    }

}
