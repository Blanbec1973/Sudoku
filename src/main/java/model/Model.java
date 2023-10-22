package model;

import java.util.ArrayList;
import control.Control;
import control.MyProperties;
import model.grille.CaseEnCours;
import model.grille.Grille;
import resolution.*;

public class Model {
	private Control control;
	private Grille grille;
	private ArrayList<MethodeResolution> listeMethodes;
	private Historisation histo = new Historisation();


	public Model(Control control, MyProperties myProperties) {
		this.control = control;
		
        grille =new Grille();
        grille.init(System.getProperty("user.dir")+ myProperties.getProperty("InitialFile"));
        
        histo.historiseGrille(grille);
        
	    listeMethodes = new ArrayList<>();
	    listeMethodes.add(new CandidatUniqueDansCase(this,grille));
	    listeMethodes.add(new CandidatUniqueDansLigne(this,grille));
	    listeMethodes.add(new CandidatUniqueDansColonne(this,grille));
	    listeMethodes.add(new CandidatUniqueDansRegion(this,grille));
	    listeMethodes.add(new PaireCandidats2CasesColonne(this, grille));
	    listeMethodes.add(new PaireConjugueeEnLigne(this, grille));
	    listeMethodes.add(new PaireConjugueeEnColonne(this, grille));
	    listeMethodes.add(new PaireConjugueeEnRegion(this, grille));
	    listeMethodes.add(new AbsenceCandidatEnColonneDansLesAutresRegions(this, grille));
	    listeMethodes.add(new AbsenceCandidatEnLigneDansLesAutresRegions(this, grille));
	    listeMethodes.add(new PaireCandidats2CasesLigne(this, grille));
	    listeMethodes.add(new TripletteCandidatsEnLigne(this, grille));
		listeMethodes.add(new CandidatDansColonneUniqueDuneRegion(this, grille));
	}

	public Grille getGrille() {return grille;}
	
	public void detecteSuivant(boolean goPourChangement) {
		int i =0;
		boolean trouve = false;
		
		do { 
			trouve = listeMethodes.get(i).detecteSuivant(goPourChangement);
			if (trouve) break;
			i+=1;
		} while (i<listeMethodes.size());
        
		if (trouve) {
			if (goPourChangement)
				this.traiteChangement(i);
			else
				control.highlightCase(listeMethodes.get(i).getNumCaseAction());
		}
		else {
			 javax.swing.JOptionPane.showMessageDialog(null,"Fin algorithme !"); 
		}
	}
	
	private void traiteChangement(int numMethodeResolution) {
		// Si une case a été trouvée :
		if (this.listeMethodes.get(numMethodeResolution).isCaseTrouvee()) {
			setValeurCaseEnCours(listeMethodes.get(numMethodeResolution).getSolution(),
					             listeMethodes.get(numMethodeResolution).calculMessageLog());
			return;
		}
		
		//Il faut éliminer un candidat : 
		elimineCandidatCase(listeMethodes.get(numMethodeResolution).getcandidatAEliminer(), 
				            listeMethodes.get(numMethodeResolution).getNumCaseAction(), 
				            listeMethodes.get(numMethodeResolution).calculMessageLog());
	}

	private void setValeurCaseEnCours(int solution, String message) {
		grille.setValeurCaseEnCours(solution);
		grille.elimineCandidatsCaseTrouvee(CaseEnCours.getXSearch(), CaseEnCours.getYSearch(), solution);
		control.demandeRefreshGrille(grille);
		control.demandeAfficheCommande(message);
		control.demandeIncrementRangResolution();
		histo.historiseGrille(grille);
	}
	
	private void elimineCandidatCase(int candidatAEliminer, int numCaseAction, String message) {
		grille.getCase(numCaseAction).elimineCandidat(candidatAEliminer);
		control.demandeRefreshAffichageCase(numCaseAction);
		control.demandeAfficheCommande(message);
		control.demandeIncrementRangResolution();
		histo.historiseGrille(grille);
	}

	public void rechargeDernierHistorique() {
		histo.supprimeDerniereGrille(grille);
	}

}
