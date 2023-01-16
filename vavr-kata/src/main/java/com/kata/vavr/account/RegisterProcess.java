package com.kata.vavr.account;

import lombok.Data;

@Data
public class RegisterProcess {

  private User user;
  private String accountId;
  private String token;
  private String tweetUrl;

  public RegisterProcess() {
  }

  private RegisterProcess(User user, String accountId, String token, String url) {
    this.user = user;
    this.accountId = accountId;
    this.token = token;
    this.tweetUrl = url;
  }

  public RegisterProcess withTweetUrl(String url) {
    RegisterProcess process = this.clone();
    process.setTweetUrl(url);
    return process;
  }

  public RegisterProcess clone() {
    return new RegisterProcess(this.getUser(), this.getAccountId(), this.getToken(), this.getTweetUrl());
  }

  public RegisterProcess withToken(String token) {
    RegisterProcess process = this.clone();
    process.setToken(token);
    return process;
  }

  public RegisterProcess withUser(User user) {
    RegisterProcess process = this.clone();
    process.setUser(user);
    return process;
  }

  public RegisterProcess withAcoountId(String accountId) {
    RegisterProcess process = this.clone();
    process.setAccountId(accountId);
    return process;
  }
}
