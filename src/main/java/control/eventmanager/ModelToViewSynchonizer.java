package control.eventmanager;

import model.EventFromModel;
import model.EventFromModelType;
import model.ModelListener;
import model.grille.Grille;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import view.ViewUpdater;

@Service
public class ModelToViewSynchonizer implements ModelListener {
    private static final Logger logger = LoggerFactory.getLogger(ModelToViewSynchonizer.class);
    private final ViewUpdater viewUpdater;

    @Autowired
    public ModelToViewSynchonizer(ViewUpdater viewUpdater) {
        this.viewUpdater = viewUpdater;
    }


    @Override
    public void onEventFromModel(Grille grille, EventFromModel eventFromModel) {
        if (eventFromModel.getEventFromModelType() == EventFromModelType.HIGHLIGHT_CASE) {
            logger.debug("Highlight in EventManager.");
            viewUpdater.highlightCase(eventFromModel.getNumCase());
        } else {
            viewUpdater.refreshGrilleDisplay(grille);
            viewUpdater.insertDisplayMessage(eventFromModel.getMessage());
            viewUpdater.updateResolutionRank(1);
        }
    }
}
