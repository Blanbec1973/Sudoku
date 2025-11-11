package resolution;

import model.grille.CaseContext;
import model.grille.Grille;

public class ResolutionAction {
    private final int numCaseAction;
    private final Integer solution;
    private final Integer candidatAEliminer;
    private final MethodeResolution methodeResolution;

    private final CaseContext context;

    public ResolutionAction(int numCaseAction, Integer solution, Integer candidatAEliminer, MethodeResolution methodeResolution, CaseContext context) {
        this.numCaseAction = numCaseAction;
        this.solution = solution;
        this.candidatAEliminer = candidatAEliminer;
        this.methodeResolution = methodeResolution;
        this.context = context;
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
}
