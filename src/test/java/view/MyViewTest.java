package view;

import model.grille.CaseEnCours;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(DisplayCondition.class)
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
		assertNotNull(myView.getFenetre());
		assertEquals("Sudoku", myView.getFenetre().getTitle());
	}

	@Test
	void testGetLogTextArea() {
		assertNotNull(myView.getLogTextArea());
		assertEquals("Grille initiale chargée.", myView.getLogTextArea().getText());
	}

	@Test
	void testGetBoutonAvance() {
		assertNotNull(myView.getBoutonAvance());
		assertEquals(">>", myView.getBoutonAvance().getText());
	}

	@Test
	void testGetBoutonExplique() {
		assertNotNull(myView.getBoutonExplique());
		assertEquals("?", myView.getBoutonExplique().getText());
	}

	@Test
	void testGetBoutonRecule() {
		assertTrue(myView.getBoutonRecule() instanceof JButton);
		assertEquals("<<", myView.getBoutonRecule().getText());
	}

	@Test
	void testGetRangResolution() {
		assertNotNull(myView.getRangResolution());
		assertEquals("0", myView.getRangResolution().getText());
	}

	@Test
	void testGetCase() {
		assertNotNull(myView.getCase(0, 0));
	}

	@Test
	void testSetCase() {
		myView.setCase(1, "toto");
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
		myView.setCaseCandidats(21, "titi");
		assertEquals("titi", myView.getCase(3, 3).getText());
		assertEquals(Color.WHITE, myView.getCase(3, 3).getBackground());
		assertEquals(12, myView.getCase(3, 3).getFont().getSize());
	}

	@Test
	void testSetCaseInitiale() {
		myView.setCaseInitiale(39, "tutu");
		assertEquals("tutu", myView.getCase(5, 5).getText());
		assertEquals(Color.GRAY, myView.getCase(5, 5).getBackground());
		assertEquals(24, myView.getCase(5, 5).getFont().getSize());
	}

	@Test
	void testInsertDisplayMessage() {
		String message = "Test message";
		myView.insertDisplayMessage(message);
		assertTrue(myView.getLogTextArea().getText().startsWith(message));
	}

	@Test
	void testUpdateResolutionRank() {
		myView.updateResolutionRank(5);
		assertEquals("5", myView.getRangResolution().getText());

		myView.updateResolutionRank(-2);
		assertEquals("3", myView.getRangResolution().getText());
	}

	@Test
	void testResetView() {
		myView.updateResolutionRank(10);
		myView.insertDisplayMessage("Old message");
		myView.resetView("Start message");

		assertEquals("0", myView.getRangResolution().getText());
		assertEquals("Start message", myView.getLogTextArea().getText());
	}

	@Test
	void testUpdateSingleCaseTrouvee() {
		Grille grille = new Grille();
		CaseEnCours.setCaseEnCours(1);
		grille.setValeurCaseEnCours(4);
		myView.updateSingleCase(grille, 1);
		assertEquals("4", myView.getCase(0, 0).getText());
		assertEquals(Color.GREEN, myView.getCase(0, 0).getBackground());
	}

	@Test
	void testGetCaseBackground() {
		myView.getCase(0, 0).setBackground(Color.BLUE);
		assertEquals(Color.BLUE, myView.getCaseBackground(1));
	}

	@Test
	void testGetCaseValue() {
		myView.getCase(0, 0).setText("8");
		assertEquals(8, myView.getCaseValue(1));
	}

	@Test
	void testRemoveLastLogLine() {
		myView.insertDisplayMessage("Line 1");
		myView.insertDisplayMessage("Line 2");
		myView.removeLastLogLine();
		assertFalse(myView.getLogTextArea().getText().contains("Line 2"));
	}

	@Test
	void testShowMessageDialog() {
		// Ce test est difficile à automatiser sans framework GUI.
		// On peut le laisser vide ou le simuler avec un mock si besoin.
		assertDoesNotThrow(() -> myView.showMessageDialog(null, "Message"));
	}

}
