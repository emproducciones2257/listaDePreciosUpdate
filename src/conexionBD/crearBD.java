package conexionBD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class crearBD {
	
    PreparedStatement pre;
    ResultSet resu;
    
    
    private void ejecutarSentencia(String sentencia) {
		
        
        try {
            pre= coneCone.connect().prepareStatement(sentencia);
            pre.execute();

        } catch (Exception e) {
        }
    }
    
    public void ejecutarScript() {
    	
    	String var="";
    	String temp ="";
    	
		try {
			
			InputStream input = getClass().getResourceAsStream("/res/bd.sql");
			
			@SuppressWarnings("resource")
			BufferedReader leer = new BufferedReader(new InputStreamReader(input));
			
			while (leer.ready()) {
				
				temp = leer.readLine();
				
				if(temp.equals(";")) {
					
					ejecutarSentencia(var);
					var= "";
			    	temp = "";
					
				}else var += temp;

			}

			System.out.println("Base creada correctamente");
			
		}catch (Exception e) {
			e.getMessage();
			
			System.out.println("no se encontro el archivo");

		}
	}

}
