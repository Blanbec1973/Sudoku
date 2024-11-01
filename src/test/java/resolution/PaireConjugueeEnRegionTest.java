package resolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import model.grille.CaseEnCours;
import model.grille.Grille;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaireConjugueeEnRegionTest {
	private PaireConjugueeEnRegion methode;
	private final Grille grille = new Grille();



	@BeforeAll
	void setUpBeforeClass() {
		grille.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/PaireConjugueeEnRegion.sud");
		methode = new PaireConjugueeEnRegion(null, grille);
	}

	@Test
	@Order(1)
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(36);
		assertTrue(methode.traiteCaseEnCours(false));
		assertEquals(3,methode.c1);
		assertEquals(7,methode.c2);
		CaseEnCours.setCaseEnCours(18);
		assertFalse(methode.traiteCaseEnCours(false));

	}

	@Test
	@Order(2)
	void testDetecteConfiguration() {
		CaseEnCours.setCaseEnCours(36);
		assertTrue(methode.detecteConfiguration());
		CaseEnCours.setCaseEnCours(18);
		assertFalse(methode.detecteConfiguration());
		CaseEnCours.setCaseEnCours(2);
		assertFalse(methode.detecteConfiguration());
	}
	
	@Test
	@Order(3)
	void testDetecteCandidatAEliminer() {
		CaseEnCours.setCaseEnCours(36);
		methode.c1=3;
		methode.c2=7;
		assertTrue(methode.detecteCandidatAEliminer());
		assertEquals(43,methode.numCaseAction);

		grille.elimineCandidat(43,3);
		grille.elimineCandidat(43,7);
		grille.elimineCandidat(52,7);
		grille.elimineCandidat(54,7);

		assertFalse(methode.detecteCandidatAEliminer());
	}


	@Test
	@Order(4)
	void testGetSimpleName() {
		assertEquals("PaireConjugueeEnRegion",methode.getSimpleName());
	}
}
