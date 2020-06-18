package modelo;

public class dtosNecesarios {
	
	private int idDtos;
	private String fechaBD;
	private int porcentaje;
	
	public dtosNecesarios() {
		// TODO Auto-generated constructor stub
	}
	
	public dtosNecesarios(String fechaBD, int porcentaje) {
		super();
		this.fechaBD = fechaBD;
		this.porcentaje = porcentaje;
	}

	public int getIdDtos() {
		return idDtos;
	}
	public void setIdDtos(int idDtos) {
		this.idDtos = idDtos;
	}
	public String getFechaBD() {
		return fechaBD;
	}
	public void setFechaBD(String fechaBD) {
		this.fechaBD = fechaBD;
	}
	public int getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	

}
