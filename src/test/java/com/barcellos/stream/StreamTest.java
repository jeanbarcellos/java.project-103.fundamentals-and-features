package com.barcellos.stream;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamTest {

    // 2.1. Empty Stream

    @Test
    public void emptyStream() {
        Stream<String> streamEmpty = Stream.empty();

        assertEquals(0, streamEmpty.count());
    }

    // 2.2. Stream of Collection

    /**
     * Também podemos criar um stream de qualquer tipo de coleção ( Collection,
     * List, Set ):
     */
    @Test
    public void streamOfCollection() {
        Collection<String> collection = Arrays.asList("a", "b", "c");
        Stream<String> streamOfCollection = collection.stream();

        assertEquals(3, streamOfCollection.count());
    }

    // 2.3. Stream of Array

    @Test
    public void stramOfArray() {

        // Um array também pode ser a fonte de um stream
        String[] arr = new String[] { "a", "b", "c" };

        Stream<String> streamOfArrayFull = Arrays.stream(arr);

        // Também podemos criar um stream a partir de um array existente ou de parte de
        // um array:
        Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);

        assertEquals(3, streamOfArrayFull.count());
        assertEquals(2, streamOfArrayPart.count());
    }

    // 2.4. Stream.builder()

    /**
     * Quando o construtor é usado, o tipo desejado deve ser especificado
     * adicionalmente na parte direita da instrução, caso contrário, o método
     * build() criará uma instância do Stream<Object>:
     */
    @Test
    public void streamBuilder() {
        Stream<String> streamBuilder = Stream.<String>builder().add("a").add("b").add("c").build();

        assertEquals(3, streamBuilder.count());
    }

    // 2.5. Stream.generate()

    /**
     * método generate() aceita um Supplier<T> para geração de elemento. Como o
     * fluxo resultante é infinito, o desenvolvedor deve especificar o tamanho
     * desejado, ou o método generate() funcionará até atingir o limite de memória:
     */
    @Test
    public void streamGenerate() {
        Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);

        assertEquals(10, streamGenerated.count());
    }

    // 2.6. Stream.iterate()

    /**
     * Outra maneira de criar um fluxo infinito é usando o método iterate() :
     */
    @Test
    public void streamIterate() {

        Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);

        assertEquals(20, streamIterated.count());
    }

    // 2.7. Fluxo de Primitivos

    /**
     * Java 8 oferece a possibilidade de criar fluxos de três tipos primitivos: int,
     * long e double.
     *
     * Como Stream<T> é uma interface genérica, e não há como usar primitivos como
     * parâmetro de tipo com genéricos, três novas interfaces especiais foram
     * criadas: IntStream, LongStream, DoubleStream
     */
    @Test
    public void streamPrimitive() {
        IntStream intStream = IntStream.range(1, 3);
        LongStream longStream = LongStream.rangeClosed(1, 3);

        assertEquals(2, intStream.count());
        assertEquals(3, longStream.count());

    }

    // 2.8. Fluxo de String
    @Test
    public void streamString() {
        Stream<String> streamOfString = Pattern.compile(", ").splitAsStream("a, b, c");

        assertEquals(3, streamOfString.count());
    }

}
