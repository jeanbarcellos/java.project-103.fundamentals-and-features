package com.barcellos.optional;

import java.util.Optional;

public class Person {
    private String name;
    private int age;
    private String password;

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<Integer> getAge() {
        return Optional.ofNullable(age);
    }

    public Optional<String> getPassword() {
        return Optional.ofNullable(password);
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}