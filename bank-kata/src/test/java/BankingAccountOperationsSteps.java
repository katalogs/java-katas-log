import static org.assertj.core.api.Assertions.assertThat;

import com.kata.banking.Account;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.LocalDate;

public class BankingAccountOperationsSteps {

  Account account = new Account();
  String ticket;

  @Given("a client makes a deposit of {int} on {int}-{int}-{int}")
  @Given("a deposit of {int} on {int}-{int}-{int}")
  public void aClientMakesADepositOfOn(int amount, int day, int month, int year) {
    account.deposit(amount, LocalDate.of(year, month, day));
  }

  @Given("a withdrawal of {int} on {int}-{int}-{int}")
  public void aWithdrawalOfOn(int amount, int day, int month, int year) {
    account.withdraw(amount, LocalDate.of(year, month, day));
  }

  @When("she prints her bank statement")
  public void shePrintsHerBankStatement() {
    ticket = account.printBankStatement();
  }

  @Then("^she would see")
  public void sheWouldSee(String expectedTicket) {
    assertThat(ticket).isEqualTo(expectedTicket);
  }
}
