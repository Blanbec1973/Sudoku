package control;

import view.MyView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class EventManager implements ActionListener {
    private final Control control;
    private final MyView myView;
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
            String fileName = myView.afficheSaveFileDialog();
            if (!fileName.isEmpty()) GridSaver.saveGrid(control.getModel().getGrille(),fileName);
        }
    }
    public void decrementResolutionRank() {
        int temp = Integer.parseInt(myView.getRangResolution().getText());
        temp-=1;
        myView.getRangResolution().setText(String.valueOf(temp));
    }



}
