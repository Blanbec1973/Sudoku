package model;

import model.grille.Grille;

public interface ModelListener {

    void onEventFromModel(Grille grille, EventFromModel eventFromModel);
}
