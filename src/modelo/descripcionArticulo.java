package modelo;

public class descripcionArticulo {
	
	private int idDescripcion;
	private int codBarrDescripcion;
	private String descripcion;
	
	
	public descripcionArticulo() {
		
	}
	
	public descripcionArticulo(int idDescripcion, int codBarrDescripcion, String descripcion) {
		super();
		this.idDescripcion = idDescripcion;
		this.codBarrDescripcion = codBarrDescripcion;
		this.descripcion = descripcion;
	}
	
	
	public int getIdDescripcion() {
		return idDescripcion;
	}
	public void setIdDescripcion(int idDescripcion) {
		this.idDescripcion = idDescripcion;
	}
	public int getCodBarrDescripcion() {
		return codBarrDescripcion;
	}
	public void setCodBarrDescripcion(int codBarrDescripcion) {
		this.codBarrDescripcion = codBarrDescripcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
