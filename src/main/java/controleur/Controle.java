package controleur;

import model.Model;
import model.Utils;
import model.grille.Grille;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.MyView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controle implements ActionListener {
    private final MyView myView;
    private final Model model;
	private static final Logger logger = LogManager.getLogger(Controle.class);
	public MyView getVue() {return myView;}
    public static void main(String[] args) {new Controle();}
        
    public Controle() {
		logger.info("Démarrage Sudoku.");
        // Initialise le modèle :
		MyProperties myProperties = new MyProperties(System.getProperty("user.dir") + "/src/main/resources/config.properties");
		model = new Model(this, myProperties);
    	
    	// Initialise la vue : 
    	myView = new MyView();
        myView.getBoutonAvance().addActionListener(this);
        myView.getBoutonExplique().addActionListener(this);
        myView.getBoutonRecule().addActionListener(this);
        myView.getMenuSave().addActionListener(this);
       
        this.demandeRefreshGrille(model.getGrille());
        myView.getFenetre().setVisible(true);
    }
    
    public void demandeRefreshGrille(Grille g) {
		myView.refreshGrilleDisplay(g);}
    
    public void demandeRefreshAffichageCase (int numCase) {
        if (model.getGrille().getCase(numCase).isCaseInitiale()) {
        	myView.setCaseInitiale(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase),
        			            String.valueOf(model.getGrille().getCase(numCase).getValeur()));
        	return;
        }
    	if (model.getGrille().getCase(numCase).isCaseTrouvee()) {
            myView.setCase(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase),
            		    String.valueOf(model.getGrille().getCase(numCase).getValeur()));
        }
        else {
            myView.setCaseCandidats(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase),
            		             model.getGrille().getCase(numCase).construitLibelleCandidats());
        }
    }

    public void demandeAfficheCommande(String texte) {
    	myView.getLogTextArea().insert(texte+'\n', 0);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO : Externaliser le gestionnaire d'évènements du contrôleur
		Object source = e.getSource();		
		if (source == myView.getBoutonAvance()) {
			model.detecteSuivant(false);
			return;
		}
		if (source == myView.getBoutonExplique()) {
			model.detecteSuivant(true);
			return;
		}
		if (source == myView.getBoutonRecule() && myView.getRangResolution().getText().equals("0")) {
			javax.swing.JOptionPane.showMessageDialog(null,"Position initiale.");
			return;
		}
		
		if (source == myView.getBoutonRecule()) {
			model.rechargeDernierHistorique();
			this.demandeRefreshGrille(model.getGrille());
			this.demandeDecrementRangResolution();
			myView.supprimeDernierLigneLog();
			return;
		}
		
		if (source == myView.getMenuSave()) {
			String fileName = myView.afficheSaveFileDialog();
			if (!fileName.isEmpty()) SauveurDeGrille.saveGrille(model.getGrille(),fileName);
		}
	}

	public void highlightCase(int numCase) {
		myView.setCaseAvantExplication(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase));
	}

    public void demandeIncrementRangResolution() {
    	int temp = Integer.parseInt(myView.getRangResolution().getText());
    	temp+=1;
    	myView.getRangResolution().setText(String.valueOf(temp));
    }

    public void demandeDecrementRangResolution() {
    	int temp = Integer.parseInt(myView.getRangResolution().getText());
    	temp-=1;
    	myView.getRangResolution().setText(String.valueOf(temp));
    }
}
