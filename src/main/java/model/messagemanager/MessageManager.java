package model.messagemanager;

import model.grille.CaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resolution.CandidatUniqueDansZone;
import resolution.PaireConjugueeDansZone;
import resolution.ResolutionAction;

@Service
public class MessageManager {
    private final TemplateProvider provider;

    @Autowired
    public MessageManager(TemplateProvider provider)  {
        this.provider = provider;
    }

    public String createMessageSolution(ResolutionAction action) {
        String message = initializeMessage(action.getContext());
        String tempString = provider.getTemplate(action.getMethodeResolution().getSimpleName());
        String tempString2=tempString.replace("%solution", String.valueOf(action.getSolution()));
        String tempString3=tempString2.replace("%ligne", action.getContext().getYEdition());
        String tempString4=tempString3.replace("%colonne", action.getContext().getXEdition());
        String tempString5=tempString4.replace("%region",String.valueOf(action.getContext().getNumRegion()));
        if (action.getMethodeResolution() instanceof CandidatUniqueDansZone zoneMethod) {
            String tempString6=tempString5.replace("%zone", String.valueOf(zoneMethod.getZone()).toLowerCase());
            return message+" "+tempString6;
        }
        return message+" "+tempString5;
    }
    public String createMessageElimination(ResolutionAction action) {
        String message = initializeMessage(action.getContext());
        String tempString = provider.getTemplate(action.getMethodeResolution().getSimpleName());
        String tempString2 = tempString.replace("%c1", String.valueOf(action.getCandidatUtilise(0)));
        String tempString3 = tempString2.replace("%c2", String.valueOf(action.getCandidatUtilise(1)));
        String tempString4;
        if (action.getNombreCandidatsUtilises()>2) {
            tempString4 = tempString3.replace("%c3", String.valueOf(action.getCandidatUtilise(2)));
        } else {
            tempString4 = tempString3;
        }
        String tempString5 = tempString4.replace("%candelim", String.valueOf(action.getCandidatAEliminer()));
        String tempString6 = tempString5.replace("%ligne", action.getContext().getYEdition());
        String tempString7 = tempString6.replace("%colonne", action.getContext().getXEdition());
        String tempString8 = tempString7.replace("%region",String.valueOf(action.getContext().getNumRegion()));
        if (action.getMethodeResolution() instanceof PaireConjugueeDansZone zoneMethod) {
            String tempString9=tempString8.replace("%zone", String.valueOf(zoneMethod.getZone()).toLowerCase());
            return message+" "+tempString9;
        }
        return message+" "+tempString8;
    }
    private String initializeMessage(CaseContext context) {
        String message ="";
        message+=String.format(provider.getTemplate("msgGeneral"), context.getXEdition(),
                context.getYEdition());
        return message;
    }
}
