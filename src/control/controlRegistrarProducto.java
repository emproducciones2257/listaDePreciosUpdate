package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import conexionBD.DBColor;
import conexionBD.DBGestionCategorias;
import conexionBD.DBGestionProductos;
import conexionBD.DBMarca;
import conexionBD.dbGestionPrecios;
import modelo.*;
import views.pnlRegistrarProducto;
import views.ventanasAvisos;

public class  controlRegistrarProducto implements KeyListener, ActionListener, MouseListener{
	
	private pnlRegistrarProducto pnl;
	private int cantidad = 0;
	private String codigoMarca ="",codigoProducto ="", categoriaSeleccionada="";
	private marca mar;
	private DBMarca BDmarca;
	private DBColor BDcolor;
	private DBGestionCategorias DBCategorias;
	private DBGestionProductos DBGProdu;
	private ArrayList<color> coloresMarca;
	private List<preciosDocumento> prepre;
    private dbGestionPrecios DBGP;   
    private producto proTemp;
    private ventanasAvisos avisos;
    private produCloud produCloud;
    private ArrayList<categorias> categorias;
	private categorias indiceCatSelec;

	public controlRegistrarProducto(pnlRegistrarProducto pnl) {
		
		this.pnl=pnl;
		DBGP = new dbGestionPrecios();
		BDmarca = new DBMarca();
		BDcolor = new DBColor();
		DBCategorias = new DBGestionCategorias();
		proTemp = new producto();
		produCloud = new produCloud();
		DBGProdu = new DBGestionProductos();
		pnl.getTxtCodMarca().addKeyListener(this);
		pnl.getTxtBusquedPrecio().addKeyListener(this);
		pnl.getVisorDatosPrecios().addMouseListener(this);
		pnl.getcmbCategorias().addActionListener(this);	
		prepre = new ArrayList<preciosDocumento>();
		avisos = new ventanasAvisos(pnl);
		cargarCategorias();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(pnl.getBtnRegistrarProd())) {
			
			if(pnl.getTxtCodMarca().getText().isEmpty() ||
					pnl.getTxtCodMarca().getText().isEmpty() ||
					pnl.getTxtDescripcionProducto().getText().isEmpty()) {
				
				avisos.faltanDatos(ventanasAvisos.FALTAN_DATOS);
				pnl.resetearComponentes();
				
			}else {
				
				proTemp.setMarca(mar.getIdMarca());
				
				produCloud.setCodMarc(mar.getCodBarMarca());
				
				if(pnl.getJcbColor().getSelectedIndex()==0) {
					proTemp.setColor(0);
				}else { proTemp.setColor(coloresMarca.get(pnl.getJcbColor().getSelectedIndex()-1).getIdColor());}
				
				coloresMarca.clear();
				
				proTemp.setUnidadDeVenta(Integer.valueOf(pnl.getTxtUVenta().getText()));
				
				produCloud.setUnidadDeVenta(proTemp.getUnidadDeVenta());
				
				proTemp.setDtosExtras(pnl.getTxtDescripcionProducto().getText().toString());
				
				produCloud.setDtosExtras(proTemp.getDtosExtras());
				
				proTemp.setCodBarr(pnl.getTxtCodProducto().getText());
				
				produCloud.setCodProd(proTemp.getCodBarr());
				
				produCloud.setIdCategoria(indiceCatSelec.getIdCategoria());
				
				proTemp.setIdCategoria(indiceCatSelec.getIdCategoria());
				
				DBGProdu.registrarProducto(proTemp);
				
				// TODO ACA NUBE TMB DBGProdu.registrarCloud(produCloud);
				
				pnl.resetearComponentes();
			}	
		}
		
		if(e.getSource().equals(pnl.getcmbCategorias())) {
			
			categoriaSeleccionada=pnl.getcmbCategorias().getSelectedItem().toString();
			
			if(!categoriaSeleccionada.equals(constantes.VALOR_DEFECTO_CATEGORIAS)) {
				indiceCatSelec = categorias.get(pnl.getcmbCategorias().getSelectedIndex()-1);
			}else {
				
				if(pnl.getVisorDatosPrecios().getRowCount()>0){
					pnl.limpiarTabla();
				}
			}
			
			if(!categoriaSeleccionada.equals(constantes.VALOR_DEFECTO_CATEGORIAS)) {
								
				if(categoriaSeleccionada.equals("LIBRERIA")) {
					mostrarTodosPreciosPorCategorias();

				}else {
					mostrarTodosPreciosPorCategorias();
				}
			}
		}
	}
	
	private void mostrarTodosPreciosPorCategorias() {
		
		prepre = DBGP.obtenerListadoProductosPrecios(indiceCatSelec.getIdCategoria());
		
		if (!prepre.isEmpty()) {
			
			if(pnl.getVisorDatosPrecios().getRowCount()>0) {
				pnl.limpiarTabla();
			}
			pnl.modeloTabla(prepre);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getSource().equals(pnl.getTxtCodMarca())) {   
			
			codigoMarca = pnl.getTxtCodMarca().getText();
			cantidad++;
			
			if (cantidad==14) {
				
				pnl.getTxtCodMarca().setText(codigoMarca.substring(3,8));
				
				codigoProducto = codigoMarca.substring(8,12);
				
				pnl.getTxtCodProducto().setText(codigoProducto);
				
				mar = obtenerMarca(pnl.getTxtCodMarca().getText());
						
				if(mar!=null) {

					pnl.getTxtCodMarca().setText(mar.getNombreMarca());
					cargarColoresDisponibles();
					
				}else {
					
					avisos.faltanDatos(ventanasAvisos.REGISTRAR_MARCA);
					pnl.resetearComponentes();
					cantidad = 0;
					codigoMarca ="";
				}
				
				 cantidad = 0;
				 codigoMarca ="";
			}
		}
		
		if (e.getSource().equals(pnl.getTxtBusquedPrecio())) {
			
			prepre = DBGP.obtenerListadoProductosPreciosFiltrados(pnl.getTxtBusquedPrecio().getText());
			
			if (!prepre.isEmpty()) {
				pnl.limpiarTabla();
				pnl.modeloTabla(prepre);
			}else {
				pnl.limpiarTabla();
			}
		}
	}

	private void cargarColoresDisponibles() {
		
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
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	marca obtenerMarca(String codigo){
		return BDmarca.obtenerMarca(codigo);
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource().equals(pnl.getVisorDatosPrecios())) {
			
			pnl.getTxtDescripcionProducto().setText(prepre.get(pnl.getVisorDatosPrecios().getSelectedRow()).getProd());
			
			proTemp.setIdPrecio(prepre.get(pnl.getVisorDatosPrecios().getSelectedRow()).getIdPrecio());
			
			produCloud.setPrecio(Integer.valueOf(prepre.get(pnl.getVisorDatosPrecios().getSelectedRow()).getCodigo()));
		}
	}

	private void cargarCategorias() {
		categorias = DBCategorias.obtenerCategorias();
		
		if(pnl.getcmbCategorias().getItemCount()>0) {
			pnl.getcmbCategorias().removeAllItems();
		}
		
		pnl.getcmbCategorias().addItem(constantes.VALOR_DEFECTO_CATEGORIAS);
		if (!categorias.isEmpty()) {
			
			for (categorias ca : categorias) {
				pnl.getcmbCategorias().addItem(ca.getNomCat());
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

}
