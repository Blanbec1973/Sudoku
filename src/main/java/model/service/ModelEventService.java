package model.service;

import model.EventFromModel;
import model.ModelEventPublisher;
import model.grille.Grille;
import org.springframework.stereotype.Service;

@Service
public class ModelEventService {

    private final ModelEventPublisher publisher;

    public ModelEventService(ModelEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publishHighlight(Grille grille, int numCase) {
        publisher.publish(grille, new EventFromModel(
                model.EventFromModelType.HIGHLIGHT_CASE,
                numCase,
                ""
        ));
    }

    public void publishSolution(Grille grille, int numCase, String message) {
        publisher.publish(grille, new EventFromModel(
                model.EventFromModelType.AJOUT_SOLUTION,
                numCase,
                message
        ));
    }

    public void publishElimination(Grille grille, int numCase, String message) {
        publisher.publish(grille, new EventFromModel(
                model.EventFromModelType.ELIMINE_CANDIDAT,
                numCase,
                message
        ));
    }
}