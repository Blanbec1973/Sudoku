package model;

import model.grille.Grille;

import java.util.ArrayList;
import java.util.List;

public class SimpleModelEventPublisher implements ModelEventPublisher {
    private final List<ModelListener> listeners = new ArrayList<>();

    public void addListener(ModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void publish(Grille grille, EventFromModel event) {
        for (ModelListener listener : listeners) {
            listener.onEventFromModel(grille, event);
        }
    }
}