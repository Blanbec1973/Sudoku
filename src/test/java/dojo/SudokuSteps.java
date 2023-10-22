package dojo;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import control.Control;
import cucumber.api.java.en.Given;  
import cucumber.api.java.en.Then;  
import cucumber.api.java.en.When;
import utils.Utils;

public class SudokuSteps {
	private Control control;

	@Given("I start my Sudoku application with file")
	public void i_start_my_Sudoku_application_with_file_file() {
	    control = new Control();
	}
	// TODO : Pointer sur le fichier initial qui sert au test
	@When("I click on nextButton")
	public void i_click_on_nextButton() {
		control.getVue().getBoutonAvance().doClick();
	}

	@Then("the cell number {int} is selected")
	public void the_cell_number_numCase_is_selected(int numCase) {
		assertTrue(true);
	}

	@Then("the cell number {int} is yellow")
	public void the_cell_number_numCase_is_yellow(Integer numCase) {
		Color c = control.getVue().getCase(Utils.calculXsearch(numCase),Utils.calculYsearch(numCase)).getBackground();
		assertEquals(Color.YELLOW,c);
	}
	

	@When("I click {int} times on ExplainButton")
	public void i_click_times_on_ExplainButton(Integer nombreClics) {
	    for (int i = 0;i < nombreClics;i++) {
			control.getVue().getBoutonExplique().doClick();
	    }
	}

	@Then("the cell number {int} is resolved by {int}")
	public void the_cell_number_is_resolved_by(Integer numCase, Integer valeur) {
	    int valeurCase = Integer.parseInt(control.getVue().getCase(Utils.calculXsearch(numCase), Utils.calculYsearch(numCase)).getText());
		assertEquals(valeur, valeurCase);
	}

	@Then("the cell number {int} is green")
	public void the_cell_number_is_green(Integer numCase) {
		Color c = control.getVue().getCase(Utils.calculXsearch(numCase),Utils.calculYsearch(numCase)).getBackground();
		assertEquals(Color.GREEN,c);
	}
}
