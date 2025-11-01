package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionListener;

import model.grille.Grille;
import utils.Utils;

public class MyView implements ViewUpdater {
	private final JFrame fenetre = new JFrame();
    private final JButton [][] maGrilleDisplay = new JButton [9][9];
    private final MonPaneauGrille  panGrille = new MonPaneauGrille();
    private final JTextArea logTextArea  = new JTextArea("Grille initiale chargée.",100,100);
    private final JScrollPane  panCommande = new JScrollPane(logTextArea);
    private final JButton boutonAvance= new JButton(">>");
    private final JButton boutonExplique= new JButton("?");
    private final JButton boutonRecule= new JButton("<<");
    private final JLabel rangResolution= new JLabel("0");
    private final JMenuItem menuSave = new JMenuItem("Save as ...");
    private final JMenuItem menuOpen = new JMenuItem("Open...");
    private final JMenuItem menuResolution = new JMenuItem("Résolution");
    public JButton[][] getMaGrilleDisplay() {return maGrilleDisplay;}
    public JScrollPane getPanCommande() {return panCommande;}
    public MonPaneauGrille getPanGrille() {return panGrille;}
    public JFrame getFenetre() {return fenetre;}
    public JTextArea getLogTextArea() {return logTextArea;}
    public JButton getBoutonAvance() {return this.boutonAvance;}
    public JButton getBoutonExplique() {return this.boutonExplique;}
	public AbstractButton getBoutonRecule() {return this.boutonRecule;}
    public JLabel getRangResolution() {return this.rangResolution;}
    public JButton getCase(int x, int y) {return maGrilleDisplay[x][y];}
    public JMenuItem getMenuSave() {return this.menuSave;}
    public JMenuItem getMenuOpen() {
        return this.menuOpen;
    }
    public JMenuItem getMenuResolution() {return this.menuResolution;}
        
    public MyView(){
        this.getFenetre().setTitle("Sudoku");
        this.getFenetre().setSize(1200,660);
        this.getFenetre().setResizable(false);
        this.getFenetre().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container container= this.getFenetre().getContentPane();

        JMenuBar barreMenus = new JMenuBar();
        this.getFenetre().setJMenuBar(barreMenus);
        JMenu menu1 = new JMenu("Fichier");
        barreMenus.add(menu1);
        menu1.add(this.getMenuOpen());
        menu1.add(this.getMenuSave());
        menu1.add(this.getMenuResolution());

        Dimension expectedDimension = new Dimension(580, 660);
        this.getPanGrille().setPreferredSize(expectedDimension);
        this.getPanGrille().setMaximumSize(expectedDimension);
        this.getPanGrille().setMinimumSize(expectedDimension);
        this.getPanGrille().setSize(600, 660);
        this.getPanGrille().setBackground(Color.cyan);

        this.getLogTextArea().setEditable(false);

        Dimension d2 = new Dimension(600,660);
        this.getPanCommande().setPreferredSize(d2);
        this.getPanCommande().setMaximumSize(d2);
        this.getPanCommande().setMinimumSize(d2);
        this.getPanCommande().setSize(600,660);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.getPanGrille(), this.getPanCommande());
        container.add(split);

        for (int y=0;y<9;y++) {
            for (int x=0;x<9;x++) {
                this.getMaGrilleDisplay()[x][y] = new JButton();
                formatMaCase(this.getMaGrilleDisplay()[x][y]);
                this.getMaGrilleDisplay()[x][y].setText(x +String.valueOf(y));
                this.getPanGrille().add(this.getMaGrilleDisplay()[x][y]);
            }
        }
        this.getPanGrille().add(this.getBoutonRecule());
        this.getPanGrille().add(this.getRangResolution());
        this.getPanGrille().add(this.getBoutonAvance());
        this.getPanGrille().add(this.getBoutonExplique());

        this.getPanCommande().getVerticalScrollBar().addAdjustmentListener(e -> {
            this.getPanCommande().revalidate();
            this.getPanCommande().repaint();
        });
    }
    public void registerController(ActionListener controller) {

        boutonAvance.setActionCommand("AVANCE");
        boutonExplique.setActionCommand("EXPLIQUE");
        boutonRecule.setActionCommand("RECULE");
        menuSave.setActionCommand("SAVE");
        menuOpen.setActionCommand("OPEN");
        menuResolution.setActionCommand("RESOLUTION");

        boutonAvance.addActionListener(controller);
        boutonExplique.addActionListener(controller);
        boutonRecule.addActionListener(controller);
        menuSave.addActionListener(controller);
        menuOpen.addActionListener(controller);
        menuResolution.addActionListener(controller);
    }

    public void setCase(int numCase, String value) {
        int x = Utils.calculXsearch(numCase);
        int y = Utils.calculYsearch(numCase);
        maGrilleDisplay[x][y].setFont(new Font("Dialog",Font.BOLD,24));
        maGrilleDisplay[x][y].setText(value);
        maGrilleDisplay[x][y].setOpaque(true);
        maGrilleDisplay[x][y].setBackground(Color.GREEN);
    }
    
    public void setCaseAvantExplication(int x, int y) {
        maGrilleDisplay[x][y].setBackground(Color.YELLOW);
    }
    
     public void setCaseCandidats(int numCase, String value) {
        int x = Utils.calculXsearch(numCase);
        int y = Utils.calculYsearch(numCase);
        maGrilleDisplay[x][y].setFont(new Font("Courrier new",Font.PLAIN,12));
        maGrilleDisplay[x][y].setText(value);
        maGrilleDisplay[x][y].setBackground(Color.WHITE);
    }
    
    public void setCaseInitiale(int numCase, String value){
        int x = Utils.calculXsearch(numCase);
        int y = Utils.calculYsearch(numCase);
        maGrilleDisplay[x][y].setFont(new Font("Dialog",Font.BOLD,24));
    	maGrilleDisplay[x][y].setText(value);
        if (!value.isEmpty()) {
            maGrilleDisplay[x][y].setOpaque(true);
            maGrilleDisplay[x][y].setBackground(Color.GRAY);
        }
    }
        
    @Override
    public void refreshGrilleDisplay(Grille maGrille) {
        String valeurCase;
        for (int numCase=1;numCase<82;numCase++) {
            valeurCase = String.valueOf(maGrille.getValeurCase(numCase));
            if (maGrille.isCaseInitiale(numCase)) {
                this.setCaseInitiale(numCase, valeurCase);
                }
            if (maGrille.isCaseATrouver(numCase)) {
                this.setCaseCandidats(numCase, maGrille.construitLibelleCandidats(numCase));
            }
            if (maGrille.isCaseTrouvee(numCase)) {
            	this.setCase(numCase, valeurCase);
            }
        }
        panGrille.repaint();
    }

    @Override
    public void highlightCase(int numCase) {
        this.setCaseAvantExplication(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase));
    }

    @Override
    public void insertDisplayMessage(String message) {
        logTextArea.insert(message + '\n', 0);
        logTextArea.setCaretPosition(0);
        panCommande.revalidate();
        panCommande.repaint();
    }

    @Override
    public void updateResolutionRank(int delta) {
        int current = Integer.parseInt(rangResolution.getText());
        rangResolution.setText(String.valueOf(current + delta));
    }
    @Override
    public void resetView(String startMessage) {
        rangResolution.setText("0");
        logTextArea.setText(startMessage);
    }
    @Override
    public void updateSingleCase(Grille grille, int numCase) {
        if (grille.isCaseInitiale(numCase)) {
            setCaseInitiale(numCase, String.valueOf(grille.getValeurCase(numCase)));
        } else if (grille.isCaseTrouvee(numCase)) {
            setCase(numCase, String.valueOf(grille.getValeurCase(numCase)));
        } else {
            setCaseCandidats(numCase, grille.construitLibelleCandidats(numCase));
        }
    }
    @Override
    public Color getCaseBackground(int numCase) {
        int x = Utils.calculXsearch(numCase);
        int y = Utils.calculYsearch(numCase);
        return maGrilleDisplay[x][y].getBackground();
    }
    @Override
    public int getCaseValue(int numCase) {
        int x = Utils.calculXsearch(numCase);
        int y = Utils.calculYsearch(numCase);
        return Integer.parseInt(maGrilleDisplay[x][y].getText());
    }
    @Override
    public void removeLastLogLine() {
        int premierSautDeLigne = logTextArea.getText().indexOf("\n");
        if (premierSautDeLigne >= 0) {
            logTextArea.replaceRange("", 0, premierSautDeLigne + 1);
        }
    }
    public void showMessageDialog(Component component, Object object) {
        javax.swing.JOptionPane.showMessageDialog(component, object);
    }
    public String afficheSaveFileDialog(String typeDialog) {
	    String fileName="";
        int returnVal = 0;
    	JFileChooser chooser = new JFileChooser();
	    
    	FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "*.sud", "sud");
	    chooser.setFileFilter(filter);
	    if (typeDialog.equals("SAVE")) {
            returnVal = chooser.showSaveDialog(this.fenetre);}
        if (typeDialog.equals("OPEN")) {
            returnVal = chooser.showOpenDialog(this.fenetre);}

	    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    if (chooser.getSelectedFile().getName().contains("."))
		    	fileName = chooser.getSelectedFile().getAbsolutePath();
		    else
		    	fileName = chooser.getSelectedFile().getAbsolutePath()+".sud";
		}
	    return fileName;
    }
    private void formatMaCase(JButton button) {
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

 