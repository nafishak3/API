package StepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinition {
//    Tidy pretty gherkin could write java code for this scrips
    @Given("User is on banking landing page")
    public void userOnBankingPageTest(){
        System.out.println("User is on banking landing page");
    }
    @When("User login into the application with username and password")
    public void loginWithUserAndPassTest(){
        System.out.println("User login into the application with the username and password");
    }

    @When("User login into the application with {string} and password {string}")
    public void withNameIncludedTest(String name1, String name2){
        System.out.println(name1+ " "+name2);
//        System.out.println(name2);
    }
    @Then("HomePage is populated")
    public void onHomePageTest(){
        System.out.println("HomePage is populated");
    }
    @And("cards displayed {string}")
    public void isCardPageDisplayedTest(String cards){
        System.out.println(cards);

    }
//    @And("cards are Not displayed")
//    public void cardsAreNotDisplayed(){
//        System.out.println("cards are Not displayed");
//    }
}
