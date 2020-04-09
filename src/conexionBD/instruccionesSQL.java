package conexionBD;

public class instruccionesSQL {
	
	
	// instrucciones tabla marca
	public static String instruccionRegistrarMarca = "INSERT INTO marca VALUES (NULL,?,?)";
	public static String instruccionVerificarCodigoMarca = "SELECT \"idMarca\" FROM marca WHERE \"codMarca\" =";
	public static String instruccionObtenerObjetoMarca = "SELECT * FROM marca WHERE \"codMarca\" =";
	
	//instrucciones tabla color
	public static String instruccionRegistrarColor = "INSERT INTO color VALUES (NULL,?,?,?)";
	public static String instruccionRecupereListaColores= "SELECT * FROM color WHERE \"idMarca\" = ";
	
	
	//instrucciones tabla precios
	public static String instruccionRecuperarTodosProductosPrecios="SELECT \"idPreSer\", \"codigoPoducto\" ,\"descArt\", \"precio\" FROM preciosServidor";
	public static String instruccionCargaProductoPrecio="INSERT INTO preciosServidor VALUES (NULL,?,?,?)";
	public static String instruccionActualizarProductoPrecio="UPDATE preciosServidor SET precio = ? WHERE codigoPoducto =?";
	public static String instruccionRecuperarProductosFiltrados="SELECT  * FROM preciosServidor WHERE \"descArt\" LIKE ";
	
	
	//instrucciones tabla productos
	public static String instruccionRegistrarProducto="INSERT INTO producto VALUES (NULL,?,?,?,?,?,?,?,?)";
	public static String instruccionConsultarPrecio = "SELECT producto.dtosExtras, preciosServidor.precio FROM producto INNER JOIN preciosServidor ON producto.idPreSer = preciosServidor.idPreSer WHERE codBarProducto = ? AND idMarca = ?";
	
	
	
	

}
