package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import modele.Grille;
import modele.Modele;
import modele.Utils;
import vue.MaFenetre;

public class Controle implements ActionListener {
    private MaFenetre fen;
    private Modele modele;
    private static final Logger LOGGER = Logger.getLogger(Controle.class.getPackage().getName() );
    
    public MaFenetre getFen() {return fen;}
    
    public static void main(String[] args) {new Controle();}
        
    public Controle() {
    	LOGGER.log( Level.INFO, "Démarrage Sudoku." );
        // Initialise le modèle :
        modele = new Modele(this);
    	
    	// Initialise la vue : 
    	fen = new MaFenetre();
        fen.getBoutonAvance().addActionListener(this);
        fen.getBoutonExplique().addActionListener(this);
        fen.getBoutonRecule().addActionListener(this);
       
        this.demandeRefreshGrille(modele.getGrille());
        fen.setVisible(true);
    }
    
    public void demandeRefreshGrille(Grille g) {fen.refreshGrilleDisplay(g);}
    
    public void demandeRefreshAffichageCase (int numCase) {
        if (modele.getGrille().getCase(numCase).isCaseInitiale()) {
        	fen.setCaseInitiale(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase), 
        			            String.valueOf(modele.getGrille().getCase(numCase).getValeur()));
        	return;
        }
    	if (modele.getGrille().getCase(numCase).isCaseTrouvee()) {
            fen.setCase(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase), 
            		    String.valueOf(modele.getGrille().getCase(numCase).getValeur()));
        }
        else {
            fen.setCaseCandidats(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase), 
            		             modele.getGrille().getCase(numCase).construitLibelleCandidats());
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
			modele.detecteSuivant(false);
			return;
		}
		if (source == fen.getBoutonExplique()) {
			modele.detecteSuivant(true);
			return;
		}
		if (source == fen.getBoutonRecule() && fen.getRangResolution().getText().equals("0")) {
			javax.swing.JOptionPane.showMessageDialog(null,"Position initiale.");
			return;
		}
		
		if (source == fen.getBoutonRecule()) {
			modele.rechargeDernierHistorique();
			this.demandeRefreshGrille(modele.getGrille());
			this.demandeDecrementRangResolution();
			fen.supprimeDernierLigneLog();
		}
	}

	public void demandeHighlightCase(int numCase) {
		fen.setCaseAvantExplication(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase));
	}
	
    
    public void demandeIncrementRangResolution() {
    	int temp = Integer.parseInt(fen.getRangResolution().getText());
    	temp+=1;
    	fen.getRangResolution().setText(String.valueOf(temp));
    }
    
    public void demandeDecrementRangResolution() {
    	int temp = Integer.parseInt(fen.getRangResolution().getText());
    	temp-=1;
    	fen.getRangResolution().setText(String.valueOf(temp));
    }
    
    
}
