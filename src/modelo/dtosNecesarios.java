package modelo;

public class dtosNecesarios {
	
	private int idDtos;
	private String fechaDB;
	private int porcentaje;
	
	public dtosNecesarios() {
		// TODO Auto-generated constructor stub
	}
	
	public dtosNecesarios(String fechaDB, int porcentaje) {
		super();
		this.fechaDB = fechaDB;
		this.porcentaje = porcentaje;
	}

	public int getIdDtos() {
		return idDtos;
	}
	public void setIdDtos(int idDtos) {
		this.idDtos = idDtos;
	}
	public String getFechaDB() {
		return fechaDB;
	}
	public void setFechaDB(String fechaDB) {
		this.fechaDB = fechaDB;
	}
	public int getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	

}
