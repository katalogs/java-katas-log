package com.kata.banking;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class AccountTest {

  @Test
  void depositAmount_1000_() {
    Account account = new Account();
    account.deposit(1000, LocalDate.now());
    assertThat(account.getBalance()).isEqualTo(1000);
    fail("rename test");
  }
}