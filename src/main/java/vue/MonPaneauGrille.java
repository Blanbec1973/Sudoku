package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import javax.swing.JPanel;


public class MonPaneauGrille extends JPanel {
	private static final long serialVersionUID = 1772144498190125227L;

	@Override
	public void paintComponent (Graphics g) {
    	super.paintComponent (g);
    	this.dessineGrille(g);
    }
    
    public void dessineGrille (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.gray);
        Stroke stroke = new BasicStroke(3f);
        g2d.setStroke(stroke);
        g2d.drawLine(202, 0, 202, 535);
        g2d.drawLine(378, 0, 378, 535);
        g2d.drawLine(0,179,600,179);
        g2d.drawLine(0,356,600,356);     
    }
}
