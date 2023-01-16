package com.kata.vavr.account;

import io.vavr.control.Option;
import java.util.UUID;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountService {

  private final UserService userService;
  private final TwitterService twitterService;
  private final BusinessLogger businessLogger;

  public Option<String> register(UUID id) {

    try {
      Option<RegisterProcess> process = this.userService.findById(id)
          .flatMap(this::registration)
          .flatMap(this::authentication)
          .flatMap(this::tweet);

      if (process.isEmpty()) {
        return Option.none();
      }

      this.userService.updateTwitterAccountId(id, process.get().getAccountId());
      businessLogger.logSuccessRegister(id);
      return Option.of(process.get().getTweetUrl());

    } catch (Exception ex) {
      this.businessLogger.logFailureRegister(id, ex);
      return Option.none();
    }
  }

  private Option<RegisterProcess> tweet(RegisterProcess state) {
    return this.twitterService.tweet(state.getToken(), "Hello I am " + state.getUser().getName())
        .map(state::withTweetUrl);
  }

  private Option<RegisterProcess> authentication(RegisterProcess state) {
    return this.twitterService.authenticate(state.getUser().getEmail(), state.getUser().getPassword())
        .map(state::withToken);
  }

  private Option<RegisterProcess> registration(User user) {
    return this.twitterService.register(user.getEmail(), user.getName())
        .map(accountId -> new RegisterProcess().withUser(user).withAcoountId(accountId));
  }
}