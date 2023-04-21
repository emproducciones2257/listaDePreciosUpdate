package modelo;

import com.google.cloud.firestore.DocumentReference;

public class modeloMSergioProductos {
	
	private String articulo;
	private String codigo;
	private String id;
	private DocumentReference refere;


	public modeloMSergioProductos() {
	}

	public modeloMSergioProductos(String articulo, String codigo, String id) {
		super();
		this.articulo = articulo;
		this.codigo = codigo;
		this.id = id;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public DocumentReference getRefere() {
		return refere;
	}

	public void setRefere(DocumentReference refere) {
		this.refere = refere;
	}

	@Override
	public String toString() {
		return "modeloMSergioProductos [articulo=" + articulo + ", codigo=" + codigo + ", id=" + id + ", refere="
				+ refere + "]";
	}
	
	


	
}
