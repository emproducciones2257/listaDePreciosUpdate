package modelo;

public class producto {
	
	private int idProducto;
	private int cantidad;
	private int idColor;
	private int idPrecio;
	private int idMarca;
	private int medida;
	private int unidadDeVenta = 0;
	private String dtosExtras;
	private String codBarr;
	
	
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getColor() {
		return idColor;
	}
	public void setColor(int color) {
		this.idColor = color;
	}
	public int getIdPrecio() {
		return idPrecio;
	}
	public void setIdPrecio(int idPrecio) {
		this.idPrecio = idPrecio;
	}
	public int getMarca() {
		return idMarca;
	}
	public void setMarca(int marca) {
		this.idMarca = marca;
	}
	public int getMedida() {
		return medida;
	}
	public void setMedida(int medida) {
		this.medida = medida;
	}
	public int getUnidadDeVenta() {
		return unidadDeVenta;
	}
	public void setUnidadDeVenta(int unidadDeVenta) {
		this.unidadDeVenta = unidadDeVenta;
	}
	public String getDtosExtras() {
		return dtosExtras;
	}
	public void setDtosExtras(String dtosExtras) {
		this.dtosExtras = dtosExtras;
	}

	public String getCodBarr() {
		return codBarr;
	}
	public void setCodBarr(String codBarr) {
		this.codBarr = codBarr;
	}
	@Override
	public String toString() {
		return "producto [idProducto=" + idProducto + ", cantidad=" + cantidad + ", idColor=" + idColor + ", idPrecio="
				+ idPrecio + ", idMarca=" + idMarca + ", medida=" + medida + ", unidadDeVenta=" + unidadDeVenta
				+ ", dtosExtras=" + dtosExtras + "]";
	}
}
