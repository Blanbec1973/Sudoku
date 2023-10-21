package vue;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

class ActingOnView {
    private ActingOnView() {
        throw new IllegalStateException("Utility class");
    }
    static void init(Vue vue) {
        vue.getFenetre().setTitle("Sudoku");
        vue.getFenetre().setSize(1200,660);
        vue.getFenetre().setResizable(false);
        vue.getFenetre().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contenu=vue.getFenetre().getContentPane();

        JMenuBar barreMenus = new JMenuBar();
        vue.getFenetre().setJMenuBar(barreMenus);
        JMenu menu1 = new JMenu("Fichier");
        barreMenus.add(menu1);
        menu1.add(vue.getMenuSave());


        Dimension expectedDimension = new Dimension(580, 660);
        vue.getPanGrille().setPreferredSize(expectedDimension);
        vue.getPanGrille().setMaximumSize(expectedDimension);
        vue.getPanGrille().setMinimumSize(expectedDimension);
        vue.getPanGrille().setSize(600, 660);
        vue.getPanGrille().setBackground(Color.cyan);



        vue.getLogTextArea().setEditable(false);

        Dimension d2 = new Dimension(600,660);
        vue.getPanCommande().setPreferredSize(d2);
        vue.getPanCommande().setMaximumSize(d2);
        vue.getPanCommande().setMinimumSize(d2);
        vue.getPanCommande().setSize(600,660);

        JSplitPane split = new JSplitPane(SwingConstants.VERTICAL, vue.getPanGrille(), vue.getPanCommande());
        contenu.add(split);

        for (int y=0;y<9;y++) {
            for (int x=0;x<9;x++) {
                vue.getMaGrilleDisplay()[x][y] = new JButton();
                formateMaCase(vue.getMaGrilleDisplay()[x][y]);
                vue.getMaGrilleDisplay()[x][y].setText(String.valueOf(x)+String.valueOf(y));
                vue.getPanGrille().add(vue.getMaGrilleDisplay()[x][y]);
            }
        }
        vue.getPanGrille().add(vue.getBoutonRecule());
        vue.getPanGrille().add(vue.getRangResolution());
        vue.getPanGrille().add(vue.getBoutonAvance());
        vue.getPanGrille().add(vue.getBoutonExplique());
    }

    private static void formateMaCase(JButton bouton) {
        Dimension expectedDimension = new Dimension(54, 54);
        bouton.setPreferredSize(expectedDimension);
        bouton.setMaximumSize(expectedDimension);
        bouton.setMinimumSize(expectedDimension);
        bouton.setSize(50, 50);
        bouton.setBackground(Color.WHITE);
        Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
        bouton.setBorder(lineborder);
        bouton.setHorizontalTextPosition(SwingConstants.CENTER);
        bouton.setVerticalTextPosition(SwingConstants.CENTER);
        bouton.setHorizontalAlignment(SwingConstants.CENTER);
        bouton.setVerticalAlignment(SwingConstants.CENTER);
    }
}
