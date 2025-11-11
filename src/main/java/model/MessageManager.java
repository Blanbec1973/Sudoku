package model;

import control.MyProperties;
import model.grille.CaseContext;
import resolution.MethodeResolution;

public class MessageManager {
    private final MyProperties prop;

    public MessageManager(MyProperties prop)  {
        this.prop = prop;
    }

    public String createMessageSolution(MethodeResolution methodeResolution, CaseContext context) {
        String message = initializeMessage(context);
        String tempString = prop.getProperty(methodeResolution.getSimpleName());
        String tempString2=tempString.replace("%solution", String.valueOf(methodeResolution.getSolution()));
        String tempString3=tempString2.replace("%ligne", context.getYEdition());
        String tempString4=tempString3.replace("%colonne", context.getXEdition());
        String tempString5=tempString4.replace("%region",String.valueOf(context.getNumRegion()));
        return message+" "+tempString5;
    }
    public String createMessageElimination(MethodeResolution methodeResolution, CaseContext context) {
        String message = initializeMessage(context);
        String tempString = prop.getProperty(methodeResolution.getSimpleName());
        String tempString2 = tempString.replace("%c1", String.valueOf(methodeResolution.getC1()));
        String tempString3 = tempString2.replace("%c2", String.valueOf(methodeResolution.getC2()));
        String tempString4 = tempString3.replace("%c3", String.valueOf(methodeResolution.getC3()));
        String tempString5 = tempString4.replace("%candelim", String.valueOf(methodeResolution.getCandidatAEliminer()));
        String tempString6 = tempString5.replace("%ligne", context.getYEdition());
        String tempString7 = tempString6.replace("%colonne", context.getXEdition());
        String tempString8 = tempString7.replace("%region",String.valueOf(context.getNumRegion()));
        return message+" "+tempString8;
    }
    private String initializeMessage(CaseContext context) {
        String message ="";
        message+=String.format(prop.getProperty("msgGeneral"), context.getXEdition(),
                context.getYEdition());
        return message;
    }
}
