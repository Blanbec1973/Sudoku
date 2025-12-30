package view;

import model.grille.Grille;

import java.awt.*;

public interface ViewUpdater {
    void refreshGrilleDisplay(Grille grille);
    void insertDisplayMessage(String message);
    void updateResolutionRank(int delta); // +1 ou -1
    void resetView(String startMessage);
    void removeLastLogLine();
    void showMessageDialog(Component component, Object object);
}

