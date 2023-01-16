package com.kata.vavr.account;

import io.vavr.control.Option;

public class TwitterService {

  public Option<String> register(String email, String name) {
    return Option.of("TwitterAccountId");
  }

  public Option<String> authenticate(String email, String password) {
    return Option.of("ATwitterToken");
  }

  public Option<String> tweet(String token, String message) {
    return Option.of("TweetUrl");
  }
}