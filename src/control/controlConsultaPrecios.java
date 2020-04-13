package control;

import java.awt.event.*;
import java.util.ArrayList;
import conexionBD.*;
import modelo.*;
import views.Principal;
import views.pnlConsultaPrecios;
import views.ventanasAvisos;

public class controlConsultaPrecios implements ActionListener, KeyListener, MouseListener {
	
	private pnlConsultaPrecios pnlPrecios;
	private String codBarras="";
	private int cantidad=0;
	private int codigoMarca=0;
	private int codigoProducto=0;
	private DBMarca DBMar;
	private DBConsultaPrecio DBCPreio;
	private ventanasAvisos avisos;
	private ArrayList<produConPreci> art;
	private int total, precio,id;
	
	public controlConsultaPrecios(pnlConsultaPrecios pnlPrecios) {
		this.pnlPrecios = pnlPrecios;
		DBMar = new DBMarca();
		DBCPreio = new DBConsultaPrecio();
		pnlPrecios.getTxtBuscarCB().addKeyListener(this);
		avisos= new ventanasAvisos(null);
		total = 0;
		art = new ArrayList<produConPreci>();
		id=0;
		this.pnlPrecios.getTblProductosVendidos().addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		art = new ArrayList<produConPreci>();
		pnlPrecios.limpiarTabla();
		pnlPrecios.limpiarComponenetes();
		id=0;
		total=0;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		codBarras = String.valueOf(pnlPrecios.getTxtBuscarCB().getText());
		
		cantidad++;
		
		if(cantidad==14) {
			
			codigoMarca = Integer.valueOf(codBarras.substring(3,8));
			
			codigoProducto = Integer.valueOf(codBarras.substring(8,12));
			
			marca marTemp = DBMar.obtenerMarca(codigoMarca);
			
			if (marTemp!=null) {
				
				produConPreci produ;
				
				produ = DBCPreio.obtenerPrecio(marTemp.getIdMarca(),codigoProducto);
				
				if (produ!=null) {
					id++;
					
					if(produ.getUniVta()!=0) {
						double temp = (double)(produ.getPrecio()/produ.getUniVta());
						precio = retornarValorPorcentaje(temp);
					}else {
						precio = retornarValorPorcentaje(produ.getPrecio());
					}
					pnlPrecios.getLblDescripcion().setText(produ.getDescri());
					pnlPrecios.getLblPrecio().setText("$ "+precio);
					pnlPrecios.getTxtBuscarCB().setText("");
					pnlPrecios.getTxtBuscarCB().setFocusable(true);
					resetearContadores();
					produConPreci temp = new produConPreci();
					temp.setId(id);
					temp.setDescri(produ.getDescri());
					temp.setCantidad(1);
					temp.setPrecio(precio);
					art.add(temp);
					total += precio;
					pnlPrecios.modeloTabla(art);
					pnlPrecios.getLblTotalParcial().setText("Total: $" + total);
				}else {
					avisos.ProductoNoReg(ventanasAvisos.PRODUCTO_N_REG);
					pnlPrecios.limpiarComponenetes();
					resetearContadores();
				}
			}else {		
				avisos.ProductoNoReg(ventanasAvisos.REGISTRAR_MARCA);
				pnlPrecios.limpiarComponenetes();;
				resetearContadores();
			}
		}
	}

	private void resetearContadores() {
		cantidad=0;
		codBarras="";
	}
	
	private int retornarValorPorcentaje(double precio) {
		
		double temp = (((precio * Principal.dtos.getPorcentaje())/100)+precio);

		return redondearPrecio(temp);
	}
	
	private int redondearPrecio(double precio) {

		String str = String.valueOf(precio);
		int intNumber = Integer.parseInt(str.substring(0, str.indexOf('.')));
		long decNumberInt = Long.parseLong(str.substring(str.indexOf('.') + 1));
		String temp = String.valueOf(decNumberInt).substring(0, 1);
		
		if(Integer.parseInt(temp)<5) {
			return intNumber;
		}else {
			return intNumber +1;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
		if(pnlPrecios.getTblProductosVendidos().getSelectedColumn()==4) {
			int indice = pnlPrecios.getTblProductosVendidos().getSelectedRow();
			int cantidad = (art.get(indice).getCantidad() + 1);
			art.get(indice).setCantidad(cantidad);
			total += art.get(indice).getPrecio();
			pnlPrecios.getLblTotalParcial().setText("Total: $" + total);
			pnlPrecios.modeloTabla(art);
		}
		
		if(pnlPrecios.getTblProductosVendidos().getSelectedColumn()==5) {
			int indice = pnlPrecios.getTblProductosVendidos().getSelectedRow();
			if(art.get(indice).getCantidad()>1) {
				int cantidad = (art.get(indice).getCantidad() - 1);
				art.get(indice).setCantidad(cantidad);
				total -= art.get(indice).getPrecio();
				pnlPrecios.getLblTotalParcial().setText("Total: $" + total);
				pnlPrecios.modeloTabla(art);
			}
		}
		
		if(pnlPrecios.getTblProductosVendidos().getSelectedColumn()==6) {
			int indice = pnlPrecios.getTblProductosVendidos().getSelectedRow();
			total -= (art.get(indice).getPrecio())*(art.get(indice).getCantidad());
			pnlPrecios.getLblTotalParcial().setText("Total: $" + total);
			art.remove(indice);
			pnlPrecios.modeloTabla(art);
		}
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
