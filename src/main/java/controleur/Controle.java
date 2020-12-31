package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import modele.Grille;
import modele.Modele;
import modele.Utils;
import vue.Vue;

public class Controle implements ActionListener {
    private Vue vue;
    private Modele modele;
    private static final Logger LOGGER = Logger.getLogger(Controle.class.getPackage().getName());
    
    public Vue getVue() {return vue;}
    
    public static void main(String[] args) {new Controle();}
        
    public Controle() {
    	LOGGER.log( Level.INFO, "Démarrage Sudoku." );
        // Initialise le modèle :
        modele = new Modele(this);
    	
    	// Initialise la vue : 
    	vue = new Vue();
        vue.getBoutonAvance().addActionListener(this);
        vue.getBoutonExplique().addActionListener(this);
        vue.getBoutonRecule().addActionListener(this);
        vue.getMenuSave().addActionListener(this);
       
        this.demandeRefreshGrille(modele.getGrille());
        vue.getFenetre().setVisible(true);
    }
    
    public void demandeRefreshGrille(Grille g) {vue.refreshGrilleDisplay(g);}
    
    public void demandeRefreshAffichageCase (int numCase) {
        if (modele.getGrille().getCase(numCase).isCaseInitiale()) {
        	vue.setCaseInitiale(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase), 
        			            String.valueOf(modele.getGrille().getCase(numCase).getValeur()));
        	return;
        }
    	if (modele.getGrille().getCase(numCase).isCaseTrouvee()) {
            vue.setCase(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase), 
            		    String.valueOf(modele.getGrille().getCase(numCase).getValeur()));
        }
        else {
            vue.setCaseCandidats(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase), 
            		             modele.getGrille().getCase(numCase).construitLibelleCandidats());
        }
    }
    
    public void demandeAfficheCommande(String texte) {
    	vue.getLogTextArea().insert(texte+'\n', 0);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();		
		if (source == vue.getBoutonAvance()) {
			modele.detecteSuivant(false);
			return;
		}
		if (source == vue.getBoutonExplique()) {
			modele.detecteSuivant(true);
			return;
		}
		if (source == vue.getBoutonRecule() && vue.getRangResolution().getText().equals("0")) {
			javax.swing.JOptionPane.showMessageDialog(null,"Position initiale.");
			return;
		}
		
		if (source == vue.getBoutonRecule()) {
			modele.rechargeDernierHistorique();
			this.demandeRefreshGrille(modele.getGrille());
			this.demandeDecrementRangResolution();
			vue.supprimeDernierLigneLog();
			return;
		}
		
		if (source == vue.getMenuSave()) {
			modele.sauveGrille();
			return;
		}
	}

	public void demandeHighlightCase(int numCase) {
		vue.setCaseAvantExplication(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase));
	}
	
    
    public void demandeIncrementRangResolution() {
    	int temp = Integer.parseInt(vue.getRangResolution().getText());
    	temp+=1;
    	vue.getRangResolution().setText(String.valueOf(temp));
    }
    
    public void demandeDecrementRangResolution() {
    	int temp = Integer.parseInt(vue.getRangResolution().getText());
    	temp-=1;
    	vue.getRangResolution().setText(String.valueOf(temp));
    }
    
    
}
