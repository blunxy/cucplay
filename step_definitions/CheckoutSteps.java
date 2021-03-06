package step_definitions;

import static org.junit.Assert.*;
import org.junit.Rule;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.contrib.java.lang.system.SystemOutRule;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import cucumber.api.java.en.*;
import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.api.Scenario;
import cucumber.api.PendingException;
import implementation.Checkout;

import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class CheckoutSteps {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    ByteArrayInputStream keyboardInput;

    private PrintStream oldErr;
    private PrintStream oldOut;
    int bananaPrice = 0;
    Checkout checkout;

    SystemOutRule systemOutRule;
    // @Rule
    // public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    TextFromStandardInputStream systemInMock;
    //@Rule
    //public final TextFromStandardInputStream systemInMock
    //= emptyStandardInputStream();

    @Before
    public void beforeCallingScenario() {
        System.out.println("*********** About to start the scenario.");
        String s = String.format("1%n2%n");
        keyboardInput = new ByteArrayInputStream(s.getBytes());
        System.setIn(keyboardInput);
        checkout = new Checkout();
        // systemOutRule = new SystemOutRule().enableLog();

        // oldErr = System.err;
        // oldOut = System.out;
        // System.setOut(new PrintStream(outContent));
        // System.setErr(new PrintStream(errContent));
    }

    @After
    public void afterRunningScenario(Scenario scenario) {
        System.out.println("*********** Just finished running scenario: "
            + scenario.getStatus());
        System.setIn(System.in);
        // System.setOut(oldOut);
        // System.setErr(oldErr);
    }

    @Given("^the price of a \"([^\"]*)\" is (\\d+)c$")
    public void thePriceOfAIsC(String itemName, int price) throws Throwable {

        bananaPrice = price;
    }

    @When("^I checkout (\\d+) \"([^\"]*)\"$")
    public void iCheckout(int itemCount, String itemName) throws Throwable {

        checkout.add(itemCount, bananaPrice);
    }

    @Then("^the total price should be (\\d+)c$")
    public void theTotalPriceShouldBeC(int total) throws Throwable {
        assertThat(checkout.total()).isEqualTo(total);
        // assertThat(systemOutRule.getLog()).contains("foo");
    }
}