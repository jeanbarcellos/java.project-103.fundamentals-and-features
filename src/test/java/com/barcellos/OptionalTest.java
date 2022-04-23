package com.barcellos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;
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

    /**
     * Quando temos um objeto Optional retornado de um método ou criado por nós,
     * podemos verificar se há um valor nele ou não com o método isPresent()
     *
     * Este método retorna true se o valor encapsulado não for nulo.
     */
    @Test
    public void givenOptional_whenIsPresentWorks_thenCorrect() {
        Optional<String> opt = Optional.of("Baeldung");
        assertTrue(opt.isPresent());

        opt = Optional.ofNullable(null);
        assertFalse(opt.isPresent());
    }

    /**
     * a partir do Java 11, podemos fazer o oposto com o método isEmpty
     */
    @Test
    public void givenAnEmptyOptional_thenIsEmptyBehavesAsExpected() {
        Optional<String> opt = Optional.of("Baeldung");
        assertFalse(opt.isEmpty());

        opt = Optional.ofNullable(null);
        assertTrue(opt.isEmpty());
    }

    // 4. Ação condicional com ifPresent()

    /**
     * O método ifPresent() nos permite executar algum código no valor encapsulado
     * se ele for não nulo.
     *
     * Antes de Optional faríamos:
     *
     * if(name != null) {
     * System.out.println(name.length());
     * }
     */
    @Test
    public void givenOptional_whenIfPresentWorks_thenCorrect() {
        Optional<String> opt = Optional.of("baeldung");

        opt.ifPresent(name -> System.out.println(name.length()));
    }

    // 5. Default Value With orElse()

    /**
     * O método orElse() é usado para recuperar o valor encapsulado em uma instância
     * Optional.
     */
    @Test
    public void whenOrElseWorks_thenCorrect() {
        String nullName = null;
        String defaultName = "john";

        String name = Optional.ofNullable(nullName).orElse(defaultName);

        assertEquals(defaultName, name);
    }

    /**
     * O método orElseGet() é semelhante ao orElse().
     * Porém, ao invés de pegar um valor para retornar se o valor Optional não
     * estiver presente, ele pega uma interface funcional do fornecedor, que é
     * invocada e retorna o valor da invocação
     */
    @Test
    public void whenOrElseGetWorks_thenCorrect() {
        String nullName = null;

        String name = Optional.ofNullable(nullName).orElseGet(() -> "john");

        assertEquals("john", name);
    }

    // 7. Difference Between orElse and orElseGet()

    public String getMyDefault() {
        System.out.println("Getting Default Value");
        return "Default Value";
    }

    @Test
    public void whenOrElseGetAndOrElseOverlap_thenCorrect() {
        String text = null;

        String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
        assertEquals("Default Value", defaultText);

        defaultText = Optional.ofNullable(text).orElse(getMyDefault());
        assertEquals("Default Value", defaultText);
    }

    @Test
    public void whenOrElseGetAndOrElseDiffer_thenCorrect() {
        String text = "Text present";

        System.out.println("Using orElseGet:");
        String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
        assertEquals("Text present", defaultText);

        System.out.println("Using orElse:");
        defaultText = Optional.ofNullable(text).orElse(getMyDefault());
        assertEquals("Text present", defaultText);
    }

    // 8. Exceções com orElseThrow()

    /**
     * O método orElseThrow() segue orElse() e orElseGet() e adiciona uma nova
     * abordagem para lidar com um valor ausente.
     *
     * Em vez de retornar um valor padrão quando o valor encapsulado não está
     * presente, ele lança uma exceção
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenOrElseThrowWorks_thenCorrect() {
        String nullName = null;

        String name = Optional.ofNullable(nullName).orElseThrow(
                IllegalArgumentException::new);
    }

    /**
     * O Java 10 introduziu uma versão simplificada sem argumentos do método
     * orElseThrow().
     * No caso de um Optional vazio, lança um NoSuchElementException
     */
    @Test(expected = NoSuchElementException.class)
    public void whenNoArgOrElseThrowWorks_thenCorrect() {
        String nullName = null;

        String name = Optional.ofNullable(nullName).orElseThrow();
    }

    // 9. Returning Value With get()

    /**
     * A abordagem final para recuperar o valor encapsulado é o método get()
     */
    @Test
    public void givenOptional_whenGetsValue_thenCorrect() {
        Optional<String> opt = Optional.of("baeldung");

        String name = opt.get();

        assertEquals("baeldung", name);
    }

    /**
     * No entanto, ao contrário das três abordagens anteriores, get() só pode
     * retornar um valor se o objeto encapsulado não for null; caso contrário, ele
     * lança uma exceção de nenhum elemento.
     *
     * Esta é a principal falha do método get().
     *
     * Idealmente, Opcional deve nos ajudar a evitar tais exceções imprevistas.
     * Portanto, essa abordagem funciona de acordo com os objetivos de Optional e
     * provavelmente será preterida em uma versão futura.
     *
     * Portanto, é aconselhável usar as outras variantes que nos permitem preparar e
     * lidar explicitamente com o caso nulo .
     */
    @Test(expected = NoSuchElementException.class)
    public void givenOptionalWithNull_whenGetThrowsException_thenCorrect() {
        Optional<String> opt = Optional.ofNullable(null);

        String name = opt.get();
    }

}
