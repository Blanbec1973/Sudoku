package model;

import model.grille.Grille;

public interface ModelEventPublisher {
    void publish(Grille grille, EventFromModel event);
}

