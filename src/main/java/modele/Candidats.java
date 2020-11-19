
package modele;

public class Candidats {
    private boolean candidats [] = new boolean [10];

    private int nombreCandidats;
    
    public Candidats() {
        for (int i=0;i<10;i++) {this.candidats[i]=true;}
        this.nombreCandidats=10;
        this.elimineCandidat(0);
    }
    
    public Candidats (boolean [] entree) {this.candidats = entree; this.nombreCandidats = this.calculNombreCandidats();}

    public boolean[] getCandidats() {return candidats;}
    public int getNombreCandidats() {return nombreCandidats;}
    public void setCandidats(boolean[] candidats) {this.candidats = candidats; this.nombreCandidats = this.calculNombreCandidats();}
    public boolean isCandidat(int rang) {return candidats[rang];}
    
    public void elimineCandidat(int valeur) {
        if (!this.candidats[valeur]) {return;}
        this.candidats[valeur]=false; 
        this.nombreCandidats-=1;
    }
    
    void elimineCandidats(boolean[] candidatsAEliminer) {
        for (int ind=1;ind<10;ind++) {
            if (candidatsAEliminer[ind]) this.elimineCandidat(ind);
        }
    }
    
    public int calculNombreCandidats() {
        int resultat = 0;
        for (int i=0;i<10;i++) {
            if (this.candidats[i]) resultat +=1;
        }
        return resultat;
    }
    
    public String construitLibelleCandidats() {
        String libelle;
        if (candidats[1]) {libelle="1";} else {libelle = "";}
        if (candidats[2]) {libelle=libelle+"2";} 
        if (candidats[3]) {libelle=libelle+"3";} 
        if (candidats[4]) {libelle=libelle+"4";} 
        if (candidats[5]) {libelle=libelle+"5";} 
        if (candidats[6]) {libelle=libelle+"6";} 
        if (candidats[7]) {libelle=libelle+"7";} 
        if (candidats[8]) {libelle=libelle+"8";} 
        if (candidats[9]) {libelle=libelle+"9";} 
        return libelle;
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
