package conexionBD;

import java.io.BufferedReader;
import java.io.FileReader;
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

			URL fileLocation = getClass().getClassLoader().getResource("bd.sql");
			
			FileReader plantillaSQL = new FileReader(fileLocation.getFile());
			
			BufferedReader leer = new BufferedReader(plantillaSQL);
			
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
