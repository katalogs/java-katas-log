package com.kata.vavr.exercises;

import io.vavr.collection.Seq;
import io.vavr.collection.Vector;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * By doing these exercises you should have learned :
 * {@link Option#isDefined()}<br>
 * {@link Option#isEmpty()}<br>
 * {@link Option#map(Function)}<br>
 * {@link Option#getOrElse(Object)}<br>
 * {@link Option#getOrElseThrow(Supplier)}<br>
 * {@link Option#peek(Consumer)}<br>
 * {@link Option#flatMap(Function)}<br>
 */
class OptionExercises extends PetDomainKata {
    @Test
    void filterAListOfPerson() {
        // Filter this list with only defined persons
        Seq<Option<Person>> persons = Vector.of(
                Option.none(),
                Option.of(new Person("John", "Doe")),
                Option.of(new Person("Mary", "Smith")),
                Option.none());


        Seq<Person> definedPersons = null;

        assertThat(definedPersons).hasSize(2);
    }

    @Test
    void workingWithNull() {
        // Instantiate an Option of null (of type String)
        // map it to an Upper case function
        // then it must return the string "Ich bin empty" if empty
        Option<String> iamAnOption = null;
        String optionValue = null;

        assertThat(iamAnOption.isEmpty()).isTrue();
        assertThat(optionValue).isEqualTo("Ich bin empty");
    }

    @Test
    void findKaradoc() {
        // Find Karadoc in the people List or returns Perceval
        String foundPersonLastName = null;

        assertThat(foundPersonLastName).isEqualTo("Perceval");
    }

    @Test
    void findPersonOrDieTryin() {
        // Find a person matching firstName and lastName, throws an IllegalArgumentException if not found
        String firstName = "Rick";
        String lastName = "Sanchez";

        Person foundPerson = null;
        assertThatThrownBy(() -> {
            throw new Exception("call your code here");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void chainCall() {
        // Chain calls to the half method 4 times with start in argument
        // For each half append the value to the resultBuilder (side effect)
        Double start = 500d;
        StringBuilder resultBuilder = new StringBuilder();

        Option<Double> result = null;

        assertThat(result).isEqualTo(Option.none());
        assertThat(resultBuilder.toString()).isEqualTo("250.0125.0");
    }

    private Option<Double> half(Double x) {
        return x % 2 == 0 ? Option.of(x / 2) : Option.none();
    }
}