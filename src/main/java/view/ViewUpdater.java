package view;

import model.grille.Grille;

import java.awt.*;

public interface ViewUpdater {
    void refreshGrilleDisplay(Grille grille);
    void highlightCase(int numCase);
    void insertDisplayMessage(String message);
    void updateResolutionRank(int delta); // +1 ou -1
    void resetView(String startMessage);
    void updateSingleCase(Grille grille, int numCase);
    Color getCaseBackground(int numCase);
    int getCaseValue(int numCase);
    void removeLastLogLine();
    void showMessageDialog(Component component, Object object);
}

