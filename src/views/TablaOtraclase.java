/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.produConPreci;

public class TablaOtraclase {
    
    public void ver_tabla(JTable tabla,ArrayList<produConPreci> art){
        
        tabla.setDefaultRenderer(Object.class, new RenderTabla());
        
        String [] nombreColumnas = {"ID","Descripcion","Precio","Cantidad","Suma","Resta","Borrar"};
        
        JButton btn1 = new JButton("Sumar");
        JButton btn2 = new JButton("Restar");
        JButton btn3 = new JButton("Eliminar");

        DefaultTableModel aModel = new DefaultTableModel() {
        	public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        aModel.setColumnIdentifiers(nombreColumnas);
        
        Object O[]=null;
               		
        if(!art.isEmpty()) {
	    	 for (int i = 0; i < art.size(); i++) {
	    		 aModel.addRow(O);
	    		 aModel.setValueAt(art.get(i).getId(), i, 0);
	    		 aModel.setValueAt(art.get(i).getDescri(), i, 1);
	    		 aModel.setValueAt(art.get(i).getPrecio(), i, 2); 
	    		 aModel.setValueAt(art.get(i).getCantidad(), i, 3); 
	    		 aModel.setValueAt(btn1, i, 4);
	    		 aModel.setValueAt(btn2,i,5);
	    		 aModel.setValueAt(btn3,i,6);	 
			}
	     }

        tabla.setModel(aModel);
        
        tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
    } 
}
