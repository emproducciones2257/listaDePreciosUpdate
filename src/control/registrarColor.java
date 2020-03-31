package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import conexionBD.DBColor;
import conexionBD.DBMarca;
import modelo.color;
import modelo.marca;
import views.pnlRegistrarColor;

public class registrarColor implements ActionListener, KeyListener{
	
	private pnlRegistrarColor pnlColor;
	private DBMarca BDmarca;
	private int cantidad = 0;
	private String codigo ="";
	public marca mar;
	
	public registrarColor(pnlRegistrarColor pnlColor) {
		
		this.pnlColor=pnlColor;
		BDmarca = new DBMarca();
		
		pnlColor.gettxtScaner().addKeyListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		
		
		if (!pnlColor.gettxtScaner().getText().equals("REGISTRAR MARCA")) {
		
			color colTemp = new color();
			
			colTemp.setMarca(mar);
			colTemp.setNombreColor(pnlColor.getTxtNombreColor().getText());
			if (pnlColor.gettxtCodigoColor().getText().isEmpty()) {
				colTemp.setNroColor(0);
			}else {colTemp.setNroColor(Integer.valueOf(pnlColor.gettxtCodigoColor().getText()));}
			
			DBColor BDcolor = new DBColor();
			
			BDcolor.registrarColor(colTemp);
			
			pnlColor.gettxtScaner().setText("");
			pnlColor.gettxtCodigoColor().setText("");
			pnlColor.getTxtNombreColor().setText("");
			
		}else {
			
			pnlColor.gettxtScaner().setText("");
			pnlColor.gettxtCodigoColor().setText("");
			pnlColor.getTxtNombreColor().setText("");
			System.out.print("NO SE PUDO REGISTRAR, REGISTRAR MARCA");
		}

	}
		
	marca obtenerMarca(int codigo){
		return BDmarca.obtenerMarca(codigo);
		}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		codigo = String.valueOf(pnlColor.gettxtScaner().getText());
		cantidad++;
		
		if (cantidad==14) {
			
			System.out.print(codigo);
			pnlColor.gettxtScaner().setText(codigo.substring(3,8));
			
			mar = obtenerMarca(Integer.valueOf(pnlColor.gettxtScaner().getText()));
					
			if(mar!=null) {

				pnlColor.gettxtScaner().setText(mar.getNombreMarca());
				
			}else {
				
				pnlColor.gettxtScaner().setText("REGISTRAR MARCA");
			}
			
			 cantidad = 0;
			 codigo ="";
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	};

}
