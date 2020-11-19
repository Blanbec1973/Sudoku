package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import javax.swing.JPanel;


public class MonPaneauGrille extends JPanel {
    public void paintComponent (Graphics g) {
	super.paintComponent (g);
        this.dessineGrille(g);
	//g.drawRect(10,10,240,240);
    }
    
    public void dessineGrille (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.gray);
        Stroke stroke = new BasicStroke(3f);
        g2d.setStroke(stroke);
        g2d.drawLine(102, 0, 102, 300);
        g2d.drawLine(198, 0, 198, 300);
        g2d.drawLine(0,99,300,99);
        g2d.drawLine(0,195,300,195);
        
    }
}
