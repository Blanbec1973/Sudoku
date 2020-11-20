package controleur;

import modele.Algorithme;
import modele.Grille;
import vue.MaFenetre;

public class Controle {
    private Grille maGrille;
    private MaFenetre fen;
    
    public static void main(String[] args) {
        new Controle();
    }
        
    public Controle() {
    	fen = new MaFenetre(this);
        fen.setVisible(true);
        
        maGrille =new Grille(this);
	    maGrille.init("C:\\Users\\heynerr\\Documents\\W-Workspace\\Sudoku\\init67-41.sud");
        fen.initialiseGrilleDisplay(maGrille);
        
        Algorithme monAlgo;
        monAlgo=new Algorithme(this);
        monAlgo.run(maGrille);
        
        javax.swing.JOptionPane.showMessageDialog(null,"Fin algorithme !");
    }
    
    public void demandeRefreshAffichageCase (int x, int y) {
        if (maGrille.getCase(x, y).isCaseTrouvee()) {
            fen.setCase(x, y, String.valueOf(maGrille.getCase(x, y).getValeur()));
        }
        else {
            fen.setCaseCandidats(x, y, maGrille.getCase(x, y).construitLibelleCandidats());
        }
    }
    
    public void demandeSetFocusCase(int x, int y) {
    	fen.setFocus(x, y);
    }
    
    public void demandeAfficheCommande(String texte) {
    	fen.getLogTextArea().insert(texte+'\n', 0);
    }
    
    

}
