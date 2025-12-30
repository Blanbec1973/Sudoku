package view;

import model.grille.Grille;

import java.awt.*;

public interface ViewCaseUpdater {
    void highlightCase(int numCase);
    void updateSingleCase(Grille grille, int numCase);
    Color getCaseBackground(int numCase);
    int getCaseValue(int numCase);
}

