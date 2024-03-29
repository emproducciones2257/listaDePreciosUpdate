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
    private JTextField txtBuscarCBPerfumeria;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;

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
        add(lblNewLabel);
        add(txtBuscarCBPerfumeria);
        add(lblNewLabel_1);
        
        btnNuevaVta.addActionListener(new controlConsultaPrecios(this));
        tablaArtenativa = new TablaOtraclase();
	}

	private void crearComponentes() {
		
		txtBuscarCB = new JTextField();
        txtBuscarCB.setBounds(625, 37, 146, 30);
        txtBuscarCB.setColumns(10);
        
        lblPrecio = new JLabel("$ 0");
        lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 34));
        lblPrecio.setForeground(Color.GREEN);
        lblPrecio.setBackground(Color.WHITE);
        lblPrecio.setBounds(668, 250, 146, 56);
        
        lblTotalParcial = new JLabel("TOTAL $ 0");
        lblTotalParcial.setForeground(Color.GREEN);
        lblTotalParcial.setFont(new Font("Tahoma", Font.PLAIN, 34));
        lblTotalParcial.setBackground(Color.WHITE);
        lblTotalParcial.setBounds(296, 311, 288, 56);
        
        lblDescripcion = new JLabel();
        lblDescripcion.setForeground(Color.WHITE);
        lblDescripcion.setBounds(597, 209, 242, 42);
        
        tblProductosVendidos = new JTable();

        jSPTabla = new JScrollPane();
        jSPTabla.setBounds(24, 21, 563, 285);
        jSPTabla.setViewportView(tblProductosVendidos);
        
        btnNuevaVta = new JButton("NUEVA VENTA");
        btnNuevaVta.setBounds(668, 338, 131, 23);
        
        lblNewLabel = new JLabel("CONSULTA LIBRERIA");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel.setBounds(641, 11, 115, 14);
        
        txtBuscarCBPerfumeria = new JTextField();
        txtBuscarCBPerfumeria.setBounds(625, 110, 146, 30);
        txtBuscarCBPerfumeria.setColumns(10);
        
        lblNewLabel_1 = new JLabel("CONSULTA PERFUMERIA");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(625, 85, 146, 14);
	}
	
	public void modeloTabla(ArrayList<produConPreci> art) {
		
		if(art.size()>1) {
			limpiarTabla();
		}
		tablaArtenativa.ver_tabla(tblProductosVendidos, art);
	}
	
	public void limpiarComponenetes () {
		getTxtBuscarCB().setText("");
		getTxtBuscarCBPerfumeria().setText("");
		getLblPrecio().setText("$ "+0);
		getLblDescripcion().setText("");
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

	public JTextField getTxtBuscarCBPerfumeria() {
		return txtBuscarCBPerfumeria;
	}
}
