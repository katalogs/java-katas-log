package com.kata.vavr.exercises;

import com.kata.vavr.account.AccountService;
import com.kata.vavr.account.BusinessLoggerImpl;
import com.kata.vavr.account.TwitterService;
import com.kata.vavr.account.UserService;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * In this real life example you will have to combine what you have learned on vavr
 * Refactor the AccountService, write cleaner code by using vavr
 * You will use at least :
 * <p/>
 * {@link Option#getOrElse(Object)}<br>
 * {@link Try#map(Function)}<br>
 * {@link Try#flatMap(Function)}<br>
 * {@link Try#onSuccess(Consumer)}<br>
 * {@link Try#onFailure(Consumer)}<br>
 */
class RealLifeExample {
    private final UUID BUD_SPENCER = UUID.fromString("376510ae-4e7e-11ea-b77f-2e728ce88125");
    private final UUID UNKNOWN_USER = UUID.fromString("376510ae-4e7e-11ea-b77f-2e728ce88121");

    private final AccountService accountService = new AccountService(
        new UserService(),
        new TwitterService(),
        new BusinessLoggerImpl());

    @Test
    void register_BudSpencer_should_return_a_new_tweet_url() {
        Option<String> tweetUrl = accountService.register(BUD_SPENCER);
        assertThat(tweetUrl.isDefined()).isTrue();
        assertThat(tweetUrl.get()).isEqualTo("TweetUrl");
    }

    @Test
    void register_an_unknown_user_should_return_an_error_message() {
        Option<String> tweetUrl = accountService.register(UNKNOWN_USER);
        assertThat(tweetUrl.isEmpty()).isTrue();
        assertThat(tweetUrl.getOrElse((String) null)).isNull();
    }
}