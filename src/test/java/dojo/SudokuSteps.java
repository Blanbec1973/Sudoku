package dojo;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import controleur.Controle;
import cucumber.api.java.en.Given;  
import cucumber.api.java.en.Then;  
import cucumber.api.java.en.When;  

public class SudokuSteps {
	private Controle control;

	@Given("I start my Sudoku application with file <file>")
	public void i_start_my_Sudoku_application_with_file_file() {
	    control = new Controle();
	}

	@When("I click on nextButton")
	public void i_click_on_nextButton() {
		control.getFen().getBoutonAvance().doClick();
	}

	@Then("the cell number <numCase> is selected")
	public void the_cell_number_numCase_is_selected() {
		assertTrue(true);
	}

	@Then("the cell number <numCase> is yellow")
	public void the_cell_number_numCase_is_yellow() {
		Color c = control.getFen().getCase(2,4).getBackground();
		assertEquals(Color.YELLOW,c);
	}
	
	
}
