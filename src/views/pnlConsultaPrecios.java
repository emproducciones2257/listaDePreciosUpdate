package views;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import control.controlConsultaPrecios;
import modelo.produConPreci;

public class pnlConsultaPrecios extends JPanel{
	
    private JTextField txtBuscarCB;
    private JLabel lblPrecio;
    private JLabel lblTotalParcial;
    private JLabel lblDescripcion;
    private JScrollPane jSPTabla;
    private JTable tblProductosVendidos;
    private JButton btnNuevaVta;
    private TablaOtraclase tablaArtenativa;
    
    private String [] nombreColumnas = {"ID","Descripcion","Precio","Cantidad","SumRes","Borrar"};

	public pnlConsultaPrecios() {
		crearComponentes();
		
		//Propiedades JPanel
		
		setBackground(new Color(34, 40, 44));
        setBounds(10, 11, 849, 378);
        setLayout(null);
        
        //Agregado de componenetes
        add(txtBuscarCB);
        add(lblPrecio);
        add(lblTotalParcial);
        add(lblDescripcion);
        add(jSPTabla);
        add(btnNuevaVta);   
        
        btnNuevaVta.addActionListener(new controlConsultaPrecios(this));
        tablaArtenativa = new TablaOtraclase();
	}

	private void crearComponentes() {
		
		txtBuscarCB = new JTextField();
        txtBuscarCB.setBounds(641, 21, 146, 30);
        txtBuscarCB.setColumns(10);
        
        lblPrecio = new JLabel("$ 0");
        lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 34));
        lblPrecio.setForeground(Color.GREEN);
        lblPrecio.setBackground(Color.WHITE);
        lblPrecio.setBounds(668, 225, 146, 56);
        
        lblTotalParcial = new JLabel("TOTAL $ 0");
        lblTotalParcial.setForeground(Color.GREEN);
        lblTotalParcial.setFont(new Font("Tahoma", Font.PLAIN, 34));
        lblTotalParcial.setBackground(Color.WHITE);
        lblTotalParcial.setBounds(296, 311, 288, 56);
        
        lblDescripcion = new JLabel();
        lblDescripcion.setForeground(Color.WHITE);
        lblDescripcion.setBounds(597, 94, 242, 120);
        
        tblProductosVendidos = new JTable();

        jSPTabla = new JScrollPane();
        jSPTabla.setBounds(24, 21, 563, 285);
        jSPTabla.setViewportView(tblProductosVendidos);
        
        btnNuevaVta = new JButton("NUEVA VENTA");
        btnNuevaVta.setBounds(668, 338, 131, 23);
	}
	
	public void modeloTabla(ArrayList<produConPreci> art) {
		
		if(art.size()>1) {
			limpiarTabla();
		}
		tablaArtenativa.ver_tabla(tblProductosVendidos, art);
	}
	
	public void limpiarComponenetes () {
		getTxtBuscarCB().setText("");
		getLblPrecio().setText("$ "+0);
		getLblDescripcion().setText("");
		getLblTotalParcial().setText("Total: $ 0");
		getTxtBuscarCB().setFocusable(true);
	}
	
	public void limpiarTabla() {
			DefaultTableModel aModel = (DefaultTableModel) tblProductosVendidos.getModel();
			int a = aModel.getRowCount()-1;
			for(int i=a; i>=0;i--){
				aModel.removeRow(i);
		}
	}
	
	public JLabel getLblDescripcion() {
		return lblDescripcion;
	}

	public JTextField getTxtBuscarCB() {
		return txtBuscarCB;
	}

	public JLabel getLblPrecio() {
		return lblPrecio;
	}

	public JLabel getLblTotalParcial() {
		return lblTotalParcial;
	}

	public JScrollPane getjSPTabla() {
		return jSPTabla;
	}

	public JTable getTblProductosVendidos() {
		return tblProductosVendidos;
	}
}
