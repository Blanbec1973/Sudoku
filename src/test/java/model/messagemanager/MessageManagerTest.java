package model.messagemanager;

import model.grille.CaseContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import resolution.MethodeResolution;
import resolution.ResolutionAction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MessageManagerTest {
    //private final MyProperties prop = Mockito.mock(MyProperties.class);
    private final TemplateProvider templateProvider = Mockito.mock(TemplateProvider.class);
    private final MethodeResolution methodeResolution = Mockito.mock(MethodeResolution.class);
    private MessageManager messageManager;
    private final ResolutionAction mockAction = Mockito.mock(ResolutionAction.class);

    @BeforeAll
    void beforeAll() {
        messageManager = new MessageManager(templateProvider);
        when(templateProvider.getTemplate("msgGeneral")).thenReturn("Case x=%s  y=%s -");
        when(mockAction.getSolution()).thenReturn(9);
        when(mockAction.getContext()).thenReturn(new CaseContext(5,7));
        when(mockAction.getCandidatUtilise(0)).thenReturn(1);
        when(mockAction.getCandidatUtilise(1)).thenReturn(2);
        when(mockAction.getCandidatUtilise(2)).thenReturn(3);
        when(mockAction.getCandidatAEliminer()).thenReturn(9);
        when(mockAction.getMethodeResolution()).thenReturn(methodeResolution);
    }

    @Test
    void candidatUniqueDansCaseMessageTest() {
        when(templateProvider.getTemplate("CandidatUniqueDansCase")).thenReturn("Candidat unique dans la case, solution=%solution.");
        when(methodeResolution.getSimpleName()).thenReturn("CandidatUniqueDansCase");
        assertEquals("Case x=6  y=8 - Candidat unique dans la case, solution=9.",
                             messageManager.createMessageSolution(mockAction));
    }
    @Test
    void candidatUniqueDansLigneMessageTest() {
        when(templateProvider.getTemplate("CandidatUniqueDansLigne")).thenReturn("Candidat unique dans la ligne %ligne, solution=%solution.");
        when(methodeResolution.getSimpleName()).thenReturn("CandidatUniqueDansLigne");
        assertEquals("Case x=6  y=8 - Candidat unique dans la ligne 8, solution=9.",
                messageManager.createMessageSolution(mockAction));
    }
    @Test
    void candidatUniqueDansColonneMessageTest() {
        when(templateProvider.getTemplate("CandidatUniqueDansColonne")).thenReturn("Candidat unique dans la colonne %colonne, solution=%solution.");
        when(methodeResolution.getSimpleName()).thenReturn("CandidatUniqueDansColonne");
        assertEquals("Case x=6  y=8 - Candidat unique dans la colonne 6, solution=9.",
                messageManager.createMessageSolution(mockAction));
    }
    @Test
    void PaireCandidats2CasesLigneMessageTest() {
        when(templateProvider.getTemplate("PaireCandidats2CasesLigne")).thenReturn("Couple conjugué %c1%c2 dans deux cases de la ligne %ligne, élimination candidat %candelim dans les autres cases de la ligne.");
        when(methodeResolution.getSimpleName()).thenReturn("PaireCandidats2CasesLigne");
        assertEquals("Case x=6  y=8 - Couple conjugué 12 dans deux cases de la ligne 8, élimination candidat 9 dans les autres cases de la ligne.",
                messageManager.createMessageElimination(mockAction));
    }
    @Test
    void PaireCandidats2CasesColonneMessageTest() {
        when(templateProvider.getTemplate("PaireCandidats2CasesColonne")).thenReturn("Couple conjugué %c1%c2 dans deux cases de la colonne %colonne, élimination candidat %candelim dans les autres cases de la colonne.");
        when(methodeResolution.getSimpleName()).thenReturn("PaireCandidats2CasesColonne");
        assertEquals("Case x=6  y=8 - Couple conjugué 12 dans deux cases de la colonne 6, élimination candidat 9 dans les autres cases de la colonne.",
                messageManager.createMessageElimination(mockAction));
    }
    @Test
    void PaireCandidats2CasesRegionMessageTest() {
        when(templateProvider.getTemplate("PaireCandidats2CasesRegion")).thenReturn("Couple conjugué %c1%c2 dans deux cases de la région %region, élimination candidat %candelim dans les autres cases de la région.");
        when(methodeResolution.getSimpleName()).thenReturn("PaireCandidats2CasesRegion");
        assertEquals("Case x=6  y=8 - Couple conjugué 12 dans deux cases de la région 8, élimination candidat 9 dans les autres cases de la région.",
                messageManager.createMessageElimination(mockAction));
    }
    @Test
    void candidatUniqueDansRegionMessageTest() {
        when(templateProvider.getTemplate("CandidatUniqueDansRegion")).thenReturn("Candidat unique dans la région %region, solution=%solution.");
        when(methodeResolution.getSimpleName()).thenReturn("CandidatUniqueDansRegion");
        assertEquals("Case x=6  y=8 - Candidat unique dans la région 8, solution=9.",
                messageManager.createMessageSolution(mockAction));
    }
    @Test
    void AbsenceCandidatEnColonneDansLesAutresRegionsMessageTest() {
        when(templateProvider.getTemplate("AbsenceCandidatEnColonneDansLesAutresRegions")).thenReturn("Candidat %candelim en colonne %colonne absent dans autres régions de la colonne.");
        when(methodeResolution.getSimpleName()).thenReturn("AbsenceCandidatEnColonneDansLesAutresRegions");
        assertEquals("Case x=6  y=8 - Candidat 9 en colonne 6 absent dans autres régions de la colonne.",
                              messageManager.createMessageElimination(mockAction));
    }
    @Test
    void AbsenceCandidatEnLigneDansLesAutresRegionsMessageTest() {
        when(templateProvider.getTemplate("AbsenceCandidatEnLigneDansLesAutresRegions")).thenReturn("Candidat %candelim en ligne %ligne absent dans autres régions de la ligne.");
        when(methodeResolution.getSimpleName()).thenReturn("AbsenceCandidatEnLigneDansLesAutresRegions");
        assertEquals("Case x=6  y=8 - Candidat 9 en ligne 8 absent dans autres régions de la ligne.",
                messageManager.createMessageElimination(mockAction));
    }
    @Test
    void tripletteCandidatsEnLigneMessageTest() {
        when(templateProvider.getTemplate("TripletteCandidatsEnLigne")).thenReturn("Triplette %c1%c2%c3 dans trois cases de la ligne, élimination candidat %candelim.");
        when(methodeResolution.getSimpleName()).thenReturn("TripletteCandidatsEnLigne");
        when(mockAction.getNombreCandidatsUtilises()).thenReturn(3);
        assertEquals("Case x=6  y=8 - Triplette 123 dans trois cases de la ligne, élimination candidat 9.",
                messageManager.createMessageElimination(mockAction));
    }
    @Test
    void candidatDansColonneUniqueDuneRegionMessageTest() {
        when(templateProvider.getTemplate("CandidatDansColonneUniqueDuneRegion")).thenReturn("Candidat %candelim dans colonne unique de la région.");
        when(methodeResolution.getSimpleName()).thenReturn("CandidatDansColonneUniqueDuneRegion");
        assertEquals("Case x=6  y=8 - Candidat 9 dans colonne unique de la région.",
                messageManager.createMessageElimination(mockAction));
    }
}