package control;

import model.EventFromModel;
import model.EventFromModelType;
import model.Model;
import model.ModelListener;
import model.grille.Grille;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import view.MyView;
import view.ViewUpdater;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class EventManager implements ActionListener, ModelListener, ViewUpdater {
    private Model model;
    private final ViewUpdater viewUpdater;
    private final MyProperties properties;
    private static final Logger logger = LoggerFactory.getLogger(EventManager.class);
    @Autowired
    public EventManager(ViewUpdater viewUpdater, MyProperties properties) {
        this.viewUpdater = viewUpdater;
        this.properties=properties;
    }

    public void setModel(Model model) {this.model=model;}

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "AVANCE":
                model.detecteSuivant(false);
                break;

            case "EXPLIQUE":
                model.detecteSuivant(true);
                break;

            case "RECULE":
                handleRecule();
                break;

            case "SAVE":
                String fileName = ((MyView) viewUpdater).afficheSaveFileDialog("SAVE");
                if (!fileName.isEmpty()) GridSaver.saveGrid(model.getGrille(), fileName);
                break;

            case "OPEN":
                String fileName2 = ((MyView) viewUpdater).afficheSaveFileDialog("OPEN");
                Path path = Paths.get(fileName2).toAbsolutePath();
                if (!fileName2.isEmpty()) reloadGrille(path);
                break;

            case "RESOLUTION":
                resolution();
                break;

            default:
                throw new IllegalArgumentException("Commande inconnue !");
        }
    }
    private void handleRecule() {
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


    private void resolution() {
        logger.info("Demande de résolution globale.");
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!model.detecteSuivant(true))
                    timer.cancel();
            }
        }, 0, 200); // Démarre immédiatement, répète toutes les secondes

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
    public void reloadGrille(Path path) {
        model.reload(path);
        viewUpdater.refreshGrilleDisplay(model.getGrille());
        viewUpdater.resetView(properties.getProperty("StartMessage"));
    }


    // Implémentation des méthodes de ViewUpdater
    @Override
    public void refreshGrilleDisplay(Grille grille) {
        viewUpdater.refreshGrilleDisplay(grille);
    }

    @Override
    public void highlightCase(int numCase) {
        viewUpdater.highlightCase(numCase);
    }

    @Override
    public void insertDisplayMessage(String message) {
        viewUpdater.insertDisplayMessage(message);
    }

    @Override
    public void updateResolutionRank(int delta) {
        viewUpdater.updateResolutionRank(delta);
    }

    @Override
    public void resetView(String startMessage) {
        viewUpdater.resetView(startMessage);
    }

    @Override
    public void updateSingleCase(Grille grille, int numCase) {
        viewUpdater.updateSingleCase(grille, numCase);
    }

    @Override
    public Color getCaseBackground(int numCase) {
        return viewUpdater.getCaseBackground(numCase);
    }

    @Override
    public int getCaseValue(int numCase) {
        return viewUpdater.getCaseValue(numCase);
    }

    @Override
    public void removeLastLogLine() {
        viewUpdater.removeLastLogLine();
    }

    @Override
    public void showMessageDialog(Component component, Object object) {
        viewUpdater.showMessageDialog(component,object);
    }


}
