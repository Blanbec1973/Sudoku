package modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grille {
    private Case [][] mesCases = new Case [9][9];
    private Modele modele;
    private List<Integer> casesAtrouver;
        
    public Grille(Modele modele) {
        this.modele = modele;
        int numcase = 0;
        for (int y=0;y<9;y++) {
            for (int x=0;x<9;x++) {
                numcase = numcase +1;
                mesCases[x][y] = new Case(numcase, x, y);
            }
        }
        casesAtrouver = new ArrayList<>();
    }
     
    public Case getCase(int x, int y) {return mesCases[x][y];}
    public Case getCaseEnCours() {return mesCases[CaseEnCours.getXSearch()][CaseEnCours.getYSearch()];}
    public List<Integer> getCasesAtrouver() {return casesAtrouver;}
    
    public void setValeurCaseEnCours(int solution) {
        this.getCaseEnCours().setValeurCase(solution);
        this.elimineCandidatsCaseTrouvee(CaseEnCours.getXSearch(), CaseEnCours.getYSearch(), solution);
        casesAtrouver.remove(casesAtrouver.indexOf(Utils.calculNumCase(CaseEnCours.getXSearch(), CaseEnCours.getYSearch())));
    }        

   
    public void displayCasesAtrouver() {
    	int i = 0;
    	while (i<this.getCasesAtrouver().size()) {
    		System.out.println("i="+i+" ==>"+ this.getCasesAtrouver().get(i++));
    	}
    }
    
    public void display() {
    	StringBuilder bld = new StringBuilder();
        for (int y=0; y<9; y++) {
            for (int x=0;x<9;x++) {
                bld.append(this.getCase(x,y).getValeur());   
            }
            System.out.println(bld.toString());
            bld.setLength(0);
        }
    }
    
    public boolean checkPresenceCandidatLigne(int valeur, int x, int numLigne) {
        for (int i=0;i<9;i++) {
            if (mesCases[i][numLigne].nEstPasCaseInitiale() && 
                mesCases[i][numLigne].nEstPasCaseTrouvee() &&
                i!=x && this.mesCases[i][numLigne].isCandidat(valeur)) {return true;}
        }
        return false;
    }
    
    public boolean checkPresenceCandidatColonne(int valeur, int numcol, int y) {
        for (int i=0;i<9;i++) {
            if (mesCases[numcol][i].nEstPasCaseInitiale() && 
                mesCases[numcol][i].nEstPasCaseTrouvee() &&
                i!=y && this.mesCases[numcol][i].isCandidat(valeur)) {return true;}
        }
        return false;
    }

    public boolean checkPresenceCandidatRegion(int indiceCandidat, int x, int y) { 
        for (int abs=CaseEnCours.getxRegion();abs<CaseEnCours.getxRegion()+3;abs++) {
            for (int ord=CaseEnCours.getyRegion();ord<CaseEnCours.getyRegion()+3;ord++) {
                if (mesCases[abs][ord].nEstPasCaseInitiale() && 
                    mesCases[abs][ord].nEstPasCaseTrouvee() &&
                    (x!=abs || y!=ord) && 
                    this.mesCases[abs][ord].isCandidat(indiceCandidat)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void elimineCandidatsCaseTrouvee(int x, int y, int solution) {
        //Case x, y trouvée ==> élimination du candidats dans ligne/colonne/région.
        //Elimination dans ligne : 
        for (int i=0;i<9;i++) {
            mesCases[i][y].elimineCandidat(solution);
            if (mesCases[i][y].nEstPasCaseInitiale() && mesCases[i][y].nEstPasCaseTrouvee()) {
            	modele.getControle().demandeRefreshAffichageCase(i, y);
            }
        }
        
        //Elimination dans colonne : 
        for (int i=0;i<9;i++) {
            mesCases[x][i].elimineCandidat(solution);
            if (mesCases[x][i].nEstPasCaseInitiale() && mesCases[x][i].nEstPasCaseTrouvee()) {
            	modele.getControle().demandeRefreshAffichageCase(x, i);
            }
        }
        
        //Elimination dans région : 
        for (int abs=CaseEnCours.getxRegion();abs<CaseEnCours.getxRegion()+3;abs++) {
            for (int ord=CaseEnCours.getyRegion();ord<CaseEnCours.getyRegion()+3;ord++) {
                mesCases[abs][ord].elimineCandidat(solution);
                if (mesCases[abs][ord].nEstPasCaseInitiale() && mesCases[abs][ord].nEstPasCaseTrouvee()) {
                	modele.getControle().demandeRefreshAffichageCase(abs, ord);
                }
            }
        }
    }

	public boolean checkPresenceCandidatRegionSaufColonne(int candidat, int colonne) {
        for (int abs=CaseEnCours.getxRegion();abs<CaseEnCours.getxRegion()+3;abs++) {
            for (int ord=CaseEnCours.getyRegion();ord<CaseEnCours.getyRegion()+3;ord++) {
                if (abs!= colonne && mesCases[abs][ord].nEstPasCaseInitiale() && 
                		             mesCases[abs][ord].nEstPasCaseTrouvee()) {
                    if (mesCases[abs][ord].isCandidat(candidat)) return true;
                    
                }     
            }
        }  
		return false;
	}
    
    public void elimineCandidatRegionSaufColonne(int candidat, int colonne) {
        for (int abs=CaseEnCours.getxRegion();abs<CaseEnCours.getxRegion()+3;abs++) {
            for (int ord=CaseEnCours.getyRegion();ord<CaseEnCours.getyRegion()+3;ord++) {
                if (abs!= colonne && mesCases[abs][ord].nEstPasCaseInitiale() && mesCases[abs][ord].nEstPasCaseTrouvee()) {
                    mesCases[abs][ord].elimineCandidat(candidat);
                    modele.getControle().demandeRefreshAffichageCase(abs, ord);
                }     
            }
        }   
    }
    
    void elimineCandidatsLigneSaufColonnes(CandidatsCase candidats, int numligne, int [] colonnesANePasPrendreEnCompte) {
        for (int i=0;i<9;i++) {
            if (this.getCase(i, numligne).nEstPasCaseInitiale() &&
                this.getCase(i, numligne).nEstPasCaseTrouvee() &&
                !Arrays.toString(colonnesANePasPrendreEnCompte).contains(String.valueOf(i))) {
                this.getCase(i,numligne).elimineCandidats(candidats.getCandidats());
                modele.getControle().demandeRefreshAffichageCase(i,numligne);
            }   
        }
    }
    
    void traiteAbsenceCandidatColonneAutreRegion(int xSearch, int ySearch) {
        boolean candidatTrouve;
        
        for (int candidat=1;candidat<10;candidat++) {
            if (this.getCaseEnCours().isCandidat(candidat)) {
                candidatTrouve=false;
                for (int i=0;i<9;i++) {
                    if (this.getCase(xSearch, ySearch).getRegion() != this.getCase(xSearch,i).getRegion() &&
                        this.getCase(xSearch, i).nEstPasCaseInitiale() &&
                        this.getCase(xSearch, i).nEstPasCaseTrouvee() &&
                        this.getCase(xSearch,i).isCandidat(candidat)) {
                        candidatTrouve = true;
                    }
                }    
                if (!candidatTrouve) {
                    javax.swing.JOptionPane.showMessageDialog(null,"Candidat "
                                                                   +String.valueOf(candidat)
                                                                   + " dans colonne "
                                                                   +String.valueOf(xSearch+1)
                                                                   +"uniquement dans région"
                                                                   +String.valueOf(this.getCaseEnCours().getRegion())); 
                    this.elimineCandidatRegionSaufColonne(candidat, xSearch);
                }        
            }    
        }
    }

    void rechercheTripletteCandidatsLigne(int xSearch, int ySearch) {
        int nombreCaseATrouver = Utils.calculNombreCaseATrouverDansLigne(this, ySearch);
        int x2=0;
        int x3=0;
        if (nombreCaseATrouver<=3) {
            return;
        }
        while (x2<9) {
            if (x2!=xSearch && this.getCase(x2,ySearch).nEstPasCaseInitiale() && this.getCase(x2, ySearch).nEstPasCaseTrouvee()) {
                x3=x2;
                while (x3<9) {
                    if (x3!=x2 && x3!=xSearch && this.getCase(x3,ySearch).nEstPasCaseInitiale() 
                               && this.getCase(x3, ySearch).nEstPasCaseTrouvee()) {
                        this.traiteTripletteEnLigne(xSearch, ySearch, x2, x3);
                    }
                    x3+=1;
                }
            }
            x2+=1;
        }
        
        
    }

    private void traiteTripletteEnLigne(int xSearch, int ySearch, int x2, int x3) {
        CandidatsCase testCandidats = new CandidatsCase(this.getCaseEnCours().getCandidatsTabBoolean());
        testCandidats.setCandidats(Utils.calculOuLogique2Candidats(this.getCaseEnCours().getCandidatsTabBoolean(),
                                                       this.getCase(x2, ySearch).getCandidatsTabBoolean()));
        testCandidats.setCandidats(Utils.calculOuLogique2Candidats(testCandidats.getCandidats(),
                                                       this.getCase(x3, ySearch).getCandidatsTabBoolean()));
        if (testCandidats.getNombreCandidats()>3) { return;}
        
        javax.swing.JOptionPane.showMessageDialog(null,"Triplette trouvée en ligne "+String.valueOf(ySearch+1)+ ", colonnes : "
                                                   +String.valueOf(xSearch+1)
                                                   +String.valueOf(x2+1)
                                                   +String.valueOf(x3+1));
        int  tableau[]=new int [3];
        tableau[0]=xSearch;tableau[1]=x2;tableau[2]=x3;       
        this.elimineCandidatsLigneSaufColonnes(testCandidats, ySearch, tableau);
    }







}
