package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Modele;
import vue.MaFenetre;

public class Controle implements ActionListener {
    private MaFenetre fen;
    private Modele modele;
    
    public static void main(String[] args) {new Controle();}
        
    public Controle() {
    	// Initialise la vue : 
    	fen = new MaFenetre(this);
        fen.setVisible(true);
        fen.getBoutonAvance().addActionListener(this);
        fen.getBoutonExplique().addActionListener(this);
        
        // Initialise le modèle :
        modele = new Modele(this);
        
	    //Remplissage initiale de la grille de la vue :
	    fen.initialiseGrilleDisplay(modele.getGrille());
       
    }
    
    public void demandeRefreshAffichageCase (int x, int y) {
        if (modele.getGrille().getCase(x, y).isCaseTrouvee()) {
            fen.setCase(x, y, String.valueOf(modele.getGrille().getCase(x, y).getValeur()));
        }
        else {
            fen.setCaseCandidats(x, y, modele.getGrille().getCase(x, y).construitLibelleCandidats());
        }
    }
    
    public void demandeSetFocusCase(int x, int y) {
    	fen.setFocus(x, y);
    }
    
    public void demandeAfficheCommande(String texte) {
    	fen.getLogTextArea().insert(texte+'\n', 0);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();		
		if (source == fen.getBoutonAvance()) {
			//javax.swing.JOptionPane.showMessageDialog(null,"Clic sur avance !");
			modele.detecteSuivant(false);	
		}
		else {
			//javax.swing.JOptionPane.showMessageDialog(null,"Clic sur explique !");
			modele.detecteSuivant(true);
		}
	}

	public void demandeHighlightCase(int x, int y, String texte) {
		fen.setCaseAvantExplication(x, y, texte);
		
	}
    
    

}
