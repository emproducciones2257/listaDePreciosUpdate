package dsbswing;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

import conexionBD.coneCone;
import conexionBD.prueba;
import modelo.paraBorrar;
import modelo.producto;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        JButton[] btns = {jButton1,jButton2,jButton3,jButton4,jButton5,jButton6};
        
        for (JButton btn : btns) {
            btn.setBackground(new Color(21,25,28));
            btn.setUI(new BasicButtonUI());
            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(new Color(54,81,207));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new Color(21,25,28));
                }
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	
        produ = new producto();
        marcaClave = new HashMap<>();
        marcaClave.put(4111, "FABER CASTELL");
        marcaClave.put(1153, "ACRILEX");
        marcaClave.put(8159, "PLAYCOLOR");
        
        
        
        pnlRoot = new javax.swing.JPanel();
        pnlSlide = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        pnlCenter = new javax.swing.JPanel();
        caed1 = new javax.swing.JPanel();
        pnlCTop = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pnCCenter = new javax.swing.JPanel();
        pnlCBooton = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlRoot.setPreferredSize(new java.awt.Dimension(960, 600));
        pnlRoot.setRequestFocusEnabled(false);
        pnlRoot.setLayout(new java.awt.BorderLayout());

        pnlSlide.setBackground(new java.awt.Color(21, 25, 28));
        pnlSlide.setPreferredSize(new java.awt.Dimension(60, 0));

        jPanel1.setBackground(new java.awt.Color(21, 25, 28));
        jPanel1.setPreferredSize(new java.awt.Dimension(50, 150));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 40));

        jButton6.setBackground(new java.awt.Color(34, 40, 44));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_trampoline_park_16px.png"))); // NOI18N
        jButton6.setMaximumSize(new java.awt.Dimension(40, 40));
        jButton6.setMinimumSize(new java.awt.Dimension(40, 40));
        jButton6.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanel1.add(jButton6);

        pnlSlide.add(jPanel1);

        jButton1.setBackground(new java.awt.Color(34, 40, 44));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_home_26px.png"))); // NOI18N
        jButton1.setMaximumSize(new java.awt.Dimension(40, 40));
        jButton1.setMinimumSize(new java.awt.Dimension(40, 40));
        jButton1.setPreferredSize(new java.awt.Dimension(40, 40));
        pnlSlide.add(jButton1);

        jButton2.setBackground(new java.awt.Color(34, 40, 44));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_circled_24px.png"))); // NOI18N
        jButton2.setMaximumSize(new java.awt.Dimension(40, 40));
        jButton2.setMinimumSize(new java.awt.Dimension(40, 40));
        jButton2.setPreferredSize(new java.awt.Dimension(40, 40));
        pnlSlide.add(jButton2);

        jButton3.setBackground(new java.awt.Color(34, 40, 44));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_document_32px.png"))); // NOI18N
        jButton3.setMaximumSize(new java.awt.Dimension(40, 40));
        jButton3.setMinimumSize(new java.awt.Dimension(40, 40));
        jButton3.setPreferredSize(new java.awt.Dimension(40, 40));
        pnlSlide.add(jButton3);

        jButton4.setBackground(new java.awt.Color(34, 40, 44));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_define_location_26px.png"))); // NOI18N
        jButton4.setMaximumSize(new java.awt.Dimension(40, 40));
        jButton4.setMinimumSize(new java.awt.Dimension(40, 40));
        jButton4.setPreferredSize(new java.awt.Dimension(40, 40));
        pnlSlide.add(jButton4);

        jButton5.setBackground(new java.awt.Color(34, 40, 44));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_align_cell_content_left_50px.png"))); // NOI18N
        jButton5.setMaximumSize(new java.awt.Dimension(40, 40));
        jButton5.setMinimumSize(new java.awt.Dimension(40, 40));
        jButton5.setPreferredSize(new java.awt.Dimension(40, 40));
        pnlSlide.add(jButton5);

        pnlRoot.add(pnlSlide, java.awt.BorderLayout.WEST);

        pnlCenter.setBackground(new java.awt.Color(34, 40, 44));
        pnlCenter.setLayout(new java.awt.CardLayout());

        caed1.setLayout(new java.awt.BorderLayout());

        pnlCTop.setBackground(new java.awt.Color(34, 40, 44));
        pnlCTop.setPreferredSize(new java.awt.Dimension(0, 100));

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Algun Lugar - Castilla");

        jLabel8.setFont(new java.awt.Font("Schadow BT", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Listado de Precios");
        
        JLabel lblFechaBD = new JLabel("Fecha Base de datos:");
        lblFechaBD.setForeground(Color.WHITE);

        javax.swing.GroupLayout pnlCTopLayout = new javax.swing.GroupLayout(pnlCTop);
        pnlCTopLayout.setHorizontalGroup(
        	pnlCTopLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(pnlCTopLayout.createSequentialGroup()
        			.addGap(51)
        			.addGroup(pnlCTopLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(pnlCTopLayout.createSequentialGroup()
        					.addComponent(jLabel8)
        					.addPreferredGap(ComponentPlacement.RELATED, 423, Short.MAX_VALUE)
        					.addComponent(lblFechaBD)
        					.addGap(133))
        				.addGroup(pnlCTopLayout.createSequentialGroup()
        					.addComponent(jLabel7)
        					.addContainerGap(733, Short.MAX_VALUE))))
        );
        pnlCTopLayout.setVerticalGroup(
        	pnlCTopLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(pnlCTopLayout.createSequentialGroup()
        			.addContainerGap(33, Short.MAX_VALUE)
        			.addGroup(pnlCTopLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel8)
        				.addComponent(lblFechaBD))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jLabel7)
        			.addContainerGap())
        );
        pnlCTop.setLayout(pnlCTopLayout);

        caed1.add(pnlCTop, java.awt.BorderLayout.NORTH);

        pnCCenter.setBackground(new java.awt.Color(34, 40, 44));
        pnCCenter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(44, 52, 58)));
        pnCCenter.setPreferredSize(new java.awt.Dimension(500, 300));

        caed1.add(pnCCenter, java.awt.BorderLayout.CENTER);
        pnCCenter.setLayout(null);
        
        JPanel pnelRegistrarMarca = new JPanel();
        pnelRegistrarMarca.setBackground(new java.awt.Color(34, 40, 44));
        pnelRegistrarMarca.setBounds(10, 11, 251, 182);
        TitledBorder tituloPanelMarca = new TitledBorder("Marca");
        tituloPanelMarca.setTitleColor((Color.WHITE));
        tituloPanelMarca.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        pnelRegistrarMarca.setBorder(tituloPanelMarca);
        pnCCenter.add(pnelRegistrarMarca);
        pnelRegistrarMarca.setLayout(null);
        
        JLabel lblCodigoMarca = new JLabel("Codigo Marca");
        lblCodigoMarca.setForeground(Color.WHITE);
        lblCodigoMarca.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCodigoMarca.setBounds(10, 25, 82, 19);
        pnelRegistrarMarca.add(lblCodigoMarca);
        
        txtScaner = new JTextField();
        txtScaner.setBounds(102, 25, 113, 30);
        pnelRegistrarMarca.add(txtScaner);
        txtScaner.setColumns(10);
        
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setBackground(Color.WHITE);
        lblNombre.setBounds(10, 73, 66, 25);
        pnelRegistrarMarca.add(lblNombre);
        
        textField = new JTextField();
        textField.setBounds(102, 71, 113, 30);
        pnelRegistrarMarca.add(textField);
        textField.setColumns(10);
        
        JButton btnRegistrarMarca = new JButton("Registrar Marca");
        btnRegistrarMarca.setBounds(67, 148, 146, 23);
        pnelRegistrarMarca.add(btnRegistrarMarca);
        
        pnelRegistraColor = new JPanel();
        pnelRegistraColor.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        pnelRegistraColor.setBackground(new Color(34, 40, 44));
        pnelRegistraColor.setBounds(316, 11, 251, 182);
        TitledBorder tituloPanelColor = new TitledBorder("Color");
        tituloPanelColor.setTitleColor((Color.WHITE));
        tituloPanelColor.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        pnelRegistraColor.setBorder(tituloPanelColor);
        pnCCenter.add(pnelRegistraColor);
        pnelRegistraColor.setLayout(null);
        
        lblMarca = new JLabel("Marca");
        lblMarca.setBounds(27, 27, 44, 19);
        lblMarca.setForeground(Color.WHITE);
        lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 12));
        pnelRegistraColor.add(lblMarca);
        
        lblNombreColor = new JLabel("Color");
        lblNombreColor.setBounds(27, 57, 49, 25);
        lblNombreColor.setForeground(Color.WHITE);
        lblNombreColor.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNombreColor.setBackground(Color.WHITE);
        pnelRegistraColor.add(lblNombreColor);
        
        textField_2 = new JTextField();
        textField_2.setBounds(102, 63, 113, 30);
        textField_2.setColumns(10);
        pnelRegistraColor.add(textField_2);
        
        btnRegistrarMarca_1 = new JButton("Registrar Color");
        btnRegistrarMarca_1.setBounds(69, 148, 146, 23);
        pnelRegistraColor.add(btnRegistrarMarca_1);
        
        JLabel lblCodigo = new JLabel("Codigo");
        lblCodigo.setForeground(Color.WHITE);
        lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCodigo.setBounds(25, 106, 46, 14);
        pnelRegistraColor.add(lblCodigo);
        
        textField_1 = new JTextField();
        textField_1.setBounds(102, 99, 113, 30);
        pnelRegistraColor.add(textField_1);
        textField_1.setColumns(10);
        
        txtMarcaColor = new JTextField();
        txtMarcaColor.setBounds(102, 27, 113, 30);
        pnelRegistraColor.add(txtMarcaColor);
        txtMarcaColor.setColumns(10);
        
        JPanel pnelCargarProducto = new JPanel();
        pnelCargarProducto.setLayout(null);
        pnelCargarProducto.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        pnelCargarProducto.setBackground(new Color(34, 40, 44));
        pnelCargarProducto.setBounds(20, 207, 839, 182);
        TitledBorder tituloPanelCargaProducto = new TitledBorder("Producto");
        tituloPanelCargaProducto.setTitleColor((Color.WHITE));
        tituloPanelCargaProducto.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        pnelCargarProducto.setBorder(tituloPanelCargaProducto);
        pnCCenter.add(pnelCargarProducto);
        
        JLabel lblMarca_1 = new JLabel("Marca");
        lblMarca_1.setForeground(Color.WHITE);
        lblMarca_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblMarca_1.setBounds(27, 27, 44, 19);
        pnelCargarProducto.add(lblMarca_1);
        
        JLabel lblNombreColor_1 = new JLabel("Color");
        lblNombreColor_1.setForeground(Color.WHITE);
        lblNombreColor_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNombreColor_1.setBackground(Color.WHITE);
        lblNombreColor_1.setBounds(27, 57, 49, 25);
        pnelCargarProducto.add(lblNombreColor_1);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(102, 63, 113, 30);
        pnelCargarProducto.add(textField_3);
        
        JButton btnRegistrarMarca_1_1 = new JButton("Registrar Producto");
        btnRegistrarMarca_1_1.setBounds(69, 148, 146, 23);
        pnelCargarProducto.add(btnRegistrarMarca_1_1);
        
        JLabel lblCodigo_1 = new JLabel("Codigo");
        lblCodigo_1.setForeground(Color.WHITE);
        lblCodigo_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCodigo_1.setBounds(25, 106, 46, 14);
        pnelCargarProducto.add(lblCodigo_1);
        
        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(102, 104, 113, 30);
        pnelCargarProducto.add(textField_4);
        
        txtProdMarca = new JTextField();
        txtProdMarca.setBounds(102, 22, 113, 30);
        pnelCargarProducto.add(txtProdMarca);
        txtProdMarca.setColumns(10);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(323, 24, 208, 110);
        pnelCargarProducto.add(scrollPane);
        
        JTextArea txtDescripcionProducto = new JTextArea();
        scrollPane.setViewportView(txtDescripcionProducto);
        
        lblDescripcion = new JLabel("Descripcion");
        lblDescripcion.setForeground(Color.WHITE);
        lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblDescripcion.setBounds(238, 29, 75, 14);
        pnelCargarProducto.add(lblDescripcion);
        
        txtScaner.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {

				codigo = codigo + String.valueOf(e.getKeyChar());
				
				if (codigo.length()==14) {
					String sacado = codigo.substring(3, 7);
					verificarCodigoMarcar(Integer.parseInt(sacado));
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
					
			}

			private void verificarCodigoMarcar(int parseInt) {
				// TODO Auto-generated method stub
					Iterator it = marcaClave.keySet().iterator();
					Boolean estado = true;
					
					while (it.hasNext()) {
						Integer key = (Integer) it.next();
						if (key == parseInt) {
							System.out.println("MARCA: " + marcaClave.get(parseInt));
							estado = false;
						}
						
					}
					
					if (estado==true) {
						System.out.println("CODIGO NO REGISTRADO EN LA BASE");
					}
					
					codigo="";
					txtScaner.setText("");
			}
		});

        pnlCBooton.setBackground(new java.awt.Color(34, 40, 44));
        pnlCBooton.setPreferredSize(new java.awt.Dimension(0, 100));

        javax.swing.GroupLayout pnlCBootonLayout = new javax.swing.GroupLayout(pnlCBooton);
        pnlCBootonLayout.setHorizontalGroup(
        	pnlCBootonLayout.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 900, Short.MAX_VALUE)
        );
        pnlCBootonLayout.setVerticalGroup(
        	pnlCBootonLayout.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 100, Short.MAX_VALUE)
        );
        pnlCBooton.setLayout(pnlCBootonLayout);

        caed1.add(pnlCBooton, java.awt.BorderLayout.SOUTH);

        pnlCenter.add(caed1, "card5");

        pnlRoot.add(pnlCenter, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlRoot, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    public static void main(String args[]) {
    	
    	
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
        
        prueba temp = new prueba();
    	paraBorrar borrar = new paraBorrar();
    	
    	Connection c = coneCone.connect();
    	
    	if(c!=null) {
    		
    		borrar = temp.recuperarEquiposPorCampeonato(c, 100);
    		System.out.print("NOMBRE: " +borrar.getNombre());
    		System.out.print("NUM A: " +borrar.getNumA());
    		System.out.print("NUM B: " +borrar.getNumB());
    		System.out.print("NUM C: " +borrar.getNumC());
    		System.out.println("");
        }else System.out.print("CAPAZ NO CHE");
    	
    	
    	Connection c2 = coneCone.connect();
    	
    	if(c!=null) {

    		
    		borrar = temp.recuperarEquiposPorCampeonato(c2, 385);
    		System.out.print("NOMBRE: " +borrar.getNombre());
    		System.out.print("NUM A: " +borrar.getNumA());
    		System.out.print("NUM B: " +borrar.getNumB());
    		System.out.print("NUM C: " +borrar.getNumC());
    		
        }else System.out.print("CAPAZ NO CHE");
    	
    	
        
    }
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel caed1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnCCenter;
    private javax.swing.JPanel pnlCBooton;
    private javax.swing.JPanel pnlCTop;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlRoot;
    private javax.swing.JPanel pnlSlide;
    private producto produ;
    
    private String codigo="";
    
    private Map<Integer, String> marcaClave;
    private static JTextField txtScaner;
    private JTextField textField;
    private JPanel pnelRegistraColor;
    private JLabel lblMarca;
    private JLabel lblNombreColor;
    private JTextField textField_2;
    private JButton btnRegistrarMarca_1;
    private JTextField textField_1;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField txtMarcaColor;
    private JTextField txtProdMarca;
    private JLabel lblDescripcion;
}
