package modelo;

public class preciosDocumento implements Comparable<preciosDocumento>{
	
	private int idPrecio;
	private String codigo;
    private String prod;
    private Double precio;   
    private int categoria;

     public preciosDocumento (int idPrecio, String codigo, String prod, Double d) {
    	 this.idPrecio=idPrecio;
         this.codigo=codigo;
         this.prod= prod;
         this.precio=d;       
    } 
     
     public preciosDocumento (String codigo, String prod, Double precio) {
         this.codigo=codigo;
         this.prod= prod;
         this.precio=precio;        
    } 
     public preciosDocumento (String codigo, String prod, Double precio, int categoria ) {
         this.codigo=codigo;
         this.prod= prod;
         this.precio=precio;
         this.categoria=categoria;
    } 

    public preciosDocumento() {
	}

	public int getIdPrecio() {
		return idPrecio;
	}

	public void setIdPrecio(int idPrecio) {
		this.idPrecio = idPrecio;
	}

	public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
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
	public int compareTo(preciosDocumento o) {
		if(Integer.valueOf(this.codigo)>Integer.valueOf(o.codigo)) 
		return 1;
		else {
			return -1;
		}
	}

	@Override
	public String toString() {
		return "preciosDocumento [idPrecio=" + idPrecio + ", codigo=" + codigo + ", prod=" + prod + ", precio=" + precio
				+ ", categoria=" + categoria + "]";
	}
}
