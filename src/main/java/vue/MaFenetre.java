package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import modele.CaseEnCours;
import modele.Grille;

public class MaFenetre extends JFrame implements ActionListener {
	private MaCase [][] maGrilleDisplay = new MaCase [9][9];
    private final MonPaneauGrille panGrille ;
    private final JScrollPane panCommande;
    private TextArea logTextArea;
    private JButton boutonAvance;
    private JButton boutonExplique;
    private JButton boutonRecule;
    private JLabel rangResolution;
    
    public TextArea getLogTextArea() {return logTextArea;}
    public JButton getBoutonAvance() {return this.boutonAvance;}
    public JButton getBoutonExplique() {return this.boutonExplique;}
	public AbstractButton getBoutonRecule() {return this.boutonRecule;}
    public JLabel getRangResolution() {return this.rangResolution;}

        
    public MaFenetre(){
        setTitle("Sudoku");
        setSize(1200,660);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contenu=getContentPane();
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
                maGrilleDisplay[x][y] = new MaCase();
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
    @Override
    public void actionPerformed (ActionEvent ev) {
            panGrille.repaint();
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

    public void setFocus(int x, int y) {
        maGrilleDisplay[x][y].requestFocus();
    }
        
    public void initialiseGrilleDisplay(Grille maGrille) {
        String valeurCase;
        for (int numCase=1;numCase<82;numCase++) {
            CaseEnCours.setCaseEnCours(numCase);
            valeurCase = String.valueOf(maGrille.getCaseEnCours().getValeur());
            if (maGrille.getCaseEnCours().isCaseInitiale()) {
                this.setCaseInitiale(CaseEnCours.getXSearch(),CaseEnCours.getYSearch(), valeurCase); 
                }
            else { 
                this.setCaseCandidats(CaseEnCours.getXSearch(), CaseEnCours.getYSearch(), maGrille.getCaseEnCours().construitLibelleCandidats());
            }
        }
    }

}

 