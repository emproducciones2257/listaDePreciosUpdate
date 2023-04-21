package modelo;

public class categorias {
	
	private int idCategoria;
	private String nomCat;
	
	
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getNomCat() {
		return nomCat;
	}
	public void setNomCat(String nomCat) {
		this.nomCat = nomCat;
	}
	@Override
	public String toString() {
		return "categorias [idCategoria=" + idCategoria + ", nomCat=" + nomCat + "]";
	}
}
