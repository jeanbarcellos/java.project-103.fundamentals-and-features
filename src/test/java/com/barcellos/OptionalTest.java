package com.barcellos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

public class OptionalTest {

    /**
     * Um valor está presente somente se tivermos criado Optional com um valor não
     * nulo
     */
    @Test
    public void whenCreatesEmptyOptional_thenCorrect() {
        Optional<String> empty = Optional.empty();

        assertFalse(empty.isPresent());
    }

    /**
     * Pode-se criar um objeto Optional com o método estático of()
     */
    @Test
    public void givenNonNull_whenCreatesNonNullable_thenCorrect() {
        String name = "baeldung";

        Optional<String> opt = Optional.of(name);

        assertTrue(opt.isPresent());
    }

    /**
     * O argumento passado para o método of() não pode ser nulo. Caso contrário,
     * obteremos um NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void givenNull_whenThrowsErrorOnCreate_thenCorrect() {
        String name = null;

        Optional.of(name);
    }

    /**
     * Caso espera-se alguns valores nulos, pode-se usar o método ofNullable():
     */
    @Test
    public void givenNonNull_whenCreatesNullable_thenCorrect() {
        String name = "baeldung";

        Optional<String> opt = Optional.ofNullable(name);

        assertTrue(opt.isPresent());
    }

    /**
     * Ao fazer isso, se passarmos uma referência nula , ela não lançará uma
     * exceção, mas retornará um objeto Optional vazio
     */
    @Test
    public void givenNull_whenCreatesNullable_thenCorrect() {
        String name = null;
        Optional<String> opt = Optional.ofNullable(name);
        assertFalse(opt.isPresent());
    }

}
