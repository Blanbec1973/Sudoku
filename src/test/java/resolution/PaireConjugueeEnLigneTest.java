package resolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import model.grille.CaseEnCours;
import model.grille.Grille;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaireConjugueeEnLigneTest {
	private PaireConjugueeEnLigne methode;
	private final Grille grille = new Grille();
	
	@BeforeAll
	void setUpBeforeClass() {
		grille.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/PaireConjugueeEnLigne.sud");
		methode = new PaireConjugueeEnLigne(null, grille);
	}
	
	@Test
	@Order(1)
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(3);
		assertTrue(methode.traiteCaseEnCours(false));
		assertEquals(2,methode.c1);
		assertEquals(6,methode.c2);
	}

	@Test
	@Order(2)
	void testDetecteConfiguration() {
		CaseEnCours.setCaseEnCours(3);
		assertTrue(methode.detecteConfiguration());
	}
	
	@Test
	@Order(3)
	void testDetecteCandidatAEliminer() {
		CaseEnCours.setCaseEnCours(3);
		methode.c1=2;
		methode.c2=6;
		assertTrue(methode.detecteCandidatAEliminer());
		assertEquals(2, methode.candidatAEliminer);
		assertEquals(1,methode.numCaseAction);
		grille.elimineCandidat(1,2);
		grille.elimineCandidat(1,6);
		grille.elimineCandidat(2,2);
		grille.elimineCandidat(2,6);
		assertFalse(methode.detecteCandidatAEliminer());
	}

	@Test
	@Order(4)
	void testGetSimpleName() {
		assertEquals("PaireConjugueeEnLigne",methode.getSimpleName());
	}

}
