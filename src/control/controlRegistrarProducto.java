package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import conexionBD.DBColor;
import conexionBD.DBGestionProductos;
import conexionBD.DBMarca;
import conexionBD.dbGestionPrecios;
import modelo.color;
import modelo.marca;
import modelo.preciosDocumento;
import modelo.producto;
import views.pnlRegistrarProducto;
import views.ventanasAvisos;

public class  controlRegistrarProducto implements KeyListener, ActionListener, MouseListener{
	
	private pnlRegistrarProducto pnl;
	private int cantidad = 0;
	private String codigoMarca ="";
	private String codigoProducto ="";
	public marca mar;
	private DBMarca BDmarca;
	private DBColor BDcolor;
	private DBGestionProductos DBGProdu;
	private ArrayList<color> coloresMarca;
	private List<preciosDocumento> prepre;
    private dbGestionPrecios DBGP;   
    private producto proTemp;
    private ventanasAvisos avisos;


	public controlRegistrarProducto(pnlRegistrarProducto pnl) {
		// TODO Auto-generated constructor stub
		
		this.pnl=pnl;
		DBGP = new dbGestionPrecios();
		BDmarca = new DBMarca();
		BDcolor = new DBColor();
		proTemp = new producto();
		DBGProdu = new DBGestionProductos();
		pnl.getTxtCodMarca().addKeyListener(this);
		pnl.getTxtBusquedPrecio().addKeyListener(this);
		pnl.getVisorDatosPrecios().addMouseListener(this);
		prepre = new ArrayList<preciosDocumento>();
		avisos = new ventanasAvisos(pnl);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(pnl.getTxtCodMarca().getText().isEmpty() ||
				pnl.getTxtCodMarca().getText().isEmpty() ||
				pnl.getTxtDescripcionProducto().getText().isEmpty()) {
			
			avisos.faltanDatos(ventanasAvisos.FALTAN_DATOS);
			pnl.resetearComponentes();
			
		}else {
			
			proTemp.setMarca(mar.getIdMarca());
			
			if(pnl.getJcbColor().getSelectedIndex()==0) {
				proTemp.setColor(0);
			}else { proTemp.setColor(coloresMarca.get(pnl.getJcbColor().getSelectedIndex()-1).getIdColor());}
			
			coloresMarca.clear();
			
			proTemp.setUnidadDeVenta(Integer.valueOf(pnl.getTxtUVenta().getText()));
			
			proTemp.setDtosExtras(pnl.getTxtDescripcionProducto().getText().toString());
			
			proTemp.setCodBarr(Integer.valueOf(pnl.getTxtCodProducto().getText()));
			
			DBGProdu.registrarProducto(proTemp);
			
			pnl.resetearComponentes();
		}	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource().equals(pnl.getTxtCodMarca())) {   
			
			codigoMarca = String.valueOf(pnl.getTxtCodMarca().getText());
			cantidad++;
			
			if (cantidad==14) {
				
				pnl.getTxtCodMarca().setText(codigoMarca.substring(3,8));
				
				codigoProducto = codigoMarca.substring(8,12);
				
				pnl.getTxtCodProducto().setText(codigoProducto);
				
				mar = obtenerMarca(Integer.valueOf(pnl.getTxtCodMarca().getText()));
						
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		pnl.getTxtDescripcionProducto().setText(prepre.get(pnl.getVisorDatosPrecios().getSelectedRow()).getProd());
		
		proTemp.setIdPrecio(prepre.get(pnl.getVisorDatosPrecios().getSelectedRow()).getIdPrecio());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
