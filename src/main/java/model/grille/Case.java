package model.grille;

import utils.Utils;

public class Case {
    private final int numCase;
    private final int xCase;
    private final int yCase;
    private int valeur;
    private final int region;
    private EtatCase etatCase;
	private final CandidatsCase candidats = new CandidatsCase();
    
    protected Case(int numCase, int x, int y) {
        this.numCase = numCase;
        this.xCase = x;
        this.yCase = y;
        this.valeur=0;
        this.etatCase = EtatCase.NOT_FOUND;
        this.region = Utils.calculNumeroRegion(numCase);
    }
    protected void setCaseInitiale(int valeur)
    {
        this.valeur = valeur;
        this.etatCase = EtatCase.INITIAL;
        candidats.setAllCandidatsToFalse();
        candidats.setCandidat(valeur);
    }
    protected void initialiserCaseVide() {
        this.valeur = 0;
        this.etatCase =EtatCase.NOT_FOUND;
        candidats.setAllCandidatsToTrue();
    }
    protected void setCaseTrouvee(int valeur)
    {
        this.valeur = valeur;
        this.etatCase = EtatCase.FOUND;
        candidats.setAllCandidatsToFalse();
        candidats.setCandidat(valeur);
    }
    protected int getNumCase() {return numCase;}
    protected int getxCase() {return xCase;}
    protected int getyCase() {return yCase;}
    protected int getValeur() {return valeur;}
    protected int getRegion() {return region;}
    protected EtatCase getEtatCase() {return etatCase;}
	protected void setEtatCase(EtatCase etatCase) {this.etatCase = etatCase;}
    protected boolean isCaseInitiale() {return this.etatCase == EtatCase.INITIAL;}
    protected boolean nEstPasCaseInitiale() {return (this.etatCase != EtatCase.INITIAL);}
    protected boolean isCaseTrouvee() {return this.etatCase == EtatCase.FOUND;}
    protected boolean isCaseATrouver() { return this.etatCase == EtatCase.NOT_FOUND;}
    protected boolean nEstPasCaseTrouvee() {return (this.etatCase != EtatCase.FOUND);}
    protected boolean isCandidat(int rang) {return candidats.isCandidat(rang);}
    protected int getNombreCandidats () {return candidats.getNombreCandidats();}
    protected boolean [] getCandidatsTabBoolean() {return candidats.getCandidats();}
    protected CandidatsCase getCandidats() {return this.candidats;}
    protected boolean contientCandidatUnique() {return candidats.getNombreCandidats() == 1;}
    protected int calculValeurUnique() {return candidats.calculValeurUnique();}
    protected String construitLibelleCandidats() {return candidats.construitLibelleCandidats();}
    protected void elimineCandidat(int valeur) {candidats.elimineCandidat(valeur);}

    public String toString() {
        StringBuilder sbd = new StringBuilder();
        sbd.append("Case ").append(this.numCase);
        if (this.isCaseInitiale()) sbd.append(" initial ").append(this.valeur);
        if (this.isCaseTrouvee()) sbd.append(" found ").append(this.valeur);
        if (this.isCaseATrouver()) sbd.append(" to be found ").append(this.valeur);
        sbd.append(" / ").append(this.getCandidats().toString());
        sbd.append(" |");
        return sbd.toString();
    }
}