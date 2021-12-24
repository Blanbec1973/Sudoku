package modele;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CaseEnCoursTest {

	@BeforeEach
	public void setUp() throws Exception {
		CaseEnCours.setCaseEnCours(41);
	}

	@Test
	void testSetCaseEnCours() {
		assertEquals(4,CaseEnCours.getXSearch());
		assertEquals(4,CaseEnCours.getYSearch());
		assertEquals(3,CaseEnCours.getxRegion());
		assertEquals(3,CaseEnCours.getyRegion());
		assertEquals("5",CaseEnCours.getXSearchEdition());
		assertEquals("5",CaseEnCours.getYSearchEdition());
	}
	
	@Test
	void testSetCaseEnCours2() {
		CaseEnCours.setCaseEnCours(0, 0);
		assertEquals(0,CaseEnCours.getXSearch());
		assertEquals(0,CaseEnCours.getYSearch());
		assertEquals(0,CaseEnCours.getxRegion());
		assertEquals(0,CaseEnCours.getyRegion());
		assertEquals("1",CaseEnCours.getXSearchEdition());
		assertEquals("1",CaseEnCours.getYSearchEdition());
		assertEquals(1, CaseEnCours.getNumRegion());
	}
	
	@Test
	void testGetXSearch() {
		assertEquals(4,CaseEnCours.getXSearch());
	}
	
	@Test
	void testGetYSearch() {
		assertEquals(4,CaseEnCours.getYSearch());
	}
	
	@Test
	void testGetxRegion() {
		assertEquals(3,CaseEnCours.getxRegion());
	}
	
	@Test
	void testGetyRegion() {
		assertEquals(3,CaseEnCours.getyRegion());
	}
	
	@Test
	void testGetNumCase() {
		assertEquals(41,CaseEnCours.getNumCase());
	}

	@Test
	void testGetNumRegion() { assertEquals(5, CaseEnCours.getNumRegion());}

}
