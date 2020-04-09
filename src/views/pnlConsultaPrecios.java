package views;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import control.controlConsultaPrecios;

public class pnlConsultaPrecios extends JPanel{
	
    private JTextField txtBuscarCB;
    private JLabel lblPrecio;
    private JLabel lblTotalParcial;
    private JTextArea txtDescripcion;
    private JScrollPane jSPTabla;
    private JTable tblProductosVendidos;
    private JButton btnNuevaVta;

	public pnlConsultaPrecios() {
		// TODO Auto-generated constructor stub

		crearComponentes();
		
		//Propiedades JPanel
		
		setBackground(new Color(34, 40, 44));
        setBounds(10, 11, 849, 378);
        setLayout(null);
        
        //Agregado de componenetes
        add(txtBuscarCB);
        add(lblPrecio);
        add(lblTotalParcial);
        add(txtDescripcion);
        add(jSPTabla);
        add(btnNuevaVta);   
        
        btnNuevaVta.addActionListener(new controlConsultaPrecios(this));
	}

	private void crearComponentes() {
		// TODO Auto-generated method stub
		
		txtBuscarCB = new JTextField();
        txtBuscarCB.setBounds(668, 21, 146, 30);
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
        lblTotalParcial.setBounds(406, 311, 178, 56);
        
        txtDescripcion = new JTextArea();
        txtDescripcion.setBounds(668, 94, 146, 90);
        
        tblProductosVendidos = new JTable();
        
        jSPTabla = new JScrollPane();
        jSPTabla.setBounds(24, 21, 563, 285);
        jSPTabla.setViewportView(tblProductosVendidos);
        
        btnNuevaVta = new JButton("NUEVA VENTA");
        btnNuevaVta.setBounds(683, 334, 131, 23);
	}

	public JTextArea getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(JTextArea txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
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
