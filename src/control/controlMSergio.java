package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import conexionBD.dBMSergio;
import modelo.modeloMSergio;
import modelo.modeloMSergioProductos;
import views.PnlMauroSergio;
import views.ventanasAvisos;

public class controlMSergio implements ActionListener{
	
	private PnlMauroSergio pnlMaurito;
	private dBMSergio dbSergito;
	private ventanasAvisos avisos;
	
	public controlMSergio(PnlMauroSergio pnlMaurito) {
		this.pnlMaurito = pnlMaurito;
		pnlMaurito.getBtnBuscar().addActionListener(this);
		pnlMaurito.getBtnCargarMaurito().addActionListener(this);
		pnlMaurito.getBtnActualizar().addActionListener(this);
		dbSergito = new dBMSergio(pnlMaurito);
		avisos = new ventanasAvisos(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(pnlMaurito.getBtnBuscar())) {
			
			buscar();
		}
			
		if(e.getSource().equals(pnlMaurito.getBtnCargarMaurito())) {

			cargaOUpdate(pnlMaurito.getBtnCargarMaurito());
			
		}
		
		if(e.getSource().equals(pnlMaurito.getBtnRegistrar())) {
			
			registrar();
		}
		
		if(e.getSource().equals(pnlMaurito.getBtnActualizar())) {
						
			cargaOUpdate(pnlMaurito.getBtnActualizar());	
		}
	}

	private void cargaOUpdate(JButton btnEvento) {
		
		
		if(btnEvento.getText().equals(pnlMaurito.getBtnActualizar().getText())) {
			
			if(camposVacios()) {
				modeloMSergio modelo = new modeloMSergio(pnlMaurito.getTxtArticuloPrecio().getText(),
						pnlMaurito.getTxtPrecioTexto().getText(),
						Double.valueOf(pnlMaurito.getTxtPrecio().getText()));
				
				dbSergito.updateRegistro(modelo);
			}else avisos.faltanDatos(ventanasAvisos.FALTAN_DATOS);
			
		}
		
		if(btnEvento.getText().equals(pnlMaurito.getBtnCargarMaurito().getText())) {
			
			if(camposVaciosProductos()) {
				
				modeloMSergioProductos productoMSergio = new modeloMSergioProductos(pnlMaurito.getTxtArticuloProducto().getText(),
						pnlMaurito.getTxtCodigo().getText(), null);
				
				dbSergito.cargarProductoMSergio(productoMSergio);
			}else avisos.faltanDatos(ventanasAvisos.FALTAN_DATOS);
		}
	}

	
	private void registrar() {
		
		if(camposVacios()){
			
			modeloMSergio sergito = new modeloMSergio(
					pnlMaurito.getTxtArticuloPrecio().getText(),
					pnlMaurito.getTxtPrecioTexto().getText(),
					Double.parseDouble(pnlMaurito.getTxtPrecio().getText()));
			
			dbSergito.cargarRegistroPrecio(sergito);
			
		}else avisos.faltanDatos(ventanasAvisos.FALTAN_DATOS);
	}

	private void buscar() {
		
		if(!(pnlMaurito.getTxtArticuloPrecio().getText().equals(""))) {
			
			modeloMSergio sergito = dbSergito.buscarPorArticulo(pnlMaurito.getTxtArticuloPrecio().getText(),true);
			
			if(!(sergito==null)) {
				pnlMaurito.cargarDatos(sergito);
				sergito = new modeloMSergio();
			}else {
				pnlMaurito.limpiarCargaPrecio();

			}
			
		}else {
			avisos.faltanDatos(ventanasAvisos.ARTICULO_VACIO);
		}			
	}
	
	private boolean camposVaciosProductos() {
		if(pnlMaurito.getTxtArticuloProducto().getText().equals("")||
				pnlMaurito.getTxtCodigo().getText().equals("")) return false;
		else return true;
	}
	
	private boolean camposVacios() {
		
		if(pnlMaurito.getTxtArticuloPrecio().getText().equals("")||
				(pnlMaurito.getTxtPrecio().getText().equals(""))||
				(pnlMaurito.getTxtPrecioTexto().getText().equals(""))) return false;
		else return true;
	}
}
