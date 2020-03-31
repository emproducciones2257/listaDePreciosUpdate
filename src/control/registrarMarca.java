package control;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import conexionBD.DBMarca;
import conexionBD.coneCone;
import modelo.marca;
import views.pnlRegistraMarca;

public class registrarMarca implements ActionListener{
	
	private pnlRegistraMarca pnl;
	private DBMarca BDmarca;
	
	public registrarMarca(pnlRegistraMarca pnl) {
		this.pnl=pnl;
		BDmarca = new DBMarca();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			marca marTemp = new modelo.marca();
			
			if(!verificarExisteCodigo(Integer.valueOf(pnl.getTxtScaner().getText()))) {
				marTemp.setCodBarMarca(Integer.valueOf(pnl.getTxtScaner().getText()));
				marTemp.setNombreMarca(pnl.getTxtNombreMarca().getText());
				
				BDmarca.registrarMarca(marTemp);
				
				pnl.getTxtNombreMarca().setText("");
				pnl.getTxtScaner().setText("");
				
			}else {
				System.out.println("CODIGO DE MARCA EXISTENTE");
				pnl.getTxtScaner().setText("");
			}
			
	}
	
	boolean verificarExisteCodigo(int codigo){return BDmarca.verificarCodigo(codigo);};

}
