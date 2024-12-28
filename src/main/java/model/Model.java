package model;

import control.MyProperties;
import model.grille.CaseEnCours;
import model.grille.Grille;
import model.grille.Historisation;
import resolution.*;

import java.util.ArrayList;

public class Model {
	private final ModelListener modelListener;
	private final MessageManager messageManager;
	private final Grille grille;
	private final ArrayList<MethodeResolution> listeMethodes;
	private final Historisation historizer = new Historisation();

	public Model(ModelListener modelListener, MyProperties myProperties) {
		this.modelListener = modelListener;
		messageManager = new MessageManager(myProperties);
		
        grille =new Grille();
        grille.init(System.getProperty("user.dir")+ myProperties.getProperty("InitialFile"));
        
        historizer.historiseGrille(grille);
        
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
		listeMethodes.add(new CandidatDansLigneUniqueDuneRegion(this,grille));
		listeMethodes.add(new TripletteCandidatsEnColonne(this,grille	));
	}

	public Grille getGrille() {return grille;}
	
	public void detecteSuivant(boolean goPourChangement) {
		int i =0;
		boolean trouve;
		
		do { 
			trouve = listeMethodes.get(i).detecteSuivant(goPourChangement);
			if (trouve) break;
			i+=1;
		} while (i<listeMethodes.size());
        
		if (trouve) {
			if (goPourChangement)
				this.traiteChangement(i);
			else
				modelListener.onEventFromModel(grille,
						new EventFromModel(EventFromModelType.HIGHLIGHT_CASE,
								listeMethodes.get(i).getNumCaseAction(),
								""));
		}
		else {
			 javax.swing.JOptionPane.showMessageDialog(null,"Fin algorithme !"); 
		}
	}
	
	private void traiteChangement(int numMethodeResolution) {
		// Box is founded :
		if (this.listeMethodes.get(numMethodeResolution).isCaseTrouvee()) {
			setValeurCaseEnCours(listeMethodes.get(numMethodeResolution).getSolution(),
					messageManager.createMessageSolution(listeMethodes.get(numMethodeResolution)));
			return;
		}
		
		// A candidate must be eliminated :
		elimineCandidatCase(listeMethodes.get(numMethodeResolution).getCandidatAEliminer(),
				            listeMethodes.get(numMethodeResolution).getNumCaseAction(),
				messageManager.createMessageElimination(listeMethodes.get(numMethodeResolution)));
	}

	private void setValeurCaseEnCours(int solution, String message) {
		grille.setValeurCaseEnCours(solution);
		grille.elimineCandidatsCaseTrouvee(CaseEnCours.getX(), CaseEnCours.getY(), solution);

		modelListener.onEventFromModel(grille,
		      new EventFromModel(EventFromModelType.AJOUT_SOLUTION,CaseEnCours.getNumCase(),message));

		historizer.historiseGrille(grille);
	}
	
	private void elimineCandidatCase(int candidatAEliminer, int numCaseAction, String message) {
		grille.elimineCandidat(numCaseAction, candidatAEliminer);

		modelListener.onEventFromModel(grille,
				new EventFromModel(EventFromModelType.ELIMINE_CANDIDAT, numCaseAction, message));

		historizer.historiseGrille(grille);
	}

	public void reloadLastHistoricization() {
		historizer.supprimeDerniereGrille(grille);
	}

    public void reload(String fileName) {
		grille.init(fileName);
		historizer.reloadGrille(grille);
    }
}
