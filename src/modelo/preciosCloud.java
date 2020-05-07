package modelo;

public class preciosCloud {
	
	private String idPrecio;
	private int idPrecioBDLocal;
    private Double precio;
    
    public preciosCloud() {
		// TODO Auto-generated constructor stub
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

	@Override
	public String toString() {
		return "preciosCloud [idPrecio=" + idPrecio + ", idPrecioBDLocal=" + idPrecioBDLocal + ", precio=" + precio
				+ "]";
	}
}
