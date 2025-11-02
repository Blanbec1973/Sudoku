package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CandidatDansColonneUniqueDuneRegionTest {
	private CandidatDansColonneUniqueDuneRegion methode;
	private final Grille grille = new Grille();
	
	@BeforeAll
	void setUpBeforeClass() {
		String fileName = "src/test/resources/grillesTest/CandidatDansColonneUniqueDuneRegion.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new CandidatDansColonneUniqueDuneRegion(grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(3);
		assertFalse(methode.traiteCaseEnCours(false).isPresent());

		CaseEnCours.setCaseEnCours(28);
		assertTrue(methode.traiteCaseEnCours(false).isPresent());
		assertEquals(1,methode.numCaseAction);
		assertEquals(6, methode.candidatAEliminer);
		assertEquals(0, methode.xAction);
		assertEquals(0,methode.yAction);

		grille.elimineCandidat(1,6);
		grille.elimineCandidat(73,6);
		assertFalse(methode.detecteCandidatAEliminer());
	}

	@Test
	void testCandidatDansColonneUnique() {
		CaseEnCours.setCaseEnCours(28);
		assertTrue(methode.candidatDansColonneUnique());

		CaseEnCours.setCaseEnCours(25);
		methode.candidatAEliminer = 3;
		assertFalse(methode.candidatDansColonneUnique());
		methode.candidatAEliminer = 7;
		assertFalse(methode.candidatDansColonneUnique());
	}

	@Test
	void testGetSimpleName() {
		assertEquals("CandidatDansColonneUniqueDuneRegion",methode.getSimpleName());
	}
}
