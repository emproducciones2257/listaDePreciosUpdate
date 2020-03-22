package modelo;

public class color {
	
	private int idColor;
	private marca marca;
	private String nombreColor;
	private int nroColor;
	
	
	public int getIdColor() {
		return idColor;
	}
	public void setIdColor(int idColor) {
		this.idColor = idColor;
	}
	public marca getMarca() {
		return marca;
	}
	public void setMarca(marca marca) {
		this.marca = marca;
	}
	public String getNombreColor() {
		return nombreColor;
	}
	public void setNombreColor(String nombreColor) {
		this.nombreColor = nombreColor;
	}
	public int getNroColor() {
		return nroColor;
	}
	public void setNroColor(int nroColor) {
		this.nroColor = nroColor;
	}
	@Override
	public String toString() {
		return "color [idColor=" + idColor + ", marca=" + marca + ", nombreColor=" + nombreColor + ", nroColor="
				+ nroColor + "]";
	}

}
