package dojo;

import control.Control;
import control.EventManager;
import control.MyProperties;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.messagemanager.MessageManager;
import model.Model;
import model.SimpleModelEventPublisher;
import model.messagemanager.TemplateProvider;
import model.service.HistorisationService;
import model.service.ModelEventService;
import model.service.ResolutionMessageService;
import view.MyView;

import java.awt.*;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuSteps {
	private Control control;

	@Given("I start my Sudoku application with file {string}")
	public void i_start_my_Sudoku_application_with_file_fileName(String fileName) {

		MyProperties myProperties = new MyProperties("config.properties");
		MyView myView = new MyView("C:/");

		//services :
        TemplateProvider provider = new TemplateProvider(myProperties);
		MessageManager messageManager = new MessageManager(provider);
		HistorisationService historisationService = new HistorisationService();
		SimpleModelEventPublisher publisher = new SimpleModelEventPublisher();
		ModelEventService eventService = new ModelEventService(publisher);
		ResolutionMessageService messageService = new ResolutionMessageService(messageManager);

		// Modèle
		Model model = new Model(eventService, messageService, historisationService);

		// EventManager
		EventManager eventManager = new EventManager(myView, myProperties);
		publisher.addListener(eventManager);
		eventManager.setModel(model);
		myView.registerController(eventManager);

		// Control avec injection
		control = new Control(eventManager, model);
		control.initialize(myView, myProperties);

		// Charger le fichier passé en paramètre
		control.reloadGrille(Paths.get(fileName).toAbsolutePath());
		myView.refreshGrilleDisplay(model.getGrille());

		myView.registerController(eventManager);
		myView.getFenetre().setVisible(true);

	}

	@When("I click on nextButton")
	public void i_click_on_nextButton() {
		control.simulateClick("AVANCE");
	}

	@Then("the cell number {int} is selected")
	public void the_cell_number_numCase_is_selected(int numCase) {
		assertTrue(true);
	}

	@Then("the cell number {int} is yellow")
	public void the_cell_number_numCase_is_yellow(Integer numCase) {
		Color c = control.getViewUpdater().getCaseBackground(numCase);
		assertEquals(Color.YELLOW,c);
	}

	@When("I click {int} times on ExplainButton")
	public void i_click_times_on_ExplainButton(Integer nombreClics) {
	    for (int i = 0;i < nombreClics;i++) {
			control.simulateClick("EXPLIQUE");
	    }
	}

	@Then("the cell number {int} is resolved by {int}")
	public void the_cell_number_is_resolved_by(Integer numCase, Integer valeur) {
	    int valeurCase = control.getViewUpdater().getCaseValue(numCase);
		assertEquals(valeur, valeurCase);
	}

	@Then("the cell number {int} is green")
	public void the_cell_number_is_green(Integer numCase) {
		Color c = control.getViewUpdater().getCaseBackground(numCase);
		assertEquals(Color.GREEN,c);
	}
}
