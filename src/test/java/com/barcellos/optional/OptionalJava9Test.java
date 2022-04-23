package com.barcellos.optional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class OptionalJava9Test {

    // 2. O método or()

    /**
     * Às vezes, quando nosso Optional está vazio, queremos executar alguma outra
     * ação que também retorne um opcional.
     *
     * Antes do Java 9, a classe Optional tinha apenas os métodos orElse() e
     * orElseGet() , mas ambos precisavam retornar valores desempacotados.
     *
     * Java 9 introduz o método or() que retorna outro Optional lentamente se nosso
     * Optional estiver vazio. Se nosso primeiro Optional tiver um valor definido, o
     * lambda passado para o método or() não será invocado e o valor não será
     * calculado e retornado
     */
    @Test
    public void givenOptional_whenPresent_thenShouldTakeAValueFromIt() {

        // given
        String expected = "properValue";
        Optional<String> value = Optional.of(expected);
        Optional<String> defaultValue = Optional.of("default");

        // when
        Optional<String> result = value.or(() -> defaultValue);

        // then
        assertThat(result.get()).isEqualTo(expected);
    }

    /**
     * No caso de Optional estar vazio , o resultado retornado será o mesmo que
     * defaultValue
     */
    @Test
    public void givenOptional_whenEmpty_thenShouldTakeAValueFromOr() {

        // given
        String defaultString = "default";
        Optional<String> value = Optional.empty();
        Optional<String> defaultValue = Optional.of(defaultString);

        // when
        Optional<String> result = value.or(() -> defaultValue);

        // then
        assertThat(result.get()).isEqualTo(defaultString);
    }

    // 3. O método ifPresentOrElse()

    /**
     * Quando temos uma instância Optional, geralmente queremos executar uma ação
     * específica no valor subjacente dela. Por outro lado, se o Optional estiver
     * vazio, queremos registrá-lo ou rastrear esse fato incrementando alguma
     * métrica.
     *
     * O método ifPresentOrElse() é criado exatamente para esses cenários. Podemos
     * passar um Consumer que será invocado se o Optional estiver definido, e
     * Runnable que será executado se o Optional estiver vazio.
     *
     * Digamos que temos um Optional definido e queremos incrementar um contador
     * específico se o valor estiver presente:
     */
    @Test
    public void givenOptional_whenPresent_thenShouldExecuteProperCallback() {

        // given
        Optional<String> value = Optional.of("properValue");
        AtomicInteger successCounter = new AtomicInteger(0);
        AtomicInteger onEmptyOptionalCounter = new AtomicInteger(0);

        // when
        value.ifPresentOrElse(
                v -> successCounter.incrementAndGet(),
                onEmptyOptionalCounter::incrementAndGet);

        // then
        assertThat(successCounter.get()).isEqualTo(1);
        assertThat(onEmptyOptionalCounter.get()).isEqualTo(0);
    }

    @Test
    public void givenOptional_whenNotPresent_thenShouldExecuteProperCallback() {

        // given
        Optional<String> value = Optional.empty();
        AtomicInteger successCounter = new AtomicInteger(0);
        AtomicInteger onEmptyOptionalCounter = new AtomicInteger(0);

        // when
        value.ifPresentOrElse(
                v -> successCounter.incrementAndGet(),
                onEmptyOptionalCounter::incrementAndGet);

        // then
        assertThat(successCounter.get()).isEqualTo(0);
        assertThat(onEmptyOptionalCounter.get()).isEqualTo(1);
    }
}
