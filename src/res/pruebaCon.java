package res;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class pruebaCon {
	
	static String nombre="";
    static String cuil = "";

	public static void main(String[] args) {
		
	
		
		      URL url;
			try {
				url = new URL("https://www.dateas.com/es/consulta_cuit_cuil?name=&cuit=30068534");
				URLConnection con = url.openConnection();
				 
			      BufferedReader in = new BufferedReader(
			         new InputStreamReader(con.getInputStream()));
			     
			 
			      String linea, lineaQueQuiero = null;
			      
			      int nroLinia=0;
			      
			      
			      while ((linea = in.readLine()) != null) {
			    	  
			    	  if(nroLinia==445) {
			    		lineaQueQuiero=in.readLine();			    		
			    	  }
			         nroLinia++;
			      }
			      
			      lineaQueQuiero = eliminarTags(lineaQueQuiero);
			      
			      recuperarNombre(lineaQueQuiero);
			      
			      System.out.println(nombre);
			      System.out.println(cuil);
		      
			
			} catch (Exception e) {

				e.printStackTrace();
			}
		      
}
	private static void recuperarNombre(String lineaQueQuiero) {

		int indice= 0;
				
		lineaQueQuiero = lineaQueQuiero.substring(3,lineaQueQuiero.length()-15);
		
		int cantidadCaracteres = lineaQueQuiero.length();

		
		for (int i = 0; i < cantidadCaracteres; i++) {
			
			if ((lineaQueQuiero.charAt(i) == '0') ||
					(lineaQueQuiero.charAt(i) == '1') ||
					(lineaQueQuiero.charAt(i) == '2') ||
					(lineaQueQuiero.charAt(i) == '3') ||
					(lineaQueQuiero.charAt(i) == '4') ||
					(lineaQueQuiero.charAt(i) == '5') ||
					(lineaQueQuiero.charAt(i) == '6') ||
					(lineaQueQuiero.charAt(i) == '7') ||
					(lineaQueQuiero.charAt(i) == '8') ||
					(lineaQueQuiero.charAt(i) == '9') ||
					(lineaQueQuiero.charAt(i) == '-')) {
						
						cuil = cuil + lineaQueQuiero.charAt(i);
				
					}else {
						nombre = nombre + lineaQueQuiero.charAt(i);
					}
			
			indice++;
		}
		
		nombre = nombre.substring(0,nombre.length()-5);
	
	}
		
	public static String eliminarTags(String cadena){
	    while(true){
	      int izdaTag= cadena.indexOf('<');
	      if (izdaTag < 0 ) return cadena;
	      int derTag = cadena.indexOf('>',izdaTag);
	      if (derTag < 0) return cadena;
	      cadena= cadena.substring(0,izdaTag)+" "+ cadena.substring(derTag+1);
	    }
	  }

}
