package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import conexionBD.DBGestionCategorias;
import views.pnlGestionCategoria;
import views.ventanasAvisos;

public class gestionCategorias implements ActionListener {
	
	private pnlGestionCategoria pnlGestionCategoria;
	private ventanasAvisos avisos;
	private DBGestionCategorias DBCategorias;

	public gestionCategorias(pnlGestionCategoria pnlGestionCategoria) {
		this.pnlGestionCategoria = pnlGestionCategoria;
		avisos = new ventanasAvisos(pnlGestionCategoria);
		DBCategorias = new DBGestionCategorias();
		pnlGestionCategoria.getBtnCancelar().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(pnlGestionCategoria.getBtnCargarCategoria())) {
			String cat = pnlGestionCategoria.getTxtNombCategoria().getText();
			if(!cat.equals("")) {
				DBCategorias.cargarCategoria(cat);
				pnlGestionCategoria.resetearComponente();
			}else {avisos.faltanDatos(avisos.FALTAN_DATOS);}

		}else {
			pnlGestionCategoria.resetearComponente();
		}
	}
}
