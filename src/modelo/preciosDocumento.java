package modelo;

public class preciosDocumento implements Comparable<preciosDocumento>{
	
	private int idPrecio;
	private int codigo;
    private String prod;
    private Double precio;   
    private Double precioPorcentaje;

    
     public preciosDocumento (int idPrecio, int c, String p, Double d) {
    	 this.idPrecio=idPrecio;
         codigo=c;
         prod= p;
         precio=d;       
    } 
     
     public preciosDocumento (int c, String p, Double d, double preCal) {
         codigo=c;
         prod= p;
         precio=d;       
         precioPorcentaje=preCal;
        
    } 

    public preciosDocumento() {
		// TODO Auto-generated constructor stub
	}

	public int getIdPrecio() {
		return idPrecio;
	}

	public void setIdPrecio(int idPrecio) {
		this.idPrecio = idPrecio;
	}

	public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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
    
    public Double getPrecioPorcentaje() {
        return precioPorcentaje;
    }

    public void setPrecioPorcentaje(Double PrecioPorcentaje) {
        this.precioPorcentaje = PrecioPorcentaje;
        
    }
    
    
    
    @Override
    public String toString (){
        
        return "Codigo: " + codigo + " Descripcion: "+ prod + " Precio: " + precio + " Precio Porcentaje: "+ precioPorcentaje;
   
    }

	@Override
	public int compareTo(preciosDocumento o) {
		if(this.codigo>o.codigo) 
		return 1;
		else {
			return -1;
		}
	}

}
