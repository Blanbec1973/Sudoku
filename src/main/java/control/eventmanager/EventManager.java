package control.eventmanager;

import model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import view.MyView;
import view.ViewUpdater;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class EventManager implements ActionListener {
    private Model model;
    private final ViewUpdater viewUpdater;
    private final ResolutionService resolutionService;
    private final NavigationService navigationService;
    @Autowired
    public EventManager(ViewUpdater viewUpdater, ResolutionService resolutionService, NavigationService navigationService) {
        this.viewUpdater = viewUpdater;
        this.resolutionService = resolutionService;
        this.navigationService = navigationService;
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
                navigationService.handleRecule();
                break;

            case "SAVE":
                String fileName = ((MyView) viewUpdater).afficheSaveFileDialog("SAVE");
                if (!fileName.isEmpty()) GridSaver.saveGrid(model.getGrille(), fileName);
                break;

            case "OPEN":
                String fileName2 = ((MyView) viewUpdater).afficheSaveFileDialog("OPEN");
                Path path = Paths.get(fileName2).toAbsolutePath();
                if (!fileName2.isEmpty()) navigationService.reloadGrille(path);
                break;

            case "RESOLUTION":
                resolutionService.resolution();
                break;

            default:
                throw new IllegalArgumentException("Commande inconnue !");
        }
    }
    public void simulateClick(String command) {
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, command);
        this.actionPerformed(event);
    }



}
