package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

class ActingOnView {
    private ActingOnView() {
        throw new IllegalStateException("Utility class");
    }
    static void init(MyView myView) {
        myView.getFenetre().setTitle("Sudoku");
        myView.getFenetre().setSize(1200,660);
        myView.getFenetre().setResizable(false);
        myView.getFenetre().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container container= myView.getFenetre().getContentPane();

        JMenuBar barreMenus = new JMenuBar();
        myView.getFenetre().setJMenuBar(barreMenus);
        JMenu menu1 = new JMenu("Fichier");
        barreMenus.add(menu1);
        menu1.add(myView.getMenuSave());


        Dimension expectedDimension = new Dimension(580, 660);
        myView.getPanGrille().setPreferredSize(expectedDimension);
        myView.getPanGrille().setMaximumSize(expectedDimension);
        myView.getPanGrille().setMinimumSize(expectedDimension);
        myView.getPanGrille().setSize(600, 660);
        myView.getPanGrille().setBackground(Color.cyan);



        myView.getLogTextArea().setEditable(false);

        Dimension d2 = new Dimension(600,660);
        myView.getPanCommande().setPreferredSize(d2);
        myView.getPanCommande().setMaximumSize(d2);
        myView.getPanCommande().setMinimumSize(d2);
        myView.getPanCommande().setSize(600,660);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, myView.getPanGrille(), myView.getPanCommande());
        container.add(split);

        for (int y=0;y<9;y++) {
            for (int x=0;x<9;x++) {
                myView.getMaGrilleDisplay()[x][y] = new JButton();
                formatMaCase(myView.getMaGrilleDisplay()[x][y]);
                myView.getMaGrilleDisplay()[x][y].setText(x +String.valueOf(y));
                myView.getPanGrille().add(myView.getMaGrilleDisplay()[x][y]);
            }
        }
        myView.getPanGrille().add(myView.getBoutonRecule());
        myView.getPanGrille().add(myView.getRangResolution());
        myView.getPanGrille().add(myView.getBoutonAvance());
        myView.getPanGrille().add(myView.getBoutonExplique());
    }

    private static void formatMaCase(JButton button) {
        Dimension expectedDimension = new Dimension(54, 54);
        button.setPreferredSize(expectedDimension);
        button.setMaximumSize(expectedDimension);
        button.setMinimumSize(expectedDimension);
        button.setSize(50, 50);
        button.setBackground(Color.WHITE);
        Border lineBorder = BorderFactory.createLineBorder(Color.black, 1);
        button.setBorder(lineBorder);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
    }
}
