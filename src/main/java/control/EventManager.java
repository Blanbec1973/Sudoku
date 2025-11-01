package control;

import model.EventFromModel;
import model.Model;
import model.ModelListener;
import model.grille.Grille;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.MyView;
import view.ViewUpdater;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

class EventManager implements ActionListener, ModelListener, IEventManager {
    private Model model;
    private final ViewUpdater viewUpdater;
    private final MyProperties properties;
    private static final Logger logger = LogManager.getLogger(EventManager.class);
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
                if (model.getGrille().getCasesAtrouver().isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null, properties.getProperty("InitialMessage"));
                } else {
                    model.reloadLastHistoricization();
                    viewUpdater.refreshGrilleDisplay(model.getGrille());
                    viewUpdater.updateResolutionRank(-1);
                }
                break;

            case "SAVE":
                String fileName = ((MyView) viewUpdater).afficheSaveFileDialog("SAVE");
                if (!fileName.isEmpty()) GridSaver.saveGrid(model.getGrille(), fileName);
                break;

            case "OPEN":
                String fileName2 = ((MyView) viewUpdater).afficheSaveFileDialog("OPEN");
                if (!fileName2.isEmpty()) reloadGrille(fileName2);
                break;

            case "RESOLUTION":
                resolution();
                break;

            default:
                throw new IllegalArgumentException("Commande inconnue !");
        }
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

    // =========================
    // Implémentation ViewUpdater
    // =========================
    @Override
    public void insertDisplayMessage(String text) {
        viewUpdater.insertDisplayMessage(text);
    }
    @Override
    public void updateResolutionRank(int delta) {
        viewUpdater.updateResolutionRank(delta);
    }
    @Override
    public void onEventFromModel(Grille grille, EventFromModel eventFromModel) {
        switch (eventFromModel.getEventFromModelType()) {
            case AJOUT_SOLUTION:
                viewUpdater.refreshGrilleDisplay(grille);
                viewUpdater.insertDisplayMessage(eventFromModel.getMessage());
                viewUpdater.updateResolutionRank(1);
                break;
            case HIGHLIGHT_CASE:
                viewUpdater.highlightCase(eventFromModel.getNumCase());
                break;
            case ELIMINE_CANDIDAT:
                viewUpdater.refreshGrilleDisplay(grille);
                viewUpdater.insertDisplayMessage(eventFromModel.getMessage());
                updateResolutionRank(1);
                break;

        }
    }
    public void reloadGrille(String fileName2) {
        model.reload(fileName2);
        viewUpdater.refreshGrilleDisplay(model.getGrille());
        viewUpdater.resetView(properties.getProperty("StartMessage"));
    }
}
