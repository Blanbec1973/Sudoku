package vue;

import controleur.Controle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import modele.CaseEnCours;
import modele.Grille;

public class MaFenetre extends JFrame implements ActionListener {
    private Controle controle;
	private MaCase [][] maGrilleDisplay = new MaCase [9][9];
    private final MonPaneauGrille panGrille ;
    private final JScrollPane panCommande;
    private TextArea logTextArea;
    private JButton boutonAvance;
    private JButton boutonExplique;
    private JLabel rangResolution;
    
    public TextArea getLogTextArea() {return logTextArea;}
    public JButton getBoutonAvance() {return this.boutonAvance;}
    public JButton getBoutonExplique() {return this.boutonExplique;}
    public JLabel getRangResolution() {return this.rangResolution;}

        
    public MaFenetre(Controle controle){
        this.controle = controle;
        setTitle("Sudoku");
        setSize(1200,660);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contenu=getContentPane();
        // création panneau pour le dessin :
        panGrille = new MonPaneauGrille();
        Dimension expectedDimension = new Dimension(580, 660);
        panGrille.setPreferredSize(expectedDimension);
        panGrille.setMaximumSize(expectedDimension);
        panGrille.setMinimumSize(expectedDimension);
        panGrille.setSize(600, 660);
        panGrille.setBackground(Color.cyan);

        
        logTextArea  = new TextArea("Grille initiale chargée.",100,100);
        logTextArea.setEditable(false);
        //panCommande.add(logTextArea);
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
                //System.out.println("xy :"+x+y);
                maGrilleDisplay[x][y].setText(String.valueOf(x)+String.valueOf(y));
                panGrille.add(maGrilleDisplay[x][y]);
            }
        }
       
        JButton boutonRecule = new JButton("<<");
        panGrille.add(boutonRecule);
        rangResolution = new JLabel("0");
        panGrille.add(rangResolution);
        this.boutonAvance = new JButton(">>");
        panGrille.add(this.boutonAvance);
        this.boutonExplique = new JButton("?");
        panGrille.add(this.boutonExplique);
        
        // création bouton rectangle :
        //rectangle = new JButton ("Rectangle");
        //contenu.add(rectangle,"North");
        //rectangle.addActionListener(this);
        // création bouton ovale :
        //ovale = new JButton ("Ovale");
        //contenu.add(ovale,"South");
        //ovale.addActionListener(this);

    }
    @Override
    public void actionPerformed (ActionEvent ev) {
            //if (ev.getSource()==rectangle) panGrille.setRectangle();
            //if (ev.getSource()==ovale) panGrille.setOvale();
            //System.out.println("avant paint");
            panGrille.repaint(); //pour forcer la peinture du panneau maintenant
    }
    
    public void setCase(int x, int y, String value) {
        maGrilleDisplay[x][y].setFont(new Font("Dialog",Font.BOLD,24));
        maGrilleDisplay[x][y].setText(value);
        maGrilleDisplay[x][y].setOpaque(true);
        maGrilleDisplay[x][y].setBackground(Color.GREEN);
    }
    
    public void setCaseAvantExplication(int x, int y, String value) {
//        maGrilleDisplay[x][y].setFont(new Font("Dialog",Font.BOLD,24));
//        maGrilleDisplay[x][y].setText(value);
//        maGrilleDisplay[x][y].setOpaque(true);
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
        //System.out.println(maGrilleDisplay[x][y].getFont().getFontName()+" "+
        //                   maGrilleDisplay[x][y].getFont().getSize());
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
            /*if (numCase == 80) {
                System.err.println("Stop");
            }*/
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

 