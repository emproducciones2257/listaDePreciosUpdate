package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import conexionBD.DBMarca;
import modelo.marca;
import views.pnlRegistraMarca;
import views.ventanasAvisos;

public class registrarMarca implements ActionListener, KeyListener{
	
	private pnlRegistraMarca pnl;
	private DBMarca BDmarca;
	private ventanasAvisos avisos;
	private int cantidad = 0;
	private String codigo ="";
	
	public registrarMarca(pnlRegistraMarca pnl) {
		this.pnl=pnl;
		BDmarca = new DBMarca();
		avisos = new ventanasAvisos(pnl);
		pnl.getTxtScaner().addKeyListener((this));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			marca marTemp = new marca();
			
			if((pnl.getTxtScaner().getText().isEmpty()) || (pnl.getTxtNombreMarca().getText().isEmpty())) {
				
				avisos.faltanDatos(ventanasAvisos.FALTAN_DATOS);
				
				pnl.limpiarElementos();
				cantidad = 0;
				codigo ="";
				
			}else {
				
				if(!verificarExisteCodigo(pnl.getTxtScaner().getText())) {
					marTemp.setCodBarMarca(pnl.getTxtScaner().getText());
					marTemp.setNombreMarca(pnl.getTxtNombreMarca().getText());
					
					BDmarca.registrarMarca(marTemp);
					
					pnl.limpiarElementos();		
					
				}else {
					
					avisos.datoExistente(ventanasAvisos.MARCA_REGISTRADA);					
					pnl.limpiarElementos();
				}
		}	
	}
	
	boolean verificarExisteCodigo(String codigo){return BDmarca.verificarCodigo(codigo);}

	@Override
	public void keyPressed(KeyEvent e) {
		
		codigo = String.valueOf(pnl.getTxtScaner().getText());
		cantidad++;
		
		if (cantidad==14) {
			System.out.print(codigo);
			pnl.getTxtScaner().setText(codigo.substring(3,8));
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
