package modelo;

public class producto {
	
	private int idProducto;
	private descripcionArticulo descripcion;
	private int cantidad;
	private color color;
	private int idPrecio;//enlace a los datos de los precios
	private marca marca;
	private int medida;
	private int unidadDeVenta = 0;
	
	
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public descripcionArticulo getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(descripcionArticulo descripcion) {
		this.descripcion = descripcion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public color getColor() {
		return color;
	}
	public void setColor(color color) {
		this.color = color;
	}
	public int getIdPrecio() {
		return idPrecio;
	}
	public void setIdPrecio(int idPrecio) {
		this.idPrecio = idPrecio;
	}
	public marca getMarca() {
		return marca;
	}
	public void setMarca(marca marca) {
		this.marca = marca;
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
	
	@Override
	public String toString() {
		return "producto [idProducto=" + idProducto + ", descripcion=" + descripcion + ", cantidad=" + cantidad
				+ ", color=" + color + ", idPrecio=" + idPrecio + ", marca=" + marca + ", medida=" + medida
				+ ", unidadDeVenta=" + unidadDeVenta + "]";
	}
}
