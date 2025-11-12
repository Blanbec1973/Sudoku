package model;

import control.MyProperties;
import model.grille.CaseContext;
import resolution.ResolutionAction;

public class MessageManager {
    private final MyProperties prop;

    public MessageManager(MyProperties prop)  {
        this.prop = prop;
    }

    public String createMessageSolution(ResolutionAction action) {
        String message = initializeMessage(action.getContext());
        String tempString = prop.getProperty(action.getMethodeResolution().getSimpleName());
        String tempString2=tempString.replace("%solution", String.valueOf(action.getSolution()));
        String tempString3=tempString2.replace("%ligne", action.getContext().getYEdition());
        String tempString4=tempString3.replace("%colonne", action.getContext().getXEdition());
        String tempString5=tempString4.replace("%region",String.valueOf(action.getContext().getNumRegion()));
        return message+" "+tempString5;
    }
    public String createMessageElimination(ResolutionAction action) {
        String message = initializeMessage(action.getContext());
        String tempString = prop.getProperty(action.getMethodeResolution().getSimpleName());
        String tempString2 = tempString.replace("%c1", String.valueOf(action.getCandidatUtilise(0)));
        String tempString3 = tempString2.replace("%c2", String.valueOf(action.getCandidatUtilise(1)));
        String tempString4 = tempString3.replace("%c3", String.valueOf(action.getCandidatUtilise(2)));
        String tempString5 = tempString4.replace("%candelim", String.valueOf(action.getCandidatAEliminer()));
        String tempString6 = tempString5.replace("%ligne", action.getContext().getYEdition());
        String tempString7 = tempString6.replace("%colonne", action.getContext().getXEdition());
        String tempString8 = tempString7.replace("%region",String.valueOf(action.getContext().getNumRegion()));
        return message+" "+tempString8;
    }
    private String initializeMessage(CaseContext context) {
        String message ="";
        message+=String.format(prop.getProperty("msgGeneral"), context.getXEdition(),
                context.getYEdition());
        return message;
    }
}
