package control;

import model.EventFromModel;
import model.ModelListener;
import model.grille.Grille;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.MyView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class EventManager implements ActionListener, ModelListener {
    private final Control control;
    private final MyView myView;
    private static final Logger logger = LogManager.getLogger(EventManager.class);
    public EventManager(Control control, MyView view) {
        this.control = control;
        this.myView = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == myView.getBoutonAvance()) {
            control.getModel().detecteSuivant(false);
            return;
        }
        if (source == myView.getBoutonExplique()) {
            control.getModel().detecteSuivant(true);
            return;
        }
        if (source == myView.getBoutonRecule() && myView.getRangResolution().getText().equals("0")) {
            javax.swing.JOptionPane.showMessageDialog(null,control.getProperties().getProperty("InitialMessage"));
            return;
        }

        if (source == myView.getBoutonRecule()) {
            control.getModel().reloadLastHistoricization();
            control.refreshDisplayGrid(control.getModel().getGrille());
            this.decrementResolutionRank();
            myView.supprimeDernierLigneLog();
            return;
        }

        if (source == myView.getMenuSave()) {
            String fileName = myView.afficheSaveFileDialog("SAVE");
            if (!fileName.isEmpty()) GridSaver.saveGrid(control.getModel().getGrille(),fileName);
        }

        if (source == myView.getMenuOpen()) {
            String fileName2 = myView.afficheSaveFileDialog("OPEN");
            logger.info("Demande chargement fichier : {}",fileName2);
            if (!fileName2.isEmpty()) control.reloadGrille(fileName2);
        }
    }
    private void decrementResolutionRank() {
        int temp = Integer.parseInt(myView.getRangResolution().getText());
        temp-=1;
        myView.getRangResolution().setText(String.valueOf(temp));
    }

    private void incrementResolutionRank() {
        int temp = Integer.parseInt(myView.getRangResolution().getText());
        temp+=1;
        myView.getRangResolution().setText(String.valueOf(temp));
    }
    private void insertDisplayMessage(String text) {
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
                break;
            case ELIMINE_CANDIDAT:
                refreshDisplayBox(grille, eventFromModel.getNumCase());
                insertDisplayMessage(eventFromModel.getMessage());
                incrementResolutionRank();
                break;

        }
    }
    private void refreshDisplayBox(Grille grille, int numCase) {
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
}
