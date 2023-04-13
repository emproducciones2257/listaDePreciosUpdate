package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.controlMSergio;
import modelo.modeloMSergio;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PnlMauroSergio extends JFrame {

	private JPanel contentPane;
	private JTextField txtArticuloPrecio,txtPrecioTexto,txtPrecio,txtArticuloProducto,txtCodigo;
	private JLabel lblNewLabel,lblNewLabel_1,lblNewLabel_2,lblNewLabel_3,lblRegistrarProducto,lblNewLabel_1_1,lblNewLabel_3_1;
	private JButton btnRegistrar,btnBuscar,btnCargarMaurito,btnActualizar;
	

	public PnlMauroSergio() {
		setTitle("Mauro Sergio - Lista De Precios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 519, 343);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(34, 40, 44));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		iniciarVistas();
		
		cargarVistas();
		
		btnRegistrar.addActionListener(new controlMSergio(this));
	}

	private void cargarVistas() {
		contentPane.add(lblNewLabel);
		contentPane.add(lblNewLabel_1);	
		contentPane.add(lblNewLabel_2);
		contentPane.add(lblNewLabel_3);
		contentPane.add(txtPrecioTexto);
		contentPane.add(txtPrecio);
		contentPane.add(btnRegistrar);
		contentPane.add(btnBuscar);
		contentPane.add(txtArticuloProducto);
		contentPane.add(lblRegistrarProducto);
		contentPane.add(lblNewLabel_1_1);
		contentPane.add(txtCodigo);
		contentPane.add(btnCargarMaurito);
		contentPane.add(lblNewLabel_3_1);
		contentPane.add(btnActualizar);
			
	}

	private void iniciarVistas() {
		
		lblNewLabel = new JLabel("CARGAR NUEVO");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(20, 11, 144, 14);
		
		lblNewLabel_1 = new JLabel("Articulo");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(46, 36, 46, 14);
		
		txtArticuloPrecio = new JTextField();
		txtArticuloPrecio.setBounds(16, 63, 86, 30);
		contentPane.add(txtArticuloPrecio);
		txtArticuloPrecio.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Precio");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(414, 36, 46, 14);
		
		lblNewLabel_3 = new JLabel("Nombre Articulo");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(200, 36, 101, 14);
		
		txtPrecioTexto = new JTextField();
		txtPrecioTexto.setBounds(118, 63, 266, 30);
		txtPrecioTexto.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(400, 63, 86, 30);		
		txtPrecio.setColumns(10);
		
		btnRegistrar = new JButton("Cargar");
		btnRegistrar.setBounds(340, 117, 140, 30);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(20, 117, 140, 30);
		
		lblRegistrarProducto = new JLabel("REGISTRAR PRODUCTO");
		lblRegistrarProducto.setForeground(Color.WHITE);
		lblRegistrarProducto.setBounds(20, 172, 144, 14);
		
		txtArticuloProducto = new JTextField();
		txtArticuloProducto.setColumns(10);
		txtArticuloProducto.setBounds(10, 222, 86, 30);
		
		lblNewLabel_1_1 = new JLabel("Articulo");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setBounds(20, 197, 46, 14);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(111, 222, 140, 30);
		
		btnCargarMaurito = new JButton("Cargar");
		btnCargarMaurito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCargarMaurito.setBounds(277, 222, 140, 30);
		
		lblNewLabel_3_1 = new JLabel("Codigo");
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setBounds(153, 197, 59, 14);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(180, 117, 140, 30);
	}
	
	public void limpiarCargaPrecio() {
		getTxtArticuloPrecio().setText("NO");
		getTxtPrecioTexto().setText("NO");
		getTxtPrecio().setText("NO");
	}
	
	public void limpiarCargaProducto() {
		getTxtArticuloProducto().setText("");
		getTxtCodigo().setText("");
	}
	
	public void cargarDatos(modeloMSergio sergito) {
		getTxtArticuloPrecio().setText(sergito.getArticulo());
		getTxtPrecioTexto().setText(sergito.getPrecioTexto());
		getTxtPrecio().setText(sergito.getPrecio()+"");
	}

	public JTextField getTxtArticuloPrecio() {
		return txtArticuloPrecio;
	}

	public JTextField getTxtPrecioTexto() {
		return txtPrecioTexto;
	}

	public JTextField getTxtPrecio() {
		return txtPrecio;
	}

	public JTextField getTxtArticuloProducto() {
		return txtArticuloProducto;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JButton getBtnCargarMaurito() {
		return btnCargarMaurito;
	}

	public JButton getBtnActualizar() {
		return btnActualizar;
	}
}
