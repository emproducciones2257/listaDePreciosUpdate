package views;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import conexionBD.dbGestionPrecios;
import control.controlRegistrarProducto;
import modelo.preciosDocumento;

public class pnlRegistrarProducto extends JPanel {
	
	private TitledBorder tituloPanelCargaProducto=new TitledBorder("Producto");
	private JLabel lblMarca_1;
	private JLabel lblNombreColor_1;
	private JComboBox<String> jcbColor;
	private JButton btnRegistrarProd;
	private JLabel lblCodigo_1;
    private JTextField txtCodProducto;
    private JTextField txtCodMarca;
    private JScrollPane scpDescripcion;
    private JScrollPane pnlBD;
    private JTextArea txtDescripcionProducto;
    private JLabel lblDescripcion;
    private JTextField txtBusquedPrecio;
    private JLabel lblNewLabel;
    private JTextField txtUVenta;
    private JLabel lblNewLabel_1;
    private JTable visorDatosPrecios;
    private DefaultTableModel aModel;
    private dbGestionPrecios DBGP;
    
    private String [] nombreColumnas = {"Descripcion","Precio"};
	
	public pnlRegistrarProducto() {
		
		DBGP = new dbGestionPrecios();
		
		crearComponentes();
		modeloTabla(DBGP.obtenerListadoProductosPrecios());
		
		//propiedades Titulo
		tituloPanelCargaProducto.setTitleColor((Color.WHITE));
		tituloPanelCargaProducto.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		//propiedades JPanel
		 setBackground(new Color(34, 40, 44));
		 setBounds(20, 207, 840, 231);
		 setLayout(null);
		 setBorder(tituloPanelCargaProducto);
		 
		 
		 //agregar elementos al panel
		 add(lblMarca_1);
		 add(lblNombreColor_1);
		 add(jcbColor);
		 add(btnRegistrarProd);
		 add(lblCodigo_1);
		 add(txtCodProducto);
		 add(txtCodMarca);
		 add(scpDescripcion);		 
		 add(lblDescripcion);
		 add(txtBusquedPrecio);
		 add(lblNewLabel);
		 add(txtUVenta);
		 add(lblNewLabel_1);
		 add(pnlBD);		 
		 
		 btnRegistrarProd.addActionListener(new controlRegistrarProducto(this)); 
		 
		 JButton btnCancelarCarga = new JButton("Cancelar Carga");
		 btnCancelarCarga.setBounds(601, 197, 208, 23);
		 add(btnCancelarCarga);
		 
		 JComboBox<String> comboBox = new JComboBox();
		 comboBox.setBounds(102, 27, 113, 30);
		 add(comboBox);
		 
		 JLabel lbCategoria = new JLabel("Categoria");
		 lbCategoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 lbCategoria.setForeground(Color.WHITE);
		 lbCategoria.setBounds(25, 30, 67, 14);
		 add(lbCategoria);
	}

	private void crearComponentes() {

		lblMarca_1 = new JLabel("Marca");
	    lblMarca_1.setForeground(Color.WHITE);
	    lblMarca_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lblMarca_1.setBounds(27, 72, 44, 19);
	    
	    lblNombreColor_1 = new JLabel("Color");
        lblNombreColor_1.setForeground(Color.WHITE);
        lblNombreColor_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNombreColor_1.setBackground(Color.WHITE);
        lblNombreColor_1.setBounds(27, 110, 49, 25);
        
        jcbColor = new JComboBox<String>();
        jcbColor.setBounds(102, 108, 113, 30);
        
        btnRegistrarProd = new JButton("Registrar Producto");
        btnRegistrarProd.setBounds(601, 163, 208, 23);
        
        lblCodigo_1 = new JLabel("Codigo");
        lblCodigo_1.setForeground(Color.WHITE);
        lblCodigo_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCodigo_1.setBounds(27, 154, 46, 14);
        
        txtCodProducto = new JTextField();
        txtCodProducto.setColumns(10);
        txtCodProducto.setBounds(102, 149, 113, 30);
        txtCodProducto.setEditable(false);
        
        txtCodMarca = new JTextField();
        txtCodMarca.setBounds(102, 67, 113, 30);
        
        scpDescripcion = new JScrollPane();
        scpDescripcion.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scpDescripcion.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scpDescripcion.setBounds(601, 57, 208, 77);
        
        txtDescripcionProducto = new JTextArea();
        scpDescripcion.setViewportView(txtDescripcionProducto);
        
        lblDescripcion = new JLabel("Descripcion");
        lblDescripcion.setForeground(Color.WHITE);
        lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblDescripcion.setBounds(675, 29, 75, 14);
        
        txtBusquedPrecio = new JTextField();
        txtBusquedPrecio.setBounds(277, 22, 314, 30);
        
        lblNewLabel = new JLabel("U. Venta");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(28, 197, 64, 14);
        
        txtUVenta = new JTextField();
        txtUVenta.setBounds(102, 190, 113, 30);        
        txtUVenta.setText("0");
        
        lblNewLabel_1 = new JLabel("Buscar");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(225, 29, 49, 14);
        
        visorDatosPrecios = new JTable();
   
        pnlBD = new JScrollPane(visorDatosPrecios);
        pnlBD.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pnlBD.setBounds(225, 57, 366, 163);
	}
	
	
	public void modeloTabla(List<preciosDocumento> lista) {
		
		Object O[]=null;
		
		 aModel = new DefaultTableModel();
		 aModel = (DefaultTableModel) visorDatosPrecios.getModel();
	     aModel.setColumnIdentifiers(nombreColumnas);

	     
	     if(!lista.isEmpty()) {
	    	 for (int i = 0; i < lista.size(); i++) {
	    		 aModel.addRow(O);
	    		 aModel.setValueAt(lista.get(i).getProd(), i, 0);
	    		 aModel.setValueAt(lista.get(i).getPrecio(), i, 1); 
			}
	     }
	    
	     visorDatosPrecios.setModel(aModel);
	}
	
	public void resetearComponentes() {
		getTxtCodMarca().setText("");
		getJcbColor().removeAllItems();
		getTxtCodMarca().setText("");
		getTxtDescripcionProducto().setText("");
		getTxtBusquedPrecio().setText("");
		getTxtCodProducto().setText("");
		getTxtUVenta().setText("0");
		limpiarTabla();
	}

	 public void limpiarTabla() {
		// TODO Auto-generated method stub
		int a = getaModel().getRowCount()-1;
		for(int i=a; i>=0;i--){
			getaModel().removeRow(i);
		}
	}

	public TitledBorder getTituloPanelCargaProducto() {
		return tituloPanelCargaProducto;
	}


	public JComboBox<String> getJcbColor() {
		return jcbColor;
	}


	public JButton getBtnRegistrarProd() {
		return btnRegistrarProd;
	}


	public JTextField getTxtCodProducto() {
		return txtCodProducto;
	}


	public JTextField getTxtCodMarca() {
		return txtCodMarca;
	}


	public JScrollPane getScpDescripcion() {
		return scpDescripcion;
	}


	public JTextArea getTxtDescripcionProducto() {
		return txtDescripcionProducto;
	}


	public JTextField getTxtBusquedPrecio() {
		return txtBusquedPrecio;
	}


	public JTextField getTxtUVenta() {
		return txtUVenta;
	}


	public JTable getVisorDatosPrecios() {
		return visorDatosPrecios;
	}


	public DefaultTableModel getaModel() {
		return aModel;
	}


	public void setaModel(DefaultTableModel aModel) {
		this.aModel = aModel;
	}
}
