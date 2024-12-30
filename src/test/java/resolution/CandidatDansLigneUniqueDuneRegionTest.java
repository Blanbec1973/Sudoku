package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CandidatDansLigneUniqueDuneRegionTest {
	private CandidatDansLigneUniqueDuneRegion methode;
	private final Grille grille = new Grille();
	
	@BeforeAll
	void setUpBeforeClass() {
		grille.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/CandidatDansLigneUniqueDuneRegion.sud");
		methode = new CandidatDansLigneUniqueDuneRegion(grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(19);
		assertFalse(methode.traiteCaseEnCours(false));

		CaseEnCours.setCaseEnCours(4);
		assertTrue(methode.traiteCaseEnCours(false));
		assertEquals(1,methode.numCaseAction);
		assertEquals(6, methode.candidatAEliminer);
		assertEquals(0, methode.xAction);
		assertEquals(0,methode.yAction);

		grille.elimineCandidat(1,6);
		grille.elimineCandidat(9,6);
		assertFalse(methode.detecteCandidatAEliminer());
	}

	@Test
	void testCandidatDansLigneUnique() {
		CaseEnCours.setCaseEnCours(4);
		assertTrue(methode.candidatDansLigneUnique());

		CaseEnCours.setCaseEnCours(57);
		methode.candidatAEliminer = 3;
		assertFalse(methode.candidatDansLigneUnique());
		methode.candidatAEliminer = 7;
		assertFalse(methode.candidatDansLigneUnique());
	}

	@Test
	void testGetSimpleName() {
		assertEquals("CandidatDansLigneUniqueDuneRegion",methode.getSimpleName());
	}
}
