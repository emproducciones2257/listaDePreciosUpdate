package views;


import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import control.controlFormatoCodBarr;
import control.registrarMarca;

public class pnlRegistraMarca extends JPanel {
	
	private TitledBorder tituloPanelMarca = new TitledBorder("Marca");
	private JLabel lblCodigoMarca;
	private JTextField txtScaner;
	private JLabel lblNombre;
    private JTextField txtNombreMarca;
    private JButton btnRegistrarMarca;
	
	
	
	public pnlRegistraMarca() {
		
		crearComponenete();
		
		//pripiedades Title
		tituloPanelMarca.setTitleColor((Color.WHITE));
		tituloPanelMarca.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		
		//propiedades JPanel
		setBackground(new java.awt.Color(34, 40, 44));
		setBounds(10, 11, 251, 182);
		setBorder(tituloPanelMarca);
		setLayout(null);
		
		//asignar elemento al panel
		
		add(lblCodigoMarca);
		add(txtScaner);
		add(lblNombre);
		add(txtNombreMarca);
		add(btnRegistrarMarca);
		
		txtScaner.addKeyListener(new controlFormatoCodBarr(this));
		btnRegistrarMarca.addActionListener(new registrarMarca(this));
	}
	
	private void crearComponenete() {
		
		lblCodigoMarca = new JLabel("Codigo Marca");
	    lblCodigoMarca.setForeground(Color.WHITE);
	    lblCodigoMarca.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lblCodigoMarca.setBounds(10, 25, 82, 19);
	    
	    txtScaner = new JTextField();
        txtScaner.setBounds(102, 25, 113, 30);
        txtScaner.setColumns(10);
        
        lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setBackground(Color.WHITE);
        lblNombre.setBounds(10, 73, 66, 25);
        
        txtNombreMarca = new JTextField();
        txtNombreMarca.setBounds(102, 71, 113, 30);
        txtNombreMarca.setColumns(10);
        
        btnRegistrarMarca = new JButton("Registrar Marca");
        btnRegistrarMarca.setBounds(67, 148, 146, 23);
        
	}

	public JTextField getTxtScaner() {
		return txtScaner;
	}


	public JTextField getTxtNombreMarca() {
		return txtNombreMarca;
	}

	
}
