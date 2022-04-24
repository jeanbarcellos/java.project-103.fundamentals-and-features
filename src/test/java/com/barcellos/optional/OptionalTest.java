package com.barcellos.optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private String getMyDefault() {
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

    // 10. Conditional Return With filter()

    @Test
    public void whenOptionalFilterWorks_thenCorrect() {
        Integer year = 2016;
        Optional<Integer> yearOptional = Optional.of(year);

        boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
        assertTrue(is2016);

        boolean is2017 = yearOptional.filter(y -> y == 2017).isPresent();
        assertFalse(is2017);
    }

    private boolean priceIsInRange1(Modem modem) {
        boolean isInRange = false;

        if (modem != null && modem.getPrice() != null
                && (modem.getPrice() >= 10
                        && modem.getPrice() <= 15)) {

            isInRange = true;
        }
        return isInRange;
    }

    @Test
    public void whenFiltersWithoutOptional_thenCorrect() {
        assertTrue(priceIsInRange1(new Modem(10.0)));
        assertFalse(priceIsInRange1(new Modem(9.9)));
        assertFalse(priceIsInRange1(new Modem(null)));
        assertFalse(priceIsInRange1(new Modem(15.5)));
        assertFalse(priceIsInRange1(null));
    }

    private boolean priceIsInRange2(Modem modem2) {
        return Optional.ofNullable(modem2)
                .map(Modem::getPrice)
                .filter(p -> p >= 10)
                .filter(p -> p <= 15)
                .isPresent();
    }

    @Test
    public void whenFiltersWithOptional_thenCorrect() {
        assertTrue(priceIsInRange2(new Modem(10.0)));
        assertFalse(priceIsInRange2(new Modem(9.9)));
        assertFalse(priceIsInRange2(new Modem(null)));
        assertFalse(priceIsInRange2(new Modem(15.5)));
        assertFalse(priceIsInRange2(null));
    }

    // 11. Transforming Value With map()

    @Test
    public void givenOptional_whenMapWorks_thenCorrect() {
        List<String> companyNames = Arrays.asList("paypal", "oracle", "", "microsoft", "", "apple");
        Optional<List<String>> listOptional = Optional.of(companyNames);

        int size = listOptional
                .map(List::size)
                .orElse(0);

        assertEquals(6, size);
    }

    @Test
    public void givenOptional_whenMapWorks_thenCorrect2() {
        String name = "baeldung";
        Optional<String> nameOptional = Optional.of(name);

        int len = nameOptional
                .map(String::length)
                .orElse(0);

        assertEquals(8, len);
    }

    @Test
    public void givenOptional_whenMapWorksWithFilter_thenCorrect() {
        String password = " password ";
        Optional<String> passOpt = Optional.of(password);

        boolean correctPassword = passOpt.filter(
                pass -> pass.equals("password")).isPresent();

        assertFalse(correctPassword);

        correctPassword = passOpt
                .map(String::trim)
                .filter(pass -> pass.equals("password"))
                .isPresent();

        assertTrue(correctPassword);
    }

    // 12. Transforming Value With flatMap()

    @Test
    public void givenOptional_whenFlatMapWorks_thenCorrect2() {
        Person person = new Person("john", 26);
        Optional<Person> personOptional = Optional.of(person);

        Optional<Optional<String>> nameOptionalWrapper = personOptional.map(Person::getName);
        Optional<String> nameOptional = nameOptionalWrapper.orElseThrow(IllegalArgumentException::new);
        String name1 = nameOptional.orElse("");
        assertEquals("john", name1);

        String name = personOptional
                .flatMap(Person::getName)
                .orElse("");

        assertEquals("john", name);
    }

    // 13. Chaining Optionals in Java 8

    private Optional<String> getEmpty() {
        return Optional.empty();
    }

    private Optional<String> getHello() {
        return Optional.of("hello");
    }

    private Optional<String> getBye() {
        return Optional.of("bye");
    }

    private Optional<String> createOptional(String input) {
        if (input == null || "".equals(input) || "empty".equals(input)) {
            return Optional.empty();
        }
        return Optional.of(input);
    }

    @Test
    public void givenThreeOptionals_whenChaining_thenFirstNonEmptyIsReturned() {
        Optional<String> found = Stream.of(getEmpty(), getHello(), getBye())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

        assertEquals(getHello(), found);
    }

    @Test
    public void givenThreeOptionals_whenChaining_thenFirstNonEmptyIsReturnedAndRestNotEvaluated() {
        Optional<String> found = Stream.<Supplier<Optional<String>>>of(this::getEmpty, this::getHello, this::getBye)
                .map(Supplier::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

        assertEquals(getHello(), found);
    }

    @Test
    public void givenTwoOptionalsReturnedByOneArgMethod_whenChaining_thenFirstNonEmptyIsReturned() {
        Optional<String> found = Stream.<Supplier<Optional<String>>>of(
                () -> createOptional("empty"),
                () -> createOptional("hello"))
                .map(Supplier::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

        assertEquals(createOptional("hello"), found);
    }

    @Test
    public void givenTwoEmptyOptionals_whenChaining_thenDefaultIsReturned() {
        String found = Stream.<Supplier<Optional<String>>>of(
                () -> createOptional("empty"),
                () -> createOptional("empty"))
                .map(Supplier::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseGet(() -> "default");

        assertEquals("default", found);
    }

    // 15. Misuse of Optionals

    public static List<Person> search(List<Person> people, String name) {
        return doSearch(people, name, 0);
    }

    public static List<Person> search(List<Person> people, String name, int age) {
        return doSearch(people, name, age);
    }

    private static List<Person> doSearch(List<Person> people, String name, int age) {
        // Null checks for people and name
        return people.stream()
                .filter(p -> p.getName().equals(name))
                .filter(p -> p.getAge().get().intValue() >= age)
                .collect(Collectors.toList());
    }

    /*
     * when CreatesEmptyOptional
     * then Correct
     *
     * given NonNull
     * when CreatesNonNullable
     * then Correct
     *
     * should ReturnTrue
     * should ThrowsException
     */

}
