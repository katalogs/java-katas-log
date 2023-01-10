package com.kata.vavr.account;

import io.vavr.control.Option;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class AccountService {

  private final UserService userService;
  private final TwitterService twitterService;
  private final BusinessLogger businessLogger;

  public Option<String> register(UUID id) {
    try {
      Option<User> user = this.userService.findById(id);

      if (user.isEmpty()) {
        return Option.none();
      }

      String accountId = this.twitterService.register(user.get().getEmail(), user.get().getName());

      if (accountId == null) {
        return Option.none();
      }

      String twitterToken = this.twitterService.authenticate(user.get().getEmail(), user.get().getPassword());

      if (twitterToken == null) {
        return Option.none();
      }

      String tweetUrl = this.twitterService.tweet(twitterToken, "Hello I am " + user.get().getName());

      if (tweetUrl == null) {
        return Option.none();
      }

      this.userService.updateTwitterAccountId(id, accountId);
      businessLogger.logSuccessRegister(id);
      return Option.of(tweetUrl);

    } catch (Exception ex) {
      this.businessLogger.logFailureRegister(id, ex);
      return null;
    }
  }
}