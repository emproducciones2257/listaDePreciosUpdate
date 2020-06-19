package control;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

import views.Principal;

public class controlBtnPrincipal implements MouseListener{
	
	private Principal pnl;
	
	public controlBtnPrincipal(Principal pnl) {
		this.pnl = pnl;
		inicioBotones();
		pnl.getBtnRegistro().addMouseListener(this);
		pnl.getBtnConsultas().addMouseListener(this);
		pnl.getBtnGestionPrecios().addMouseListener(this);	
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if(e.getSource().equals(pnl.getBtnRegistro())) {
			pnl.getPnlConsultaPrecios().setVisible(false);
			pnl.getPnlGestionCarga().setVisible(true);
			pnl.getPnlGestionPrecios().setVisible(false);
		}
		
		if(e.getSource().equals(pnl.getBtnConsultas())) {
			pnl.getPnlConsultaPrecios().setVisible(true);
			pnl.getPnlGestionCarga().setVisible(false);
			pnl.getPnlGestionPrecios().setVisible(false);
		}
		
		if(e.getSource().equals(pnl.getBtnGestionPrecios())) {
			pnl.getPnlConsultaPrecios().setVisible(false);
			pnl.getPnlGestionCarga().setVisible(false);
			pnl.getPnlGestionPrecios().setVisible(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JButton b = (JButton) e.getSource();
		b.setBackground(new Color(54,81,207));		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton b = (JButton) e.getSource();
		b.setBackground(new Color(21,25,28));		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void inicioBotones() {
		JButton[] bt  = {pnl.getBtnAlPedo(),pnl.getBtnConsultas(),pnl.getBtnGestionPrecios(),pnl.getBtnRegistro()};
		
		for (JButton btn : bt) {
            btn.setBackground(new Color(21,25,28));
            btn.setUI(new BasicButtonUI());
            btn.addMouseListener(this);
        }
	}
}
