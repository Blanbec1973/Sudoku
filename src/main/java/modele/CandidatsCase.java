package modele;

public class CandidatsCase {
    private boolean[] candidats  = new boolean [10];
    private int nombreCandidats;
    
    public CandidatsCase() {
        for (int i=0;i<10;i++) {this.candidats[i]=true;}
        this.nombreCandidats=10;
        this.elimineCandidat(0);
    }
    
    public CandidatsCase (boolean [] entree) {this.candidats = entree; this.nombreCandidats = this.calculNombreCandidats();}

    public boolean[] getCandidats() {return candidats;}
    public int getNombreCandidats() {return nombreCandidats;}
    public void setCandidats(boolean[] candidats) {this.candidats = candidats; this.nombreCandidats = this.calculNombreCandidats();}
    public void setCandidat(int rang) {this.candidats[rang]=true;this.nombreCandidats+=1;}
    public boolean isCandidat(int rang) {return candidats[rang];} 
    
    public String displayCandidats() {
    	StringBuilder bld = new StringBuilder();
    	for (int i=1;i<10;i++ ) {
    		if (this.isCandidat(i)) bld.append("1"); else bld.append("0");
    	}
    	return bld.toString();
    }
    
    public void setAllCandidatsToFalse() {
        for (int i=0;i<10;i++) {this.candidats[i]=false;}
        this.nombreCandidats=0;
    }
    
    public void elimineCandidat(int valeur) {
        if (!this.candidats[valeur]) {return;}
        this.candidats[valeur]=false; 
        this.nombreCandidats-=1;
    }
    
    public int calculNombreCandidats() {
        int resultat = 0;
        for (int i=0;i<10;i++) {
            if (this.candidats[i]) resultat +=1;
        }
        return resultat;
    }
    
    public String construitLibelleCandidats() {
        StringBuilder bld = new StringBuilder();
        bld.append("<html>");
        bld.append(this.editeCandidat(1));
        bld.append(this.editeCandidat(2));
        bld.append(this.editeCandidat(3));
        bld.append("<br>");
        bld.append(this.editeCandidat(4));
        bld.append(this.editeCandidat(5));
        bld.append(this.editeCandidat(6));
        bld.append("<br>");
        bld.append(this.editeCandidat(7));
        bld.append(this.editeCandidat(8));
        bld.append(this.editeCandidat(9));
        bld.append("</html>");
        return bld.toString();
    }
    
    private String editeCandidat(int candidat) {
    	if (this.isCandidat(candidat)) return String.valueOf(candidat);
    	return " ";
    }

    boolean contientCandidatUnique() {
        return this.nombreCandidats == 1;
    }

    int calculValeurUnique() {
        int i = 1;
        while (!this.isCandidat(i)) {i+=1;}
        return i;
    }
    
}
