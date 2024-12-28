package control;


import model.grille.Grille;

public interface IEventManager {

    void decrementResolutionRank();
    void incrementResolutionRank();
    void insertDisplayMessage(String text);

    void refreshDisplayBox(Grille grille, int numCase);
}
