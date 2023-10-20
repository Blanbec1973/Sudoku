package vue;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import modele.grille.Grille;

public class Vue {
	private JFrame fenetre;
	private JButton [][] maGrilleDisplay = new JButton [9][9];
    private final MonPaneauGrille panGrille ;
    private final JScrollPane panCommande;
    private TextArea logTextArea;
    private JButton boutonAvance;
    private JButton boutonExplique;
    private JButton boutonRecule;
    private JLabel rangResolution;
    private JMenuItem menuSave;
    
    public JFrame getFenetre() {return fenetre;}
    public TextArea getLogTextArea() {return logTextArea;}
    public JButton getBoutonAvance() {return this.boutonAvance;}
    public JButton getBoutonExplique() {return this.boutonExplique;}
	public AbstractButton getBoutonRecule() {return this.boutonRecule;}
    public JLabel getRangResolution() {return this.rangResolution;}
    public JButton getCase(int x, int y) {return maGrilleDisplay[x][y];}
    public JMenuItem getMenuSave() {return this.menuSave;}
        
    public Vue(){
    	fenetre = new JFrame();
    	fenetre.setTitle("Sudoku");
    	fenetre.setSize(1200,660);
    	fenetre.setResizable(false);
    	fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contenu=fenetre.getContentPane();
        
		JMenuBar barreMenus = new JMenuBar();
		fenetre.setJMenuBar(barreMenus);
		JMenu menu1 = new JMenu("Fichier");
		barreMenus.add(menu1);
		menuSave = new JMenuItem("Save as ...");
		menu1.add(menuSave);
        
        panGrille = new MonPaneauGrille();
        Dimension expectedDimension = new Dimension(580, 660);
        panGrille.setPreferredSize(expectedDimension);
        panGrille.setMaximumSize(expectedDimension);
        panGrille.setMinimumSize(expectedDimension);
        panGrille.setSize(600, 660);
        panGrille.setBackground(Color.cyan);

        
        logTextArea  = new TextArea("Grille initiale chargée.",100,100);
        logTextArea.setEditable(false);
        panCommande = new JScrollPane(logTextArea);
        Dimension d2 = new Dimension(600,660);
        panCommande.setPreferredSize(d2);
        panCommande.setMaximumSize(d2);
        panCommande.setMinimumSize(d2);
        panCommande.setSize(600,660);

        JSplitPane split = new JSplitPane(SwingConstants.VERTICAL, panGrille, panCommande);
        contenu.add(split);

        for (int y=0;y<9;y++) {
            for (int x=0;x<9;x++) {
                maGrilleDisplay[x][y] = new JButton();
                formateMaCase(maGrilleDisplay[x][y]);
                maGrilleDisplay[x][y].setText(String.valueOf(x)+String.valueOf(y));
                panGrille.add(maGrilleDisplay[x][y]);
            }
        }
       
        this.boutonRecule = new JButton("<<");
        panGrille.add(boutonRecule);
        rangResolution = new JLabel("0");
        panGrille.add(rangResolution);
        this.boutonAvance = new JButton(">>");
        panGrille.add(this.boutonAvance);
        this.boutonExplique = new JButton("?");
        panGrille.add(this.boutonExplique);

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
            valeurCase = String.valueOf(maGrille.getCase(numCase).getValeur());
            if (maGrille.getCase(numCase).isCaseInitiale()) {
                this.setCaseInitiale(maGrille.getCase(numCase).getxCase(),maGrille.getCase(numCase).getyCase(), valeurCase); 
                }
            if (maGrille.getCase(numCase).isCaseATrouver()) { 
                this.setCaseCandidats(maGrille.getCase(numCase).getxCase(),maGrille.getCase(numCase).getyCase(), 
                		              maGrille.getCase(numCase).construitLibelleCandidats());
            }
            if (maGrille.getCase(numCase).isCaseTrouvee()) {
            	this.setCase(maGrille.getCase(numCase).getxCase(),maGrille.getCase(numCase).getyCase(), valeurCase);
            }
        }
    }
    
    public void supprimeDernierLigneLog() {
    	int premierSautDeLigne = logTextArea.getText().indexOf("\n");
    	logTextArea.replaceRange("", 0,premierSautDeLigne+1);
    }
    
    private void formateMaCase(JButton bouton) {
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
    
    public String afficheSaveFileDialog() {
	    String fileName="";
    	JFileChooser chooser = new JFileChooser();
	    
    	FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "*.sud", "sud");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showSaveDialog(this.fenetre);
	    
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    if (chooser.getSelectedFile().getName().contains("."))
		    	fileName = chooser.getSelectedFile().getAbsolutePath();
		    else
		    	fileName = chooser.getSelectedFile().getAbsolutePath()+".sud";
		}
	    return fileName;
    }
    
}

 