package modelo;

public class produCloud {
	
	private int idProducto;
	private String Precio;
	private int unidadDeVenta = 0;
	private int idCategoria;
	private String dtosExtras;
	private String codProd;
	private String codMarc;
	private String idNube;
	
	public produCloud() {
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getPrecio() {
		return Precio;
	}

	public void setPrecio(String precio) {
		Precio = precio;
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

	public String getCodProd() {
		return codProd;
	}

	public void setCodProd(String codProd) {
		this.codProd = codProd;
	}

	public String getCodMarc() {
		return codMarc;
	}

	public void setCodMarc(String codMarc) {
		this.codMarc = codMarc;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getIdNube() {
		return idNube;
	}

	public void setIdNube(String idNube) {
		this.idNube = idNube;
	}

	@Override
	public String toString() {
		return "produCloud [idProducto=" + idProducto + ", Precio=" + Precio + ", unidadDeVenta=" + unidadDeVenta
				+ ", idCategoria=" + idCategoria + ", dtosExtras=" + dtosExtras + ", codProd=" + codProd + ", codMarc="
				+ codMarc + ", idNube=" + idNube + "]";
	}
}
