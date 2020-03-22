package modelo;

public class paraBorrar {
	
	private String nombre;
	private int numA;
	private int numB;
	private int numC;
	
	
	public paraBorrar(String nombre, int numA, int numB, int numC) {
		super();
		this.nombre = nombre;
		this.numA = numA;
		this.numB = numB;
		this.numC = numC;
	}
	
	public paraBorrar() {
		
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumA() {
		return numA;
	}
	public void setNumA(int numA) {
		this.numA = numA;
	}
	public int getNumB() {
		return numB;
	}
	public void setNumB(int numB) {
		this.numB = numB;
	}
	public int getNumC() {
		return numC;
	}
	public void setNumC(int numC) {
		this.numC = numC;
	}
	
	
	

}
