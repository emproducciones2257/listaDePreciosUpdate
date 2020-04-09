package control;

import java.awt.event.*;
import conexionBD.*;
import modelo.*;
import views.pnlConsultaPrecios;
import views.ventanasAvisos;

public class controlConsultaPrecios implements ActionListener, KeyListener {
	
	private pnlConsultaPrecios pnlPrecios;
	private String codBarras="";
	private int cantidad=0;
	private int codigoMarca=0;
	private int codigoProducto=0;
	private DBMarca DBMar;
	private DBConsultaPrecio DBCPreio;
	private ventanasAvisos avisos;
	
	public controlConsultaPrecios(pnlConsultaPrecios pnlPrecios) {
		this.pnlPrecios = pnlPrecios;
		DBMar = new DBMarca();
		DBCPreio = new DBConsultaPrecio();
		pnlPrecios.getTxtBuscarCB().addKeyListener(this);
		avisos= new ventanasAvisos(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		codBarras = String.valueOf(pnlPrecios.getTxtBuscarCB().getText());
		
		cantidad++;
		
		if(cantidad==14) {
			
			codigoMarca = Integer.valueOf(codBarras.substring(3,8));
			
			codigoProducto = Integer.valueOf(codBarras.substring(8,12));
			
			System.out.println("Codigo: " + codigoMarca);
			
			marca marTemp = DBMar.obtenerMarca(codigoMarca);
			
			if (marTemp!=null) {
				
				produConPreci produ = new produConPreci();
				
				produ = DBCPreio.obtenerPrecio(marTemp.getIdMarca(),codigoProducto);
				
				if (produ!=null) {
					
					pnlPrecios.getTxtDescripcion().setText(produ.getDescri());
					pnlPrecios.getLblPrecio().setText("$ "+produ.getPrecio());
					pnlPrecios.getTxtBuscarCB().setText("");
					pnlPrecios.getTxtBuscarCB().setFocusable(true);
					cantidad=0;
					codBarras="";
					
				}
			}else {
				
				avisos.ProductoNoReg(ventanasAvisos.PRODUCTO_N_REG);
				
			}
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
}
