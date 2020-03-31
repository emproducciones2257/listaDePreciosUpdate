package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import views.pnlRegistraMarca;

public class controlFormatoCodBarr implements KeyListener {
	
	pnlRegistraMarca reg;
	int cantidad = 0;
	String codigo ="";
	
	public controlFormatoCodBarr(pnlRegistraMarca reg) {
		// TODO Auto-generated constructor stub
		this.reg= reg;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		codigo = String.valueOf(reg.getTxtScaner().getText());
		cantidad++;
		
		if (cantidad==14) {
			System.out.print(codigo);
			reg.getTxtScaner().setText(codigo.substring(3,8));
			 cantidad = 0;
			 codigo ="";
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
