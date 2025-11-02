package model.grille;

import model.service.GrilleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Grille {
    private final Case[][] mesCases = new Case [9][9];
    private static final Logger logger = LogManager.getLogger(Grille.class.getPackage().getName());
    private final List<Integer> casesAtrouver= new ArrayList<>();
    private final GrilleService grilleService;
        
    public Grille() {
        int numcase = 0;
        for (int y=0;y<9;y++) {
            for (int x=0;x<9;x++) {
                numcase = numcase +1;
                mesCases[x][y] = new Case(numcase, x, y);
            }
        }
        this.grilleService = new GrilleService(this);
    }

    public void init(Path path) {
        casesAtrouver.clear();
        String readLine;
        int valeur;
        int indexCase = 1;
        int y=0;
        try (BufferedReader b = new BufferedReader(new FileReader(path.toFile()))){
            while ((readLine = b.readLine()) != null) {
                for (int x=0;x<9;x++) {
                    valeur = Integer.parseInt(readLine.substring(x,x+1));
                    if (valeur != 0) {
                        this.getCase(x,y).setCaseInitiale(valeur);
                    }
                    else {
                        this.getCasesAtrouver().add(indexCase);
                        this.getCase(x,y).initialiserCaseVide();
                    }
                    indexCase+=1;
                }
                y++;
            }
        } catch (IOException | NullPointerException ex) {
            logger.fatal("Exception : {}",ex.getMessage());
            System.exit(-1);
        }
        logger.info("Chargement OK fichier : {}",path.getFileName());
        grilleService.calculTousLesCandidats();
    }

    public GrilleService getGrilleService() {return this.grilleService;}
    protected Case getCase(int x, int y) {return mesCases[x][y];}
    protected Case getCase(int numCase) {return mesCases[Utils.calculXsearch(numCase)][Utils.calculYsearch(numCase)];}
    protected Case getCaseEnCours() {return mesCases[CaseEnCours.getX()][CaseEnCours.getY()];}
    public List<Integer> getCasesAtrouver() {return casesAtrouver;}
    // Gestion candidats :
    public CandidatsCase getCandidats(int numCase) {return this.getCase(numCase).getCandidats();}
    public boolean [] getCandidatsTabBoolean(int numCase) {return this.getCase(numCase).getCandidatsTabBoolean();}
    public boolean [] getCandidatsTabBoolean(int x, int y) {return this.getCase(x,y).getCandidatsTabBoolean();}
    public void setAllCandidatsToFalse(int numCase) {this.getCase(numCase).getCandidats().setAllCandidatsToFalse();}
    public void setCandidat(int numCase, int candidat) {this.getCase(numCase).getCandidats().setCandidat(candidat);}
    public void elimineCandidat (int numCase, int candidatAEliminer) {
        this.getCase(numCase).elimineCandidat(candidatAEliminer);
    }
    public void elimineCandidat (int x, int y, int candidatAEliminer) {
        this.getCase(x,y).elimineCandidat(candidatAEliminer);
    }
    //Questions sur les cases :
    public boolean isCaseInitiale(int numCase) {return this.getCase(numCase).isCaseInitiale();}
    public boolean isCaseTrouvee(int numCase) {return this.getCase(numCase).isCaseTrouvee();}
    public boolean isCaseATrouver(int numCase) {
        int x = Utils.calculXsearch(numCase);
        int y = Utils.calculYsearch(numCase);
        return isCaseATrouver(x, y);
    }
    public boolean isCaseATrouver(int x, int y) {return this.getCase(x,y).isCaseATrouver();}

    public boolean nEstPasCaseInitiale(int x, int y) {return this.getCase(x, y).nEstPasCaseInitiale();}

    public boolean nEstPasCaseTrouvee(int x, int y) {return this.getCase(x, y).nEstPasCaseTrouvee();}
    public boolean contientCandidatUnique(int numCase) {return this.getCase(numCase).contientCandidatUnique();}
    public boolean isCandidat(int numCase, int candidat) {return this.getCase(numCase).isCandidat(candidat);}
    public boolean isCandidat(int x, int y, int candidat) {return this.getCase(x,y).isCandidat(candidat);}
    public int getValeurCase(int numCase) {return this.getCase(numCase).getValeur();}
    public int getValeurCase(int x, int y) {return this.getCase(x,y).getValeur();}
    public int getNombreCandidats(int numCase) {return this.getCase(numCase).getNombreCandidats();}
    public int getRegion(int numCase) {return this.getCase(numCase).getRegion();}
    public int calculValeurUnique(int numCase) {return this.getCase(numCase).calculValeurUnique();}
    public String construitLibelleCandidats(int numCase) {return this.getCase(numCase).construitLibelleCandidats();}

    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	for (int i=1;i<81;i++) {
    		sb.append(i);
    		sb.append("  : ");
    		sb.append(this.getCase(i).isCaseInitiale());
    		sb.append(this.getCase(i).isCaseATrouver());
    		sb.append(this.getCase(i).isCaseTrouvee());
    		sb.append(" ");
    		sb.append(this.getCase(i).getCandidats().getNombreCandidats());
    		sb.append(" ==> ");
    		sb.append(this.getCase(i).getCandidats().toString());
            sb.append("=");
            sb.append(this.getCase(i).getValeur());
    		if (i % 9 == 0) {sb.append("\n");} else {sb.append(" | ");}
    	}
    	return sb.toString();	
    }
    
    public void setValeurCaseEnCours(int solution) {
        this.getCaseEnCours().setCaseTrouvee(solution);
        grilleService.elimineCandidatsCaseTrouvee(CaseEnCours.getX(), CaseEnCours.getY(), solution);
        casesAtrouver.remove(casesAtrouver.indexOf(calculNumCase(CaseEnCours.getX(), CaseEnCours.getY())));
    }

    public static int calculNumCase(int x, int y) {
        return (y*9+x+1);
    }
}
