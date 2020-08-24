package res;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class pruebaCon {

	public static void main(String[] args) {
		
		      URL url;
			try {
				url = new URL("https://www.casajujuy.com/libreria.pdf");
				URLConnection con = url.openConnection();
				 
			      BufferedReader in = new BufferedReader(
			         new InputStreamReader(con.getInputStream()));
			 
			      String linea;
			      while ((linea = in.readLine()) != null) {
			         System.out.println(linea);
			      }
			   
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      
}

}
