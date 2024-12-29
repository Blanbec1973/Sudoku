package control;

import model.EventFromModel;
import model.Model;
import model.ModelListener;
import model.grille.Grille;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;
import view.MyView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class EventManager implements ActionListener, ModelListener, IEventManager {
    private Model model;
    private final MyView myView;

    private final MyProperties properties;
    private static final Logger logger = LogManager.getLogger(EventManager.class);
    public EventManager(MyView view, MyProperties properties) {
        this.myView = view;
        this.properties=properties;
        myView.getBoutonAvance().addActionListener(this);
        myView.getBoutonExplique().addActionListener(this);
        myView.getBoutonRecule().addActionListener(this);
        myView.getMenuSave().addActionListener(this);
        myView.getMenuOpen().addActionListener(this);
    }

    public void setModel(Model model) {this.model=model;}

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == myView.getBoutonAvance()) {
            model.detecteSuivant(false);
            return;
        }
        if (source == myView.getBoutonExplique()) {
            model.detecteSuivant(true);
            return;
        }
        if (source == myView.getBoutonRecule() && myView.getRangResolution().getText().equals("0")) {
            javax.swing.JOptionPane.showMessageDialog(null,properties.getProperty("InitialMessage"));
            return;
        }

        if (source == myView.getBoutonRecule()) {
            model.reloadLastHistoricization();
            myView.refreshGrilleDisplay(model.getGrille());
            this.decrementResolutionRank();
            myView.supprimeDernierLigneLog();
            return;
        }

        if (source == myView.getMenuSave()) {
            String fileName = myView.afficheSaveFileDialog("SAVE");
            if (!fileName.isEmpty()) GridSaver.saveGrid(model.getGrille(),fileName);
        }

        if (source == myView.getMenuOpen()) {
            String fileName2 = myView.afficheSaveFileDialog("OPEN");
            logger.info("Demande chargement fichier : {}",fileName2);
            if (!fileName2.isEmpty()) this.reloadGrille(fileName2);
        }
    }
    public void decrementResolutionRank() {
        int temp = Integer.parseInt(myView.getRangResolution().getText());
        temp-=1;
        myView.getRangResolution().setText(String.valueOf(temp));
    }

    public void incrementResolutionRank() {
        int temp = Integer.parseInt(myView.getRangResolution().getText());
        temp+=1;
        myView.getRangResolution().setText(String.valueOf(temp));
    }
    public void insertDisplayMessage(String text) {
        myView.getLogTextArea().insert(text+'\n', 0);
        myView.getLogTextArea().setCaretPosition(0);
        myView.getPanCommande().revalidate();
        myView.getPanCommande().repaint();
    }
    @Override
    public void onEventFromModel(Grille grille, EventFromModel eventFromModel) {
        switch (eventFromModel.getEventFromModelType()) {
            case AJOUT_SOLUTION:
                myView.refreshGrilleDisplay(grille);
                insertDisplayMessage(eventFromModel.getMessage());
                incrementResolutionRank();
                break;
            case HIGHLIGHT_CASE:
                myView.setCaseAvantExplication(Utils.calculXsearch(eventFromModel.getNumCase()),
                                                Utils.calculYsearch(eventFromModel.getNumCase()));
                break;
            case ELIMINE_CANDIDAT:
                refreshDisplayBox(grille, eventFromModel.getNumCase());
                insertDisplayMessage(eventFromModel.getMessage());
                incrementResolutionRank();
                break;

        }
    }
    public void refreshDisplayBox(Grille grille, int numCase) {
        if (grille.isCaseInitiale(numCase)) {
            myView.setCaseInitiale(numCase, String.valueOf(grille.getValeurCase(numCase)));
            return;
        }
        if (grille.isCaseTrouvee(numCase)) {
            myView.setCase(numCase, String.valueOf(grille.getValeurCase(numCase)));
        }
        else {
            myView.setCaseCandidats(numCase, grille.construitLibelleCandidats(numCase));
        }
    }

    public void reloadGrille(String fileName2) {
        model.reload(fileName2);
        myView.refreshGrilleDisplay(model.getGrille());
        myView.getRangResolution().setText("0");
        myView.getLogTextArea().setText(properties.getProperty("StartMessage"));
    }
}
