package conexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.color;

public class DBColor {
	
	 private PreparedStatement pre;
	 private ResultSet resu;
	 
	 public void registrarColor(color color) {
		 
		 System.out.println(color.getMarca().getIdMarca());
         System.out.println(color.getNroColor());
         System.out.println(color.getNombreColor());
			
	        try {
	            pre= coneCone.connect().prepareStatement(instruccionesSQL.instruccionRegistrarColor);
	            pre.setInt(1, color.getMarca().getIdMarca());
	            pre.setInt(2, color.getNroColor());
	            pre.setString(3, color.getNombreColor());
	            pre.execute();
	            System.out.println("cargar COLOR");
	            coneCone.connect().close();
	            
	        } catch (Exception e) {
	        	System.out.println("No se pudo cargar COLOR" + e.getCause());
	        }
	    }

	@SuppressWarnings("null")
	public ArrayList<color> obtenerColoresMarca(int idMarca) {
		// TODO Auto-generated method stub
		
		ArrayList<color> coloresMarca = new ArrayList<>();
		
		try {
			pre=coneCone.connect().prepareStatement(instruccionesSQL.instruccionRecupereListaColores + idMarca);
			resu=pre.executeQuery();
			
			while (resu.next()) {
				color cTemp = new color();
				cTemp.setIdColor(resu.getInt("idColor"));
				cTemp.setNombreColor(resu.getString("nombreColor"));
				coloresMarca.add(cTemp);
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("No pude conseguir los colores" + e.getMessage());
		}

		return coloresMarca;
	}


}
