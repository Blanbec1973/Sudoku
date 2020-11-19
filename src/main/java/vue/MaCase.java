/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class MaCase extends JButton {
    public MaCase() {
        Dimension expectedDimension = new Dimension(27, 27);
        this.setPreferredSize(expectedDimension);
        this.setMaximumSize(expectedDimension);
        this.setMinimumSize(expectedDimension);
        this.setSize(25, 25);
        this.setBackground(Color.WHITE);
        Border lineborder = BorderFactory.createLineBorder(Color.black, 1); 
        this.setBorder(lineborder);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.CENTER);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
        //System.out.println("Case : "+this.getFont()+" "+this.getFont().getSize());
    }
    
   
}
