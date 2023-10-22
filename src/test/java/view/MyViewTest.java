package view;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MyViewTest {
	private static MyView myView;
	
	@BeforeAll
	static void setUpBeforeClass() {
		myView = new MyView();
	}

	@Test
	void testVue() {
		assertEquals("Sudoku", myView.getFenetre().getTitle());
	}

	@Test
	void testGetFenetre() {
		assertTrue(myView.getFenetre() instanceof JFrame);
		assertEquals("Sudoku", myView.getFenetre().getTitle());
	}

	@Test
	void testGetLogTextArea() {
		assertTrue(myView.getLogTextArea() instanceof TextArea);
		assertEquals("Grille initiale chargée.", myView.getLogTextArea().getText());
	}

	@Test
	void testGetBoutonAvance() {
		assertTrue(myView.getBoutonAvance() instanceof JButton);
		assertEquals(">>", myView.getBoutonAvance().getText());
	}

	@Test
	void testGetBoutonExplique() {
		assertTrue(myView.getBoutonExplique() instanceof JButton);
		assertEquals("?", myView.getBoutonExplique().getText());
	}

	@Test
	void testGetBoutonRecule() {
		assertTrue(myView.getBoutonRecule() instanceof JButton);
		assertEquals("<<", myView.getBoutonRecule().getText());
	}

	@Test
	void testGetRangResolution() {
		assertTrue(myView.getRangResolution() instanceof JLabel);
		assertEquals("0", myView.getRangResolution().getText());
	}

	@Test
	void testGetCase() {
		assertTrue(myView.getCase(0, 0) instanceof JButton);
	}

	@Test
	void testSetCase() {
		myView.setCase(1, 1, "toto");
		assertEquals("toto", myView.getCase(1, 1).getText());
		assertEquals(Color.GREEN, myView.getCase(1, 1).getBackground());
	}

	@Test
	void testSetCaseAvantExplication() {
		myView.setCaseAvantExplication(1, 1);
		assertEquals(Color.YELLOW, myView.getCase(1, 1).getBackground());
	}

	@Test
	void testSetCaseCandidats() {
		myView.setCaseCandidats(3, 3, "titi");
		assertEquals("titi", myView.getCase(3, 3).getText());
		assertEquals(Color.WHITE, myView.getCase(3, 3).getBackground());
		assertEquals(12, myView.getCase(3, 3).getFont().getSize());
	}

	@Test
	void testSetCaseInitiale() {
		myView.setCaseInitiale(5, 5, "tutu");
		assertEquals("tutu", myView.getCase(5, 5).getText());
		assertEquals(Color.GRAY, myView.getCase(5, 5).getBackground());
		assertEquals(24, myView.getCase(5, 5).getFont().getSize());
	}

}
