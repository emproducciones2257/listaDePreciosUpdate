package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import control.gestionCategorias;

public class pnlGestionCategoria extends JPanel {
	
	private TitledBorder tituloPanelCategoria = new TitledBorder("Categoria");
	private JLabel lblGestionCatTitulo;
	private JTextField txtNombCategoria;
	private JButton btnCargarCategoria, btnCancelar;
	
	public pnlGestionCategoria() {
		
		crearComponenete();
		
		//pripiedades Title
		tituloPanelCategoria.setTitleColor((Color.WHITE));
		tituloPanelCategoria.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		//propiedades JPanel
		setBackground(new java.awt.Color(34, 40, 44));
		setBounds(567, 11, 272, 171);
		setBorder(tituloPanelCategoria);
		setLayout(null);
		
		//asignar elemento al panel
		
		add(lblGestionCatTitulo);
		add(txtNombCategoria);
		add(btnCargarCategoria);
		add(btnCancelar);
		
		btnCargarCategoria.addActionListener(new gestionCategorias(this));	
	}

	private void crearComponenete() {
        
        lblGestionCatTitulo = new JLabel("CREAR CATEGORIA");
        lblGestionCatTitulo.setBounds(78, 32, 112, 14);
        lblGestionCatTitulo.setForeground(Color.WHITE);
        lblGestionCatTitulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        txtNombCategoria = new JTextField();
        txtNombCategoria.setBounds(49, 66, 164, 30);
        txtNombCategoria.setColumns(10);
        
        btnCargarCategoria = new JButton("CARGAR");
        btnCargarCategoria.setBounds(151, 123, 112, 23);
        
        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setBounds(10, 123, 112, 23);

	}
	
	public void resetearComponente() {txtNombCategoria.setText("");}

	public TitledBorder getTituloPanelCategoria() {
		return tituloPanelCategoria;
	}

	public JTextField getTxtNombCategoria() {
		return txtNombCategoria;
	}

	public JButton getBtnCargarCategoria() {
		return btnCargarCategoria;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	
}
