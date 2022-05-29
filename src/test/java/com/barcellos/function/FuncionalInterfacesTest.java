package com.barcellos.function;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FuncionalInterfacesTest {

    /**
     * Predicate -> Predicados
     *
     * Função que aceita um único argumento do tipo T de entrada
     * e retorna um valor booleano
     *
     * Equivalente ao Predicate do C#
     */
    public void predicates_test1() {

        Predicate<String> predicate = text -> text.length() > 0;

        predicate.test("foo"); // true
        predicate.negate().test("foo"); // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }

    /**
     * Function -> Funções
     *
     * Função que aceita um único argumento do tipo T de entrada
     * e retorna um valor do tipo R
     *
     * Equivalente a Func do C#
     */
    public void functions_test1() {
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123"); // "123"
    }

    /**
     * Consumidores
     *
     * Operação que aceita um único argumento de entrada
     * e não retorna nenhum resultado.
     *
     * Ao contrário da maioria das outras interfaces funcionais,
     * espera-se que o Consumer opere por meio de efeitos colaterais.
     *
     * Operações a serem executadas em um único argumento de entrada.
     *
     * Função que aceita um único argumento do tipo T de entrada
     * e não retorna nenhum (void) resultado.
     */
    public void consumers_test1() {
        Consumer<Person> greeter = person -> System.out.println("Hello, " + person.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));
    }

    /**
     * Fornecedores
     *
     * Os Suppliers produzem um resultado de um determinado tipo genérico.
     * Ao contrário das Functions, os Suppliers não aceitam argumentos.
     *
     * Função que não aceita argumentos de entrada
     * e retorna um valor do tipo <T>
     */
    public void suppliers_test1() {
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get(); // new Person
    }

    public void test1() {

        // Predicate
        Predicate<String> predicate = text -> text.length() > 0;
        boolean result = predicate.test("foo"); // true

        // Function
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        String result2 = backToString.apply("123"); // "123"

        // Consumer
        Consumer<Person> greeter = person -> System.out.println("Hello, " + person.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));

        // Supplier
        Supplier<Person> personSupplier = Person::new;
        Person result4 = personSupplier.get(); // new Person
    }

    private class Person {
        public String firstName;
        public String lastName;

        public Person() {
        }

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

    }

}
