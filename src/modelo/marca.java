package modelo;

public class marca {
	private int idMarca;
	private int codBarMarca;
	private String nombreMarca;
	
	
	public marca() {}
	
	
	public marca(int idMarca, int codBarMarca, String nombreMarca) {
		super();
		this.idMarca = idMarca;
		this.codBarMarca = codBarMarca;
		this.nombreMarca = nombreMarca;
	}
	
	
	public int getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}
	public int getCodBarMarca() {
		return codBarMarca;
	}
	public void setCodBarMarca(int codBarMarca) {
		this.codBarMarca = codBarMarca;
	}
	public String getNombreMarca() {
		return nombreMarca;
	}
	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}


	@Override
	public String toString() {
		return "marca [idMarca=" + idMarca + ", codBarMarca=" + codBarMarca + ", nombreMarca=" + nombreMarca + "]";
	}
	
}
