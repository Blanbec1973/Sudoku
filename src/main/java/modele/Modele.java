package modele;

import java.util.ArrayList;
import controleur.Controle;
import controleur.MesProprietes;
import modele.grille.CaseEnCours;
import modele.grille.Grille;
import resolution.*;

public class Modele {
	private Controle controle;
	private Grille grille;
	private ArrayList<MethodeResolution> listeMethodes;
	private Historisation histo = new Historisation();


	public Modele(Controle controle, MesProprietes mesProprietes) {
		this.controle = controle;
		
        grille =new Grille();
        InitialiseurDeGrille initialiseurDeGrille  = new InitialiseurDeGrille(grille);
        initialiseurDeGrille.init(System.getProperty("user.dir")+mesProprietes.getFichierInitial());
        initialiseurDeGrille.calculTousLesCandidats();
        
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
				controle.demandeHighlightCase(listeMethodes.get(i).getNumCaseAction());
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
		controle.demandeRefreshGrille(grille);
		controle.demandeAfficheCommande(message);
		controle.demandeIncrementRangResolution();
		histo.historiseGrille(grille);
	}
	
	private void elimineCandidatCase(int candidatAEliminer, int numCaseAction, String message) {
		grille.getCase(numCaseAction).elimineCandidat(candidatAEliminer);
		controle.demandeRefreshAffichageCase(numCaseAction);
		controle.demandeAfficheCommande(message);
		controle.demandeIncrementRangResolution();		
		histo.historiseGrille(grille);
	}

	public void rechargeDernierHistorique() {
		histo.supprimeDerniereGrille(grille);
	}

}
