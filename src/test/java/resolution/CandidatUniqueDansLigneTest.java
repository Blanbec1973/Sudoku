package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;


class CandidatUniqueDansLigneTest {
	private static CandidatUniqueDansLigne methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		Grille grille = new Grille();
		String fileName = "src/test/resources/grillesTest/CandidatUniqueDansLigne.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new CandidatUniqueDansLigne(grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(2);
		assertFalse(methode.traiteCaseEnCours(false).isPresent());
		
		CaseEnCours.setCaseEnCours(39);
		assertTrue(methode.traiteCaseEnCours(false).isPresent());
		assertEquals(6, methode.getSolution());
	}
	@Test
	void testGetSimpleName() {
		assertEquals("CandidatUniqueDansLigne",methode.getSimpleName());
	}

}
