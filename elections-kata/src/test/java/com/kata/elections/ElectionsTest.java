package com.kata.elections;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

class ElectionsTest {
    @Test
    void electionWithoutDistricts() {
        Map<String, List<String>> list = Map.of(
                "District 1", Arrays.asList("Bob", "Anna", "Jess", "July"),
                "District 2", Arrays.asList("Jerry", "Simon"),
                "District 3", Arrays.asList("Johnny", "Matt", "Carole")
        );
        Elections elections = new Elections(list, false);
        elections.addCandidate("Michel");
        elections.addCandidate("Jerry");
        elections.addCandidate("Johnny");

        elections.voteFor("Bob", "Jerry", "District 1");
        elections.voteFor("Jerry", "Jerry", "District 2");
        elections.voteFor("Anna", "Johnny", "District 1");
        elections.voteFor("Johnny", "Johnny", "District 3");
        elections.voteFor("Matt", "Donald", "District 3");
        elections.voteFor("Jess", "Joe", "District 1");
        elections.voteFor("Simon", "", "District 2");
        elections.voteFor("Carole", "", "District 3");

        var results = elections.results();

        //todo : add approval tests here
    }

    @Test
    void electionWithDistricts() {
        Map<String, List<String>> list = Map.of(
                "District 1", Arrays.asList("Bob", "Anna", "Jess", "July"),
                "District 2", Arrays.asList("Jerry", "Simon"),
                "District 3", Arrays.asList("Johnny", "Matt", "Carole")
        );
        Elections elections = new Elections(list, true);
        elections.addCandidate("Michel");
        elections.addCandidate("Jerry");
        elections.addCandidate("Johnny");

        elections.voteFor("Bob", "Jerry", "District 1");
        elections.voteFor("Jerry", "Jerry", "District 2");
        elections.voteFor("Anna", "Johnny", "District 1");
        elections.voteFor("Johnny", "Johnny", "District 3");
        elections.voteFor("Matt", "Donald", "District 3");
        elections.voteFor("Jess", "Joe", "District 1");
        elections.voteFor("July", "Jerry", "District 1");
        elections.voteFor("Simon", "", "District 2");
        elections.voteFor("Carole", "", "District 3");

        var results = elections.results();

        //todo : add approval tests here
    }
}
