package com.kata.vavr.exercises;

import io.vavr.collection.Map;
import io.vavr.collection.Seq;
import io.vavr.collection.Vector;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * By doing these exercises you should have learned about the following APIs.
 * <p/>
 * {@link Seq#map(Function)}<br>
 * {@link Seq#filter(Predicate)}<br>
 * {@link Seq#reject(Predicate)}<br>
 * {@link Seq#find(Predicate)}<br>
 * {@link Seq#forAll(Predicate)}<br>
 * {@link Seq#count(Predicate)}<br>
 * {@link Seq#map(Function)}<br>
 * {@link Seq#flatMap(Function)}<br>
 * <p>
 * {@link Seq#count(Predicate)}<br>
 * <p>
 */
class CollectionsExercises extends PetDomainKata {
    @Test
    void getFirstNamesOfAllPeople() {
        // Replace null, with a transformation method on Seq.
        Seq<String> firstNames = null;

        Seq<String> expectedFirstNames = Vector.of("Mary", "Bob", "Ted", "Jake", "Barry", "Terry", "Harry", "John");
        assertThat(firstNames).isEqualTo(expectedFirstNames);
    }

    @Test
    void getNamesOfMarySmithsPets() {
        Person person = this.getPersonNamed("Mary Smith");
        Seq<Pet> pets = person.getPets();

        // Replace null, with a transformation method on Seq.
        Seq<String> names = null;

        assertThat(names.mkString()).isEqualTo("Tabby");
    }

    @Test
    void getPeopleWithCats() {
        // Replace null, with a positive filtering method on Seq.
        Seq<Person> peopleWithCats = null;

        assertThat(peopleWithCats).hasSize(2);
    }

    @Test
    void getPeopleWithoutCats() {
        // Replace null, with a negative filtering method on Seq.
        Seq<Person> peopleWithoutCats = null;
        assertThat(peopleWithoutCats).hasSize(6);
    }

    @Test
    void doAnyPeopleHaveCats() {
        //replace null with a Predicate lambda which checks for PetType.CAT
        boolean doAnyPeopleHaveCats = false;
        assertThat(doAnyPeopleHaveCats).isTrue();
    }

    @Test
    void doAllPeopleHavePets() {
        //replace with a method call send to this.people that checks if all people have pets
        Predicate<Person> predicate = null;
        boolean result = people.forAll(predicate);
        assertThat(result).isFalse();
    }

    @Test
    void howManyPeopleHaveCats() {
        // replace 0 with the correct answer
        int count = 0;
        assertThat(count).isEqualTo(2);
    }

    @Test
    void getPeopleWithPets() {
        // replace with only the pets owners
        Seq<Person> petPeople = null;
        assertThat(petPeople).hasSize(7);
    }

    @Test
    void getAllPetTypesOfAllPeople() {
        // retrieve all pet types owned by the people
        Seq<PetType> petTypes = null;

        assertThat(petTypes)
                .isEqualTo(Vector.of(PetType.CAT, PetType.DOG, PetType.SNAKE, PetType.BIRD, PetType.TURTLE, PetType.HAMSTER));
    }

    @Test
    void howManyPersonHaveCats() {
        // count the number of persons who owns cats
        int count = 0;
        assertThat(count).isEqualTo(2);
    }

    @Test
    void whoOwnsTheYoungestPet() {
        // find the person who owns the youngest pet
        Option<Person> person = null;
        assertThat(person.get().getFirstName()).isEqualTo("Jake");
    }

    @Test
    void whoOwnsTheOldestPet() {
        // find the person who owns the oldest pet
        Option<Person> person = null;
        assertThat(person.get().getFirstName()).isEqualTo("Ted");
    }

    @Test
    void averagePetAge() {
        // replace null by the average pet age
        Option<Double> average = null;
        assertThat(average.get()).isEqualTo(1.89);
    }

    @Test
    void totalPetAge() {
        // replace 0 by the total age of all the pets
        Number average = 0;
        assertThat(average).isEqualTo(17L);
    }

    @Test
    void petsNameSorted() {
        // sort pet names alphabetically
        String sortedPetNames = null;
        assertThat(sortedPetNames)
                .isEqualTo("Dolly,Fuzzy,Serpy,Speedy,Spike,Spot,Tabby,Tweety,Wuzzy");
    }

    @Test
    void sortByAge() {
        // Create a Seq<Integer> with ascending ordered age values.
        Seq<Integer> sortedAgeList = null;

        assertThat(sortedAgeList).hasSize(4);
        assertThat(sortedAgeList).isEqualTo(Vector.of(1, 2, 3, 4));
    }

    @Test
    void sortByDescAge() {
        // Create a Seq<Integer> with descending ordered age values.
        Seq<Integer> sortedAgeList = null;

        assertThat(sortedAgeList).hasSize(4);
        assertThat(sortedAgeList).isEqualTo(Vector.of(4, 3, 2, 1));
    }

    @Test
    void top3OlderPets() {
        // get the names of the 3 older pets
        Seq<Pet> top3OlderPets = null;

        assertThat(top3OlderPets).hasSize(3);
        assertThat(top3OlderPets.map(Pet::getName)).isEqualTo(Vector.of("Spike", "Dolly", "Tabby"));
    }

    @Test
    void getFirstPersonWithAtLeast2Pets() {
        // Find the first person who owns at least 2 pets
        Option<Person> firstPersonWithAtLeast2Pets = null;

        assertThat(firstPersonWithAtLeast2Pets.isDefined()).isTrue();
        assertThat(firstPersonWithAtLeast2Pets.get().getFirstName()).isEqualTo("Bob");
    }

    @Test
    void isThereAnyPetOlderThan4() {
        // Check whether any pets older than 4 exists or not
        boolean isThereAnyPetOlderThan4 = true;
        assertThat(isThereAnyPetOlderThan4).isFalse();
    }

    @Test
    void isEveryPetsOlderThan1() {
        // Check whether all pets are older than 1 or not
        boolean allOlderThan1 = false;
        assertThat(allOlderThan1).isTrue();
    }

    private Seq<String> filterParksFor(Seq<PetType> petTypes) {
        return this.parks.filter(park -> park.getAuthorizedPetTypes().containsAll(petTypes)).map(Park::getName);
    }

    @Test
    void getListOfPossibleParksForAWalkPerPerson() {
        // For each person described as "firstName lastName" returns the list of names possible parks to go for a walk
        Map<String, Seq<String>> possibleParksForAWalkPerPerson = null;

        assertThat(possibleParksForAWalkPerPerson.get("John Doe").get()).isEqualTo(Vector.of("Jurassic", "Central", "Hippy"));
        assertThat(possibleParksForAWalkPerPerson.get("Jake Snake").get()).isEqualTo(Vector.of("Jurassic", "Hippy"));
    }
}