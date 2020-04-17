package views;

import java.awt.*;
import java.util.ArrayList;
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
    private JTextArea txtDescripcion;
    private JTextField txtPrecio;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private JButton btnRegistrarPrecio;
    private DefaultTableModel aModel;
    private JScrollPane pnlTabla;
    private dbGestionPrecios DBGP;
    private String [] nombreColumnas = {"ID","Descripcion","Precio"};
    private JLabel lblNewLabel_7;
    private JTextField txtFiltrarProducto;
    private List<preciosDocumento> lista;
    private JScrollPane scrollPane;
    
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
		add(lblNewLabel_6);
		add(txtPrecio);
		add(btnRegistrarPrecio);
		add(txtFiltrarProducto);
		add(lblNewLabel_7);
		
		btnBuscarArchivo.addActionListener(new controlGestionPrecios(this));
		
		
		add(scrollPane);
		
		txtDescripcion = new JTextArea();
		scrollPane.setViewportView(txtDescripcion);
		
		lista = new ArrayList<preciosDocumento>();
	}
	
	private void crearComponentes() {
		// TODO Auto-generated method stub
		
		btnBuscarArchivo = new JButton("PDF");
		btnBuscarArchivo.setBounds(10, 28, 86, 23);
	        
	    lblEstadoArchivo = new JLabel("Sin Archivo");
	    lblEstadoArchivo.setBounds(175, 31, 92, 15);
	    lblEstadoArchivo.setForeground(Color.WHITE);
	    lblEstadoArchivo.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    
	    lblNewLabel = new JLabel("Buscar Archivo");
	    lblNewLabel.setBounds(17, 9, 79, 15);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel.setForeground(Color.WHITE);
        
        lblNewLabel_1 = new JLabel("Estado Archivo");
        lblNewLabel_1.setBounds(165, 9, 81, 15);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        lblNewLabel_2 = new JLabel("Porcentaje");
        lblNewLabel_2.setBounds(360, 9, 59, 15);
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        txtPorcentaje = new JTextField();
        txtPorcentaje.setBounds(348, 29, 86, 20);
        txtPorcentaje.setColumns(10);
        
        lblNewLabel_3 = new JLabel("Procesar");
        lblNewLabel_3.setBounds(629, 9, 46, 15);
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_3.setForeground(Color.WHITE);
        
        btnProcesar = new JButton("Procesar Archivo");
        btnProcesar.setBounds(599, 28, 113, 23);
        
        tblListadoPrecios = new JTable();
        
        pnlTabla = new JScrollPane(tblListadoPrecios);
        pnlTabla.setBounds(10, 62, 829, 217);
        pnlTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        lblNewLabel_4 = new JLabel("ID");
        lblNewLabel_4.setBounds(17, 297, 12, 15);
        lblNewLabel_4.setForeground(Color.WHITE);
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        txtIdProducto = new JTextField();
        txtIdProducto.setBounds(58, 290, 86, 30);
        txtIdProducto.setEditable(false);
        
        lblNewLabel_5 = new JLabel("Descripcion");
        lblNewLabel_5.setBounds(541, 278, 61, 15);
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_5.setForeground(Color.WHITE);
        
        lblNewLabel_6 = new JLabel("Precio");
        lblNewLabel_6.setBounds(10, 344, 33, 15);
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_6.setForeground(Color.WHITE);
        
        lblNewLabel_7 = new JLabel("Buscar");
		lblNewLabel_7.setBounds(158, 298, 32, 14);
		lblNewLabel_7.setForeground(Color.WHITE);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(466, 293, 227, 74);
        
        txtPrecio = new JTextField();
        txtPrecio.setBounds(58, 337, 86, 30);
        
        btnRegistrarPrecio = new JButton("REGISTRAR PRECIO");
        btnRegistrarPrecio.setBounds(708, 341, 131, 23);
        
        txtFiltrarProducto = new JTextField();
        txtFiltrarProducto.setBounds(198, 290, 221, 30);
	}

	public void modeloTabla() {
		
		if(lista==null) {
			lista = DBGP.obtenerListadoProductosPrecios();
		}
		
		Object O[]=null;
		 aModel = new DefaultTableModel();
		 aModel = (DefaultTableModel) tblListadoPrecios.getModel();
	     aModel.setColumnIdentifiers(nombreColumnas);
	     
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
	
	public void limpiarTabla() {
		
		int a = aModel.getRowCount()-1;
		for(int i=a; i>=0;i--){
			aModel.removeRow(i);
		}
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

	public JTextArea getTxtDescripcion() {
		return txtDescripcion;
	}

	public JTextField getTxtPrecio() {
		return txtPrecio;
	}

	public JButton getBtnRegistrarPrecio() {
		return btnRegistrarPrecio;
	}

	public JTextField getTxtFiltrarProducto() {
		return txtFiltrarProducto;
	}
	
	public void setLista(List<preciosDocumento> lista) {
		this.lista = lista;
	}

	public preciosDocumento retornarElemento(int selectedRow){return lista.get(selectedRow);}
}
