package resolution.candidatunique;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import resolution.ResolutionAction;
import resolution.ZoneType;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CandidatUniqueDansRegionTest {
	private static CandidatUniqueDansZone methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		Grille grille = new Grille();
		String fileName = "src/test/resources/grillesTest/CandidatUniqueDansRegion.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new CandidatUniqueDansZone(
				grille,
				ZoneType.BLOC
		);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseContext context = new CaseContext(2);
		assertFalse(methode.traiteCaseEnCours(context, false).isPresent());
		
		CaseContext context2 = new CaseContext(42);
		ResolutionAction action = methode.traiteCaseEnCours(context2, false)
				                         .orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(4, action.getSolution());
	}

	@Test
	void testGetSimpleName() {
		assertEquals("CandidatUniqueDansZone",methode.getSimpleName());
		assertEquals(ZoneType.BLOC, methode.getZone());
	}
}
