package views;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import conexionBD.dbGestionPrecios;
import control.controlGestionPrecios;
import modelo.preciosDocumento;

public class pnlGestionPrecios extends JPanel{
	
	private JButton btnBuscarArchivo;
	private JLabel lblEstadoArchivo;
	private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JTextField txtPorcentaje;
    private JLabel lblNewLabel_3;
    private JButton btnProcesar;
    private JTable tblListadoPrecios;
	private JLabel lblNewLabel_4;
    private JTextField txtIdProducto;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private JButton btnRegistrarPrecio;
    private DefaultTableModel aModel;
    private JScrollPane pnlTabla;
    private dbGestionPrecios DBGP;
    private String [] nombreColumnas = {"ID","Descripcion","Precio"};
    
	public pnlGestionPrecios() {
		
		DBGP = new dbGestionPrecios();
		crearComponentes();
		modeloTabla();
		//propiedades JPanel
		setBackground(new java.awt.Color(34, 40, 44));
		setBounds(10, 11, 849, 378);;
		setLayout(null);
		
		add(btnBuscarArchivo);
		add(lblEstadoArchivo);
		add(lblNewLabel);
		add(lblNewLabel_1);
		add(lblNewLabel_2);
		add(txtPorcentaje);
		add(lblNewLabel_3);
		add(btnProcesar);
		add(pnlTabla);
		add(lblNewLabel_4);
		add(txtIdProducto);
		add(lblNewLabel_5);
		add(txtDescripcion);
		add(lblNewLabel_6);
		add(txtPrecio);
		add(btnRegistrarPrecio);
		
		btnBuscarArchivo.addActionListener(new controlGestionPrecios(this));
	}
	
	private void crearComponentes() {
		// TODO Auto-generated method stub
		
		btnBuscarArchivo = new JButton("PDF");
	    btnBuscarArchivo.setBounds(33, 29, 64, 23);
	        
	    lblEstadoArchivo = new JLabel("Sin Archivo");
	    lblEstadoArchivo.setForeground(Color.WHITE);
	    lblEstadoArchivo.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lblEstadoArchivo.setBounds(168, 32, 85, 14);
	    
	    lblNewLabel = new JLabel("Buscar Archivo");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(23, 4, 85, 14);
        
        lblNewLabel_1 = new JLabel("Estado Archivo");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(158, 4, 85, 14);
        
        lblNewLabel_2 = new JLabel("Porcentaje");
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_2.setBounds(307, 4, 85, 14);
        
        txtPorcentaje = new JTextField();
        txtPorcentaje.setBounds(296, 30, 113, 30);
        txtPorcentaje.setColumns(10);
        
        lblNewLabel_3 = new JLabel("Procesar");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_3.setForeground(Color.WHITE);
        lblNewLabel_3.setBounds(492, 4, 46, 14);
        
        btnProcesar = new JButton("Procesar Archivo");
        btnProcesar.setBounds(461, 29, 119, 23);
        
        tblListadoPrecios = new JTable();
        
        pnlTabla = new JScrollPane(tblListadoPrecios);
        pnlTabla.setBounds(23, 79, 794, 191);
        pnlTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        lblNewLabel_4 = new JLabel("ID");
        lblNewLabel_4.setForeground(Color.WHITE);
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_4.setBounds(23, 294, 34, 14);
        
        txtIdProducto = new JTextField();
        txtIdProducto.setBounds(53, 292, 86, 30);
        
        lblNewLabel_5 = new JLabel("Descripcion");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_5.setForeground(Color.WHITE);
        lblNewLabel_5.setBounds(212, 295, 72, 14);
        
        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(307, 292, 435, 30);
        
        lblNewLabel_6 = new JLabel("Precio");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_6.setForeground(Color.WHITE);
        lblNewLabel_6.setBounds(23, 338, 46, 14);
        
        txtPrecio = new JTextField();
        txtPrecio.setBounds(67, 336, 86, 30);
        
        btnRegistrarPrecio = new JButton("REGISTRAR PRECIO");
        btnRegistrarPrecio.setBounds(491, 335, 174, 23);
	}

	public void modeloTabla() {
		
		Object O[]=null;
		List<preciosDocumento> lista;
		 aModel = new DefaultTableModel();
		 aModel = (DefaultTableModel) tblListadoPrecios.getModel();
	     aModel.setColumnIdentifiers(nombreColumnas);
	     lista = DBGP.obtenerListadoProductosPrecios();
	     
	     if(!lista.isEmpty()) {
	    	 for (int i = 0; i < lista.size(); i++) {
	    		 aModel.addRow(O);
	    		 aModel.setValueAt(lista.get(i).getCodigo(), i, 0);
	    		 aModel.setValueAt(lista.get(i).getProd(), i, 1);
	    		 aModel.setValueAt(lista.get(i).getPrecio(), i, 2); 
			}
	     }
	    
	     tblListadoPrecios.setModel(aModel);
	}

	public JButton getBtnBuscarArchivo() {
		return btnBuscarArchivo;
	}

	public JLabel getLblEstadoArchivo() {
		return lblEstadoArchivo;
	}

	public JTextField getTxtPorcentaje() {
		return txtPorcentaje;
	}

	public JButton getBtnProcesar() {
		return btnProcesar;
	}

	public JTable getTblListadoPrecios() {
		return tblListadoPrecios;
	}

	public JTextField getTxtIdProducto() {
		return txtIdProducto;
	}

	public JTextField getTxtDescripcion() {
		return txtDescripcion;
	}

	public JTextField getTxtPrecio() {
		return txtPrecio;
	}

	public JButton getBtnRegistrarPrecio() {
		return btnRegistrarPrecio;
	}

}
