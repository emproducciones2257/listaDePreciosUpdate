package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import control.controlRegistrarProducto;
import modelo.color;

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
    private JTextArea txtDescripcionProducto;
    private JLabel lblDescripcion;
    private JTextField txtBusquedPrecio;
    private JLabel lblNewLabel;
    private JTextField txtUVenta;
    private JLabel lblNewLabel_1;
    private JTable visorDatosPrecios;
	
	
	public pnlRegistrarProducto() {
		
		crearComponentes();
		
		//propiedades Titulo
		tituloPanelCargaProducto.setTitleColor((Color.WHITE));
		tituloPanelCargaProducto.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		//propiedades JPanel
		 setBackground(new Color(34, 40, 44));
		 setBounds(20, 207, 839, 193);
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
		 add(visorDatosPrecios);		 
		 
		 btnRegistrarProd.addActionListener(new controlRegistrarProducto(this));
		 
	}
	

	private void crearComponentes() {

		lblMarca_1 = new JLabel("Marca");
	    lblMarca_1.setForeground(Color.WHITE);
	    lblMarca_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lblMarca_1.setBounds(27, 27, 44, 19);
	    
	    lblNombreColor_1 = new JLabel("Color");
        lblNombreColor_1.setForeground(Color.WHITE);
        lblNombreColor_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNombreColor_1.setBackground(Color.WHITE);
        lblNombreColor_1.setBounds(27, 57, 49, 25);
        
        jcbColor = new JComboBox<String>();
        jcbColor.setBounds(102, 63, 113, 30);
        
        btnRegistrarProd = new JButton("Registrar Producto");
        btnRegistrarProd.setBounds(601, 159, 208, 23);
        
        lblCodigo_1 = new JLabel("Codigo");
        lblCodigo_1.setForeground(Color.WHITE);
        lblCodigo_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCodigo_1.setBounds(25, 106, 46, 14);
        
        txtCodProducto = new JTextField();
        txtCodProducto.setColumns(10);
        txtCodProducto.setBounds(102, 104, 113, 30);
        
        txtCodMarca = new JTextField();
        txtCodMarca.setBounds(102, 22, 113, 30);
        
        scpDescripcion = new JScrollPane();
        scpDescripcion.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scpDescripcion.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scpDescripcion.setBounds(601, 57, 208, 77);
        
        JTextArea txtDescripcionProducto = new JTextArea();
        scpDescripcion.setViewportView(txtDescripcionProducto);
        
        lblDescripcion = new JLabel("Descripcion");
        lblDescripcion.setForeground(Color.WHITE);
        lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblDescripcion.setBounds(675, 29, 75, 14);
        
        txtBusquedPrecio = new JTextField();
        txtBusquedPrecio.setBounds(251, 55, 314, 30);
        
        lblNewLabel = new JLabel("U. Venta");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(10, 162, 64, 14);
        
        txtUVenta = new JTextField();
        txtUVenta.setBounds(102, 155, 113, 30);        
        
        lblNewLabel_1 = new JLabel("Buscar Precio");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(351, 29, 113, 14);
        
        visorDatosPrecios = new JTable();
        
        visorDatosPrecios.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Producto", "Producto"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		Integer.class, String.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        visorDatosPrecios.setColumnSelectionAllowed(true);
        visorDatosPrecios.setCellSelectionEnabled(true);
        visorDatosPrecios.setBounds(251, 107, 314, 57);
		
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

}
