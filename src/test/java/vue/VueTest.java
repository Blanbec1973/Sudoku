package vue;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class VueTest {
	private static Vue vue;
	
	@BeforeAll
	static void setUpBeforeClass() {
		vue = new Vue();
	}

	@Test
	void testVue() {
		assertEquals("Sudoku",vue.getFenetre().getTitle());
	}

	@Test
	void testGetFenetre() {
		assertTrue(vue.getFenetre() instanceof JFrame);
		assertEquals("Sudoku",vue.getFenetre().getTitle());
	}

	@Test
	void testGetLogTextArea() {
		assertTrue(vue.getLogTextArea() instanceof TextArea);
		assertEquals("Grille initiale chargée.",vue.getLogTextArea().getText());
	}

	@Test
	void testGetBoutonAvance() {
		assertTrue(vue.getBoutonAvance() instanceof JButton);
		assertEquals(">>",vue.getBoutonAvance().getText());
	}

	@Test
	void testGetBoutonExplique() {
		assertTrue(vue.getBoutonExplique() instanceof JButton);
		assertEquals("?",vue.getBoutonExplique().getText());
	}

	@Test
	void testGetBoutonRecule() {
		assertTrue(vue.getBoutonRecule() instanceof JButton);
		assertEquals("<<",vue.getBoutonRecule().getText());
	}

	@Test
	void testGetRangResolution() {
		assertTrue(vue.getRangResolution() instanceof JLabel);
		assertEquals("0",vue.getRangResolution().getText());
	}

	@Test
	void testGetCase() {
		assertTrue(vue.getCase(0, 0) instanceof JButton);
	}

	@Test
	void testSetCase() {
		vue.setCase(1, 1, "toto");
		assertEquals("toto",vue.getCase(1, 1).getText());
		assertEquals(Color.GREEN,vue.getCase(1, 1).getBackground());
	}

	@Test
	void testSetCaseAvantExplication() {
		vue.setCaseAvantExplication(1, 1);
		assertEquals(Color.YELLOW,vue.getCase(1, 1).getBackground());
	}

	@Test
	void testSetCaseCandidats() {
		vue.setCaseCandidats(3, 3, "titi");
		assertEquals("titi",vue.getCase(3, 3).getText());
		assertEquals(Color.WHITE,vue.getCase(3, 3).getBackground());
		assertEquals(12, vue.getCase(3, 3).getFont().getSize());
	}

	@Test
	void testSetCaseInitiale() {
		vue.setCaseInitiale(5, 5, "tutu");
		assertEquals("tutu",vue.getCase(5, 5).getText());
		assertEquals(Color.GRAY,vue.getCase(5, 5).getBackground());
		assertEquals(24, vue.getCase(5, 5).getFont().getSize());
	}

}
