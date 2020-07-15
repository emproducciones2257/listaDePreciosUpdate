package modelo;

public class preciosCloud {
	
	private String idPrecio;
	private String idPrecioBDLocal;
    private Double precio;
    private int categoria;
    
    public preciosCloud() {
	}

	public String getIdPrecio() {
		return idPrecio;
	}

	public void setIdPrecio(String idPrecio) {
		this.idPrecio = idPrecio;
	}

	public String getIdPrecioBDLocal() {
		return idPrecioBDLocal;
	}

	public void setIdPrecioBDLocal(String idPrecioBDLocal) {
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
