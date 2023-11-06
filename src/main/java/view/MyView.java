package view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import model.grille.Grille;

public class MyView {
	private final JFrame fenetre = new JFrame();
    private final JButton [][] maGrilleDisplay = new JButton [9][9];
    private final MonPaneauGrille  panGrille = new MonPaneauGrille();
    private final TextArea logTextArea  = new TextArea("Grille initiale chargée.",100,100);
    private final JScrollPane  panCommande = new JScrollPane(logTextArea);
    private final JButton boutonAvance= new JButton(">>");
    private final JButton boutonExplique= new JButton("?");
    private final JButton boutonRecule= new JButton("<<");
    private final JLabel rangResolution= new JLabel("0");
    private final JMenuItem menuSave = new JMenuItem("Save as ...");
    private final JMenuItem menuOpen = new JMenuItem("Open...");

    public JButton[][] getMaGrilleDisplay() {return maGrilleDisplay;}
    public JScrollPane getPanCommande() {return panCommande;}
    public MonPaneauGrille getPanGrille() {return panGrille;}
    public JFrame getFenetre() {return fenetre;}
    public TextArea getLogTextArea() {return logTextArea;}
    public JButton getBoutonAvance() {return this.boutonAvance;}
    public JButton getBoutonExplique() {return this.boutonExplique;}
	public AbstractButton getBoutonRecule() {return this.boutonRecule;}
    public JLabel getRangResolution() {return this.rangResolution;}
    public JButton getCase(int x, int y) {return maGrilleDisplay[x][y];}
    public JMenuItem getMenuSave() {return this.menuSave;}
        
    public MyView(){
    	ActingOnView.init(this);
    }
    
    public void setCase(int x, int y, String value) {
        maGrilleDisplay[x][y].setFont(new Font("Dialog",Font.BOLD,24));
        maGrilleDisplay[x][y].setText(value);
        maGrilleDisplay[x][y].setOpaque(true);
        maGrilleDisplay[x][y].setBackground(Color.GREEN);
    }
    
    public void setCaseAvantExplication(int x, int y) {
        maGrilleDisplay[x][y].setBackground(Color.YELLOW);
    }
    
     public void setCaseCandidats(int x, int y, String value) {
        maGrilleDisplay[x][y].setFont(new Font("Courrier new",Font.PLAIN,12));
        maGrilleDisplay[x][y].setText(value);
        maGrilleDisplay[x][y].setBackground(Color.WHITE);
    }
    
    public void setCaseInitiale(int x, int y, String value){
        maGrilleDisplay[x][y].setFont(new Font("Dialog",Font.BOLD,24));
    	maGrilleDisplay[x][y].setText(value);
        if (!value.isEmpty()) {
            maGrilleDisplay[x][y].setOpaque(true);
            maGrilleDisplay[x][y].setBackground(Color.GRAY);
        }
    }
        
    public void refreshGrilleDisplay(Grille maGrille) {
        String valeurCase;
        for (int numCase=1;numCase<82;numCase++) {
            valeurCase = String.valueOf(maGrille.getValeurCase(numCase));
            if (maGrille.isCaseInitiale(numCase)) {
                this.setCaseInitiale(maGrille.getxCase(numCase),maGrille.getyCase(numCase), valeurCase);
                }
            if (maGrille.isCaseATrouver(numCase)) {
                this.setCaseCandidats(maGrille.getxCase(numCase),maGrille.getyCase(numCase),
                		              maGrille.construitLibelleCandidats(numCase));
            }
            if (maGrille.isCaseTrouvee(numCase)) {
            	this.setCase(maGrille.getxCase(numCase),maGrille.getyCase(numCase), valeurCase);
            }
        }
    }
    
    public void supprimeDernierLigneLog() {
    	int premierSautDeLigne = logTextArea.getText().indexOf("\n");
    	logTextArea.replaceRange("", 0,premierSautDeLigne+1);
    }
    

    
    public String afficheSaveFileDialog(String typeDialog) {
	    String fileName="";
        int returnVal = 0;
    	JFileChooser chooser = new JFileChooser();
	    
    	FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "*.sud", "sud");
	    chooser.setFileFilter(filter);
	    if (typeDialog == "SAVE") {
            returnVal = chooser.showSaveDialog(this.fenetre);}
        if (typeDialog == "OPEN") {
            returnVal = chooser.showOpenDialog(this.fenetre);}

	    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    if (chooser.getSelectedFile().getName().contains("."))
		    	fileName = chooser.getSelectedFile().getAbsolutePath();
		    else
		    	fileName = chooser.getSelectedFile().getAbsolutePath()+".sud";
		}
	    return fileName;
    }

    public JMenuItem getMenuOpen() {
        return this.menuOpen;
    }

}

 