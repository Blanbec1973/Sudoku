package resolution;

import model.grille.CaseContext;
import model.grille.Grille;

import java.util.Arrays;

public class ResolutionAction {
    private final int numCaseAction;
    private final Integer solution;
    private final Integer candidatAEliminer;
    private final MethodeResolution methodeResolution;
    private final CaseContext context;

    private final int [] candidatsUtilises;

    @Override
    public String toString() {
        return "ResolutionAction{" +
                "numCaseAction=" + numCaseAction +
                ", solution=" + solution +
                ", candidatAEliminer=" + candidatAEliminer +
                ", methodeResolution=" + methodeResolution +
                ", context=" + context +
                ", candidatsUtilises=" + Arrays.toString(candidatsUtilises) +
                '}';
    }

    public ResolutionAction(int numCaseAction, Integer solution, Integer candidatAEliminer, MethodeResolution methodeResolution, CaseContext context, int[] candidatsUtilises) {
        this.numCaseAction = numCaseAction;
        this.solution = solution;
        this.candidatAEliminer = candidatAEliminer;
        this.methodeResolution = methodeResolution;
        this.context = context;
        this.candidatsUtilises = candidatsUtilises;
    }

    public void applyTo(Grille grille) {
        if (solution != null) {
            grille.setValeurCaseEnCours(this);
        } else if (candidatAEliminer != null) {
            grille.elimineCandidat(numCaseAction, candidatAEliminer);
        }
    }

    public boolean isCaseTrouvee() { return (solution != null);}

    public int getNumCaseAction() {
        return numCaseAction;
    }

    public Integer getSolution() {
        return solution;
    }

    public Integer getCandidatAEliminer() {
        return candidatAEliminer;
    }
    public MethodeResolution getMethodeResolution() {return methodeResolution;}

    public CaseContext getContext() {
        return this.context;
    }

    public int getCandidatUtilise(int i) {
        return candidatsUtilises[i];
    }

    public int getNombreCandidatsUtilises() {
        return candidatsUtilises.length;
    }
}
