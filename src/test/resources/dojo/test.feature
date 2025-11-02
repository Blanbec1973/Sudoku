#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Resolution grille 40
  I want to use this template for my feature file

  Background: 
    Given I start my Sudoku application with file "src/test/resources/grillesTest/init67-40.sud"

  @tag1
  Scenario: Case1
    When I click on nextButton
    Then the cell number 39 is selected
    And the cell number 39 is yellow

  Scenario: Case2
    When I click 1 times on ExplainButton
    Then the cell number 39 is resolved by 6
    And the cell number 39 is green

  Scenario: Case3
    When I click 2 times on ExplainButton
    Then the cell number 50 is resolved by 8
    And the cell number 50 is green
