package model.grille;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaseEnCoursTest {

	@BeforeEach
	public void setUp() {
		CaseEnCours.setCaseEnCours(41);
	}

	@Test
	void testSetCaseEnCours() {
		assertEquals(4,CaseEnCours.getX());
		assertEquals(4,CaseEnCours.getY());
		assertEquals(3,CaseEnCours.getxRegion());
		assertEquals(3,CaseEnCours.getyRegion());
		assertEquals("5",CaseEnCours.getXEdition());
		assertEquals("5",CaseEnCours.getYEdition());
	}
	
	@Test
	void testSetCaseEnCours2() {
		CaseEnCours.setCaseEnCours(0, 0);
		assertEquals(0,CaseEnCours.getX());
		assertEquals(0,CaseEnCours.getY());
		assertEquals(0,CaseEnCours.getxRegion());
		assertEquals(0,CaseEnCours.getyRegion());
		assertEquals("1",CaseEnCours.getXEdition());
		assertEquals("1",CaseEnCours.getYEdition());
		assertEquals(1, CaseEnCours.getNumRegion());
	}
	
	@Test
	void testGetXSearch() {
		assertEquals(4,CaseEnCours.getX());
	}
	
	@Test
	void testGetYSearch() {
		assertEquals(4,CaseEnCours.getY());
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
