package modelo;

public class modeloMSergio {

	private String articulo;
	private String precioTexto;
	private Double precio;

	public modeloMSergio() {
	}

	public modeloMSergio(String articulo, String precioTexto, Double precio) {
		super();
		this.articulo = articulo;
		this.precioTexto = precioTexto;
		this.precio = precio;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public String getPrecioTexto() {
		return precioTexto;
	}

	public void setPrecioTexto(String precioTexto) {
		this.precioTexto = precioTexto;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "modeloMSergio [articulo=" + articulo + ", precioTexto=" + precioTexto + ", precio=" + precio + "]";
	}
}
