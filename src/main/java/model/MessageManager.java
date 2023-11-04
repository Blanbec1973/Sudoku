package model;

import control.MyProperties;
import model.grille.CaseEnCours;
import resolution.MethodeResolution;

class MessageManager {
    private final MyProperties prop;

    public MessageManager(MyProperties prop)  {
        this.prop = prop;
    }

    public String createMessageSolution(MethodeResolution methodeResolution) {
        String message = initializeMessage();
        String tempString = prop.getProperty(methodeResolution.getSimpleName());
        String tempString2=tempString.replace("%solution", String.valueOf(methodeResolution.getSolution()));
        String tempString3=tempString2.replace("%ligne", CaseEnCours.getYSearchEdition());
        String tempString4=tempString3.replace("%colonne", CaseEnCours.getXSearchEdition());
        String tempString5=tempString4.replace("%region",String.valueOf(CaseEnCours.getNumRegion()));
        return message+" "+tempString5;
    }
    public String createMessageElimination(MethodeResolution methodeResolution) {
        String message = initializeMessage();
        String tempString = prop.getProperty(methodeResolution.getSimpleName());
        String tempString2 = tempString.replace("%c1", String.valueOf(methodeResolution.getC1()));
        String tempString3 = tempString2.replace("%c2", String.valueOf(methodeResolution.getC2()));
        String tempString4 = tempString3.replace("%c3", String.valueOf(methodeResolution.getC3()));
        String tempString5 = tempString4.replace("%candelim", String.valueOf(methodeResolution.getCandidatAEliminer()));
        String tempString6 = tempString5.replace("%ligne", CaseEnCours.getYSearchEdition());
        String tempString7 = tempString6.replace("%colonne", CaseEnCours.getXSearchEdition());
        String tempString8 = tempString7.replace("%region",String.valueOf(CaseEnCours.getNumRegion()));
        return message+" "+tempString8;
    }
    private String initializeMessage() {
        String message ="";
        message+=String.format(prop.getProperty("msgGeneral"), CaseEnCours.getXSearchEdition(),
                                                               CaseEnCours.getYSearchEdition());
        return message;
    }
}
