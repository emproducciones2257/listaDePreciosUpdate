package views;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

import conexionBD.coneCone;

import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public Principal() {
        initComponents();
        JButton[] btns = {jButton1,jButton2,jButton3,jButton4,jButton5,jButton6};
        
        for (JButton btn : btns) {
            btn.setBackground(new Color(21,25,28));
            btn.setUI(new BasicButtonUI());
            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	
                	
                	if(pnCCenter.isShowing()) {
                		pnlCenter.setVisible(false);
                	}else {
                		pnlCenter.setVisible(true);
					}
                	
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
        
        pnelRegistrarMarca = new pnlRegistraMarca();
        pnCCenter.add(pnelRegistrarMarca);
        
        pnelRegistraColor = new pnlRegistrarColor();
        pnCCenter.add(pnelRegistraColor);
        
        pnelCargarProducto = new pnlRegistrarProducto();
        pnCCenter.add(pnelCargarProducto);

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
			coneCone.connect();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
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
    private JPanel pnelRegistraColor;
    private JPanel pnelRegistrarMarca;
    private JPanel pnelCargarProducto;
}
