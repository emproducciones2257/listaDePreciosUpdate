package conexionBD;

public class instruccionesSQL {
	
	
	// instrucciones tabla marca
	public static String instruccionRegistrarMarca = "INSERT INTO marca VALUES (NULL,?,?)";
	public static String instruccionVerificarCodigoMarca = "SELECT \"idMarca\" FROM marca WHERE \"codMarca\" =";
	public static String instruccionObtenerObjetoMarca = "SELECT * FROM marca WHERE \"codMarca\" =";
	
	//instrucciones tabla color
	public static String instruccionRegistrarColor = "INSERT INTO color VALUES (NULL,?,?,?)";
	public static String instruccionRecupereListaColores= "SELECT * FROM color WHERE \"idMarca\" = ";
	

}
