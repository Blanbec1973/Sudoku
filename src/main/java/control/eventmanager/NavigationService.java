package control.eventmanager;

import control.MyProperties;
import model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import view.ViewUpdater;

import java.nio.file.Path;

@Service
public class NavigationService {
    private final ViewUpdater viewUpdater;
    private final MyProperties properties;
    private final Model model;

    @Autowired
    public NavigationService(ViewUpdater viewUpdater, MyProperties properties, Model model) {
        this.viewUpdater = viewUpdater;
        this.properties = properties;
        this.model = model;
    }

    void handleRecule() {
        // Vérifier si on peut revenir en arrière
        if (!model.canReloadLastHistoricization()) {
            viewUpdater.showMessageDialog(null, properties.getProperty("InitialMessage"));
            return;
        }

        // Revenir en arrière
        model.reloadLastHistoricization();
        viewUpdater.refreshGrilleDisplay(model.getGrille());
        viewUpdater.updateResolutionRank(-1);
        viewUpdater.removeLastLogLine(); // Ajout pour corriger le problème des messages
    }


    public void reloadGrille(Path path) {
        model.reload(path);
        viewUpdater.refreshGrilleDisplay(model.getGrille());
        viewUpdater.resetView(properties.getProperty("StartMessage"));
    }
}
