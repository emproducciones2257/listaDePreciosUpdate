package conexionBD;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class coneCone {

	
	public static Connection connect() throws SQLException {
		//HOla manola
        Connection conn = null;
        String url = "C:/sqlite/db/dbPrecios.db";
        File file = new File(url);
        
        if(file.exists()) {
        	 try {
                 conn = DriverManager.getConnection("jdbc:sqlite:"+url);                
                 
             } catch (SQLException e) {
                 System.out.println(e.getMessage() + "NO SE PUEDE CONECTAR A LA BASE");
                
             }
        }else {
        	System.out.println("NO ESTA LA BASE, ACA SE PODRIA CREAR");
        	
        	conn = DriverManager.getConnection("jdbc:sqlite:"+url);
        	
        	crearBD crear = new crearBD();
        	
        	crear.ejecutarScript();
		}
		return conn; 
    }		
}
