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
  public void aClientMakesADepositOfOn(int depositAmount, int day, int month, int year) {
    account.deposit(depositAmount, LocalDate.of(year, month, day));
  }

  @And("a deposit of {int} on {int}-{int}-{int}")
  public void aDepositOfOn(int depositAmount, int day, int month, int year) {
    account.deposit(depositAmount, LocalDate.of(year, month, day));
  }

  @And("a withdrawal of {int} on {int}-{int}-{int}")
  public void aWithdrawalOfOn(int withdrawAmount, int day, int month, int year) {
    account.withdraw(withdrawAmount, LocalDate.of(year, month, day));
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
