package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import conexionBD.DBColor;
import conexionBD.DBMarca;
import modelo.color;
import modelo.marca;
import views.pnlRegistrarProducto;

public class  controlRegistrarProducto implements KeyListener, ActionListener{
	
	private pnlRegistrarProducto pnl;
	private int cantidad = 0;
	private String codigoMarca ="";
	private String codigoProducto ="";
	public marca mar;
	private DBMarca BDmarca;
	private DBColor BDcolor;
	private ArrayList<color> coloresMarca;

	public controlRegistrarProducto(pnlRegistrarProducto pnl) {
		// TODO Auto-generated constructor stub
		this.pnl=pnl;
		BDmarca = new DBMarca();
		BDcolor = new DBColor();
		pnl.getTxtCodMarca().addKeyListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println("Marca: "+pnl.getTxtCodMarca().getText());
		System.out.println("Color: "+ coloresMarca.get(pnl.getJcbColor().getSelectedIndex()-1).getIdColor() + " - " + coloresMarca.get(pnl.getJcbColor().getSelectedIndex()-1).getNombreColor());
		pnl.getTxtCodMarca().setText("");
		pnl.getJcbColor().removeAllItems();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		codigoMarca = String.valueOf(pnl.getTxtCodMarca().getText());
		cantidad++;
		
		if (cantidad==14) {
			
			pnl.getTxtCodMarca().setText(codigoMarca.substring(3,8));
			
			codigoProducto = codigoMarca.substring(8,12);
			
			pnl.getTxtCodProducto().setText(codigoProducto);
			
			mar = obtenerMarca(Integer.valueOf(pnl.getTxtCodMarca().getText()));
					
			if(mar!=null) {

				pnl.getTxtCodMarca().setText(mar.getNombreMarca());
				
			}else {
				
				pnl.getTxtCodMarca().setText("REGISTRAR MARCA");
			}
			
			 cantidad = 0;
			 codigoMarca ="";
			 
			 cargarColoresDisponibles();
		}
	}

	private void cargarColoresDisponibles() {
		// TODO Auto-generated method stub
		
		pnl.getJcbColor().removeAllItems();
		
		coloresMarca = BDcolor.obtenerColoresMarca(mar.getIdMarca());

		pnl.getJcbColor().addItem("SIN COLOR");
		
		if (coloresMarca!=null) {
			for (color color : coloresMarca) {
				pnl.getJcbColor().addItem(color.getNombreColor());
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	marca obtenerMarca(int codigo){
		return BDmarca.obtenerMarca(codigo);
		}

}
