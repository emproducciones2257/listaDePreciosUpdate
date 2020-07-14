package control;

import java.awt.event.*;

import conexionBD.*;
import modelo.*;
import views.pnlRegistrarColor;
import views.ventanasAvisos;

public class registrarColor implements ActionListener, KeyListener{
	
	private pnlRegistrarColor pnlColor;
	private DBMarca BDmarca;
	private int cantidad = 0;
	private String codigo ="";
	public marca mar;
	private ventanasAvisos aviso;
	
	public registrarColor(pnlRegistrarColor pnlColor) {
		
		this.pnlColor=pnlColor;
		BDmarca = new DBMarca();
		aviso = new ventanasAvisos(pnlColor);
		pnlColor.gettxtScaner().addKeyListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if ((pnlColor.gettxtScaner().getText().isEmpty()) || (pnlColor.getTxtNombreColor().getText().isEmpty())) {
			
			aviso.faltanDatos(ventanasAvisos.FALTAN_DATOS);
			
			cantidad = 0;
			codigo ="";
			pnlColor.limpiarElementos();
			
		} else {
			color colTemp = new color();
			
			colTemp.setMarca(mar);
			colTemp.setNombreColor(pnlColor.getTxtNombreColor().getText());
			
			if (pnlColor.gettxtCodigoColor().getText().isEmpty()) {
				colTemp.setNroColor(0);
			}else {colTemp.setNroColor(Integer.valueOf(pnlColor.gettxtCodigoColor().getText()));}
			
			DBColor BDcolor = new DBColor();
			
			BDcolor.registrarColor(colTemp);
			
			pnlColor.limpiarElementos();
		}
	}
		
	marca obtenerMarca(String codigo){return BDmarca.obtenerMarca(codigo);}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		codigo = String.valueOf(pnlColor.gettxtScaner().getText());
		cantidad++;
		
		if (cantidad==14) {
			
			System.out.print(codigo);
			pnlColor.gettxtScaner().setText(codigo.substring(3,8));
			
			mar = obtenerMarca(pnlColor.gettxtScaner().getText());
					
			if(mar!=null) {

				pnlColor.gettxtScaner().setText(mar.getNombreMarca());
				
			}else {
				
				aviso.faltanDatos(ventanasAvisos.REGISTRAR_MARCA);
				pnlColor.limpiarElementos();
				cantidad = 0;
				codigo ="";
			}
			
			 cantidad = 0;
			 codigo ="";
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {		
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	};
}
