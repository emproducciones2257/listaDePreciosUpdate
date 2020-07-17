package modelo;

public class preCloLibre {
	
	private String idPrecio;
	private int idPrecioBDLocal;
    private Double precio;
    private int categoria;
    
    public preCloLibre() {
	}
	public String getIdPrecio() {
		return idPrecio;
	}

	public void setIdPrecio(String idPrecio) {
		this.idPrecio = idPrecio;
	}

	public int getIdPrecioBDLocal() {
		return idPrecioBDLocal;
	}

	public void setIdPrecioBDLocal(int idPrecioBDLocal) {
		this.idPrecioBDLocal = idPrecioBDLocal;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "preciosCloud [idPrecio=" + idPrecio + ", idPrecioBDLocal=" + idPrecioBDLocal + ", precio=" + precio
				+ ", categoria=" + categoria + "]";
	}
}
