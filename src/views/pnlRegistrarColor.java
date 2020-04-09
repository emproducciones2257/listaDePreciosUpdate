package views;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import control.registrarColor;

public class pnlRegistrarColor extends JPanel{
	
	private TitledBorder tituloPanelColor = new TitledBorder("Color");
	private JLabel lblMarca;
	private JLabel lblNombreColor;
    private JTextField txtNombreColor;
    private JButton btnRegistrarMarca;
    private JLabel lblCodigo;
    private JTextField txtCodigoColor;
    private JTextField txtScaner;

	public pnlRegistrarColor() {
		
		crearComponenete();
		
		//pripiedades Title
		tituloPanelColor.setTitleColor((Color.WHITE));
		tituloPanelColor.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		
		//propiedades JPanel
		setBackground(new java.awt.Color(34, 40, 44));
		setBounds(316, 11, 251, 182);
		setBorder(tituloPanelColor);
		setLayout(null);
		
		//asignar elemento al panel
		
		add(lblMarca);
		add(lblNombreColor);
		add(txtNombreColor);
		add(btnRegistrarMarca);
		add(lblCodigo);
		add(txtCodigoColor);
		add(txtScaner);
		
		
		btnRegistrarMarca.addActionListener(new registrarColor(this));
		

	}
	
	private void crearComponenete() {
		
		lblMarca = new JLabel("Marca");
        lblMarca.setBounds(27, 27, 44, 19);
        lblMarca.setForeground(Color.WHITE);
        lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 12));		
        
        lblNombreColor = new JLabel("Color");
        lblNombreColor.setBounds(27, 57, 49, 25);
        lblNombreColor.setForeground(Color.WHITE);
        lblNombreColor.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNombreColor.setBackground(Color.WHITE);
        
        txtNombreColor = new JTextField();
        txtNombreColor.setBounds(102, 63, 113, 30);
        txtNombreColor.setColumns(10);
        
        btnRegistrarMarca = new JButton("Registrar Color");
        btnRegistrarMarca.setBounds(69, 148, 146, 23);
        
        lblCodigo = new JLabel("Codigo");
        lblCodigo.setForeground(Color.WHITE);
        lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCodigo.setBounds(25, 106, 46, 14);
        
        txtCodigoColor = new JTextField();
        txtCodigoColor.setBounds(102, 99, 113, 30);
        txtCodigoColor.setColumns(10);
        
        txtScaner = new JTextField();
        txtScaner.setBounds(102, 27, 113, 30);
        txtScaner.setColumns(10);

	}

	public JTextField getTxtNombreColor() {
		return txtNombreColor;
	}

	public JTextField gettxtCodigoColor() {
		return txtCodigoColor;
	}

	public JTextField gettxtScaner() {
		return txtScaner;
	}
	
	public void limpiarElementos() {
		gettxtScaner().setText("");
		gettxtCodigoColor().setText("");
		getTxtNombreColor().setText("");
	}

}
