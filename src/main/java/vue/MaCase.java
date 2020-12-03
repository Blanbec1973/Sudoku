package vue;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class MaCase extends JButton {
    public MaCase() {
        Dimension expectedDimension = new Dimension(54, 54);
        this.setPreferredSize(expectedDimension);
        this.setMaximumSize(expectedDimension);
        this.setMinimumSize(expectedDimension);
        this.setSize(50, 50);
        this.setBackground(Color.WHITE);
        Border lineborder = BorderFactory.createLineBorder(Color.black, 1); 
        this.setBorder(lineborder);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.CENTER);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
    } 
}
