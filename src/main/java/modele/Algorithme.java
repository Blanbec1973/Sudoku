package modele;

import controleur.Controle;
import vue.MaFenetre;
import java.util.Arrays;

public class Algorithme {
    private boolean estTraitementFini, trouveDansDernierBalayage;
    private int numCase;
    private Controle controle;
    
    public Algorithme(Controle controle) {
        //Initialisation des variables :
        this.controle = controle;
        this.estTraitementFini=false;
        this.trouveDansDernierBalayage=false;
        numCase=0;
    }
    
    public void run(Grille maGrille) {
        while (!this.estTraitementFini) {
            numCase = numCase + 1;
            this.traitementCase(maGrille);
            if (numCase == 81) {
                numCase = 0;
                if (!this.trouveDansDernierBalayage) {this.estTraitementFini = true;}
                else {trouveDansDernierBalayage = false;}
            }
        }
    }
    public boolean isEstTraitementFini() {
        return estTraitementFini;
    }

    private void traitementCase(Grille maGrille) {
        int xSearch, ySearch, solution;
        maGrille.calculXYSearchEtRegion(this.numCase);
        xSearch=maGrille.getxSearch();
        ySearch=maGrille.getySearch();
        //fen.setFocus(xSearch,ySearch);
        if (numCase == 2) {
            System.out.println(String.valueOf(this.numCase));}
        
        //Case initiale ?
        if (maGrille.getCaseEnCours().isCaseInitiale()) {return;}
        
        //Case déjà trouvée ?
        if (maGrille.getCaseEnCours().isCaseTrouvee()) {return;}        
        
        //Valeur unique dans la case ?
        if (maGrille.getCaseEnCours().contientCandidatUnique()) {
             solution = maGrille.getCaseEnCours().calculValeurUnique();
             this.trouveDansDernierBalayage = true;
             maGrille.setValeurCaseEnCours(solution);
             maGrille.elimineCandidatsCaseTrouvee(xSearch, ySearch, solution);
             javax.swing.JOptionPane.showMessageDialog(null,"Valeur unique case");
             return;
        }
        
        //Candidat unique dans la ligne ?
        for (int candidat=1;candidat<10;candidat++) {
            if (maGrille.getCaseEnCours().isCandidat(candidat) &&
                !maGrille.checkPresenceCandidatLigne(candidat, xSearch, ySearch)) {
                this.trouveDansDernierBalayage = true;
                maGrille.setValeurCaseEnCours(candidat);
                maGrille.elimineCandidatsCaseTrouvee(xSearch, ySearch, candidat);
                javax.swing.JOptionPane.showMessageDialog(null,"Candidat unique Ligne : "+String.valueOf(candidat));
                return;
            }
        }
        
        //Candidat unique dans la colonne ?
        for (int candidat=1;candidat<10;candidat++) {
            if (maGrille.getCaseEnCours().isCandidat(candidat) &&
                !maGrille.checkPresenceCandidatColonne(candidat, xSearch, ySearch)) {
                this.trouveDansDernierBalayage = true;
                maGrille.setValeurCaseEnCours(candidat);
                maGrille.elimineCandidatsCaseTrouvee(xSearch, ySearch, candidat);
                javax.swing.JOptionPane.showMessageDialog(null,"Candidat unique Colonne : "+String.valueOf(candidat));
                return;
            }
        }
        
        //Candidat unique dans la région ?
        for (int candidat=1;candidat<10;candidat++) {
            if (maGrille.getCaseEnCours().isCandidat(candidat) &&
                !maGrille.checkPresenceCandidatRegion(candidat, xSearch, ySearch)) {
                this.trouveDansDernierBalayage = true;
                maGrille.getCaseEnCours().setValeurCase(candidat);
                controle.demandeRefreshAffichageCase(xSearch, ySearch);
                maGrille.elimineCandidatsCaseTrouvee(xSearch, ySearch, candidat);
                javax.swing.JOptionPane.showMessageDialog(null,"Candidat unique Région : "+String.valueOf(candidat));
            }
        }
        //Recherche d'absence de candidats en colonne dans les autres régions.
        maGrille.traiteAbsenceCandidatColonneAutreRegion(xSearch, ySearch);
        
        //Recherche de triplette de candidat dans 3 cases en ligne : 
        maGrille.rechercheTripletteCandidatsLigne(xSearch, ySearch);
        
        
        // Recherche de paires de candidats en ligne
        if (maGrille.getCaseEnCours().getNombreCandidats()!=2 ) {return;}
        for (int indBalayage=0;indBalayage<9;indBalayage++) {
            if (indBalayage != xSearch &&
                maGrille.getCase(indBalayage, ySearch).nEstPasCaseInitiale() &&
                maGrille.getCase(indBalayage, ySearch).nEstPasCaseTrouvee() && 
                Arrays.equals(maGrille.getCaseEnCours().getCandidats(), maGrille.getCase(indBalayage, ySearch).getCandidats())) {
                javax.swing.JOptionPane.showMessageDialog(null,"Paire candidats en ligne "+String.valueOf(ySearch+1)+ " : "+
                                                          String.valueOf(maGrille.getCase(xSearch, ySearch).construitLibelleCandidats()));
                
                for (int x2=0;x2<9;x2++) {
                    if (maGrille.getCase(x2, ySearch).nEstPasCaseInitiale() &&
                        maGrille.getCase(x2, ySearch).nEstPasCaseTrouvee() && 
                        x2!=xSearch && x2 !=indBalayage) {
                        maGrille.getCase(x2, ySearch).elimineCandidats(maGrille.getCase(xSearch,ySearch).getCandidats());
                        controle.demandeRefreshAffichageCase(x2, ySearch);
                    }
                }
            }   
        }
        // Recherche de paires de candidats en colonne
        for (int indBalayage=0;indBalayage<9;indBalayage++) {
            if (indBalayage != ySearch &&
                maGrille.getCase(xSearch,indBalayage).nEstPasCaseInitiale() &&
                maGrille.getCase(xSearch,indBalayage).nEstPasCaseTrouvee() && 
                Arrays.equals(maGrille.getCaseEnCours().getCandidats(), maGrille.getCase(xSearch,indBalayage).getCandidats())) {
                javax.swing.JOptionPane.showMessageDialog(null,"Paire candidats en colonne "+String.valueOf(xSearch+1)+ " : "+
                                                          String.valueOf(maGrille.getCase(xSearch, ySearch).construitLibelleCandidats()));
                
                for (int y2=0;y2<9;y2++) {
                    if (maGrille.getCase(xSearch, y2).nEstPasCaseInitiale() &&
                        maGrille.getCase(xSearch, y2).nEstPasCaseTrouvee() && 
                        y2!=ySearch && y2 !=indBalayage) {
                        maGrille.getCase(xSearch, y2).elimineCandidats(maGrille.getCase(xSearch,ySearch).getCandidats());
                        controle.demandeRefreshAffichageCase(xSearch, y2);
                    }
                }
            }   
        }
        //Recherche de paire de candidats en région :
        maGrille.traitePaireCandidatsRegion(xSearch, ySearch);
        

        
        
    }

}
