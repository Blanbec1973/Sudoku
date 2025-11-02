package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CandidatUniqueDansRegionTest {
	private static CandidatUniqueDansRegion methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		Grille grille = new Grille();
		String fileName = "src/test/resources/grillesTest/CandidatUniqueDansRegion.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new CandidatUniqueDansRegion(grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(2);
		assertFalse(methode.traiteCaseEnCours(false).isPresent());
		
		CaseEnCours.setCaseEnCours(42);
		assertTrue(methode.traiteCaseEnCours(false).isPresent());
		assertEquals(4, methode.getSolution());
	}

	@Test
	void testGetSimpleName() {
		assertEquals("CandidatUniqueDansRegion",methode.getSimpleName());
	}
}
