package model.grille;

import utils.Utils;

class Case {
    private int numCase;
    private int xCase;
    private int yCase;
    private int valeur;
    private int region;
    private EtatCase etatCase;
	private final CandidatsCase candidats = new CandidatsCase();
    
    protected Case(int numCase, int x, int y) {
        this.setNumCase(numCase);
        this.setX(x);
        this.setY(y);
        this.etatCase = EtatCase.NOT_FOUNDED;
        this.setRegion(Utils.calculNumeroRegion(numCase));
    }
    
    protected int getNumCase() {return numCase;}
    private void setNumCase(int numCase) {this.numCase = numCase;}
    protected int getxCase() {return xCase;}
    private void setX(int xCase) {this.xCase = xCase;}
    protected int getyCase() {return yCase;}
    private void setY(int yCase) {this.yCase = yCase;}
    protected int getValeur() {return valeur;}
    protected void setValeur(int valeur) {this.valeur=valeur;}
    protected void setValeurCase(int valeur)
        {
            this.valeur = valeur;
            this.etatCase = EtatCase.FOUNDED;
            candidats.setAllCandidatsToFalse();
        }
    protected int getRegion() {return region;}
    private void setRegion(int region) {this.region = region;}
    protected EtatCase getEtatCase() {return etatCase;}
	protected void setEtatCase(EtatCase etatCase) {this.etatCase = etatCase;}
    protected boolean isCaseInitiale() {return this.etatCase == EtatCase.INITIAL;}
    protected boolean nEstPasCaseInitiale() {return (this.etatCase != EtatCase.INITIAL);}
    protected void setCaseInitiale(int valeur)
        {
            this.valeur = valeur;
            this.etatCase = EtatCase.INITIAL;
            candidats.setAllCandidatsToFalse();
        }
    protected boolean isCaseTrouvee() {return this.etatCase == EtatCase.FOUNDED;}
    protected boolean isCaseATrouver() { return this.etatCase == EtatCase.NOT_FOUNDED;}
    protected boolean nEstPasCaseTrouvee() {return (this.etatCase != EtatCase.FOUNDED);}
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