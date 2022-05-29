_Repositório apenas para estudo_

# Project Demo 103 - Fundamentos e funcionalidades

Demo para revisão de fundamentos e funcionalidades do JAVA 8

Recursos:

- Maven
- JUnit
- AssertJ

## Optional

Objetos Opcionais

- _Optional_ deve ser usado como um tipo de retorno.
- Não é recomendado tentar usá-lo como um tipo de campo.

### Referências

- https://www.baeldung.com/java-optional
- https://www.baeldung.com/java-9-optional
- https://www.baeldung.com/java-optional-return

<br>
<br>

## Collections

- Listas
- Conjuntos
- Mapas

### Referencias

- http://www.dsc.ufcg.edu.br/~jacques/cursos/p2/html/ed/colecoes.htm

<br>
<br>

## Stream

A `java.util.Stream` representa uma sequência de elementos nos quais uma ou mais operações podem ser executadas.

As operações de fluxo são **intermediárias** ou **terminais**

As coleções no Java 8 são estendidas para que se possa simplesmente criar fluxos chamando `Collection.stream()`;

A API de `stream` ajuda a substituir os loops , for-each e while. Permite concentrar-se na lógica da operação, mas não na iteração sobre a _sequência_ de elementos.

**Filtering (filter)** - Filtro

O método `filter()` nos permite escolher um fluxo de elementos que satisfaçam um predicado.

**Mapping (map)** - Mapeando

Pode-se usar o método `map()` para converter elementos de um Stream aplicando uma função especial a eles e coletar esses novos elementos em um `Stream`.

**Matching (match)** - Coincidindo

Fornece um conjunto útil de instrumentos para validar elementos de uma sequência de acordo com algum predicado. Para fazer isso, um dos seguintes métodos pode ser usado: `anyMatch()`, `allMatch()`, `noneMatch()`

```java
boolean isValid = list.stream().anyMatch(element -> element.contains("h")); // true
boolean isValidOne = list.stream().allMatch(element -> element.contains("h")); // false
boolean isValidTwo = list.stream().noneMatch(element -> element.contains("h")); // false
```

**Reduction (reduce)** - Redução

Permite reduzir uma sequência de elementos a algum valor de acordo com uma função especificada com a ajuda do método `reduce()` do tipo `Stream`.

Este método recebe dois parâmetros: primeiro – valor inicial, segundo – uma função de acumulador.

```java
List<Integer> integers = Arrays.asList(1, 1, 1);
Integer reduced = integers.stream().reduce(23, (a, b) -> a + b);
```

**Collecting** - Coletando

- A redução também pode ser fornecida pelo método `collect()` do tipo `Stream`.
- Esta operação é muito útil no caso de converter um fluxo em uma **Collection** ou **Map** e representar um fluxo na forma de uma única string.
- Existe uma classe de utilitário Collectors que fornece uma solução para quase todas as operações típicas de coleta.

**Count** - Contagem

Count é uma operação de _terminal_ que retorna o número de elementos no fluxo como um arquivo `long`.

**Sorted (sort)** - Classificação

Sorted é uma operação _intermediária_ que retorna uma exibição classificada do fluxo. Os elementos são classificados em ordem natural, a menos que você passe um custom `Comparator`.

```java
List<String> resultList
  = list.stream().map(element -> element.toUpperCase()).collect(Collectors.toList());
```

    - Este código usa a operação terminal collect() para reduzir um `Stream<String>` para `List<String>`.

Java 8 oferece a possibilidade de criar fluxos de três tipos primitivos: int, long e double.

Como Stream<T> é uma interface genérica, e não há como usar primitivos como parâmetro de tipo com genéricos, três novas interfaces especiais foram criadas: **_IntStream_**, **_LongStream_**, **_DoubleStream_**

```java
IntStream intStream = IntStream.range(1, 3);
LongStream longStream = LongStream.rangeClosed(1, 3);
```

### Referências:

- https://github.com/loiane/java8-tutorial#streams
- https://www.baeldung.com/java-8-streams-introduction
- https://www.baeldung.com/java-8-streams

<br>
<br>
<br>

## Interfaces funcionais integradas

### **Predicates** - Predicados

Os `Predicates` são funções que aceitam um argumento do tipo `T` e produzem resultado de valor booleano.

A interface contém vários métodos padrão para compor predicados para termos lógicos complexos (e, ou, nega)

Interface:

```java
public interface Predicate<T> {

  boolean test(T t);
}
```

Exemplos:

```java
Predicate<String> predicate = (s) -> s.length() > 0;

predicate.test("foo");              // true
predicate.negate().test("foo");     // false

Predicate<Boolean> nonNull = Objects::nonNull;
Predicate<Boolean> isNull = Objects::isNull;

Predicate<String> isEmpty = String::isEmpty;
Predicate<String> isNotEmpty = isEmpty.negate();
```

<br>

### **Functions** - Funções

As `functions` aceitam um argumento e produzem um resultado.

Os métodos padrão podem ser usados ​​para encadear várias funções juntas (compose eThen).

Interface:

```java
public interface Function<T, R> {

   R apply(T t);
}
```

Exemplos:

```java
Function<String, Integer> toInteger = Integer::valueOf;
Function<String, String> backToString = toInteger.andThen(String::valueOf);

backToString.apply("123");     // "123"
```

<br>

### **Supplier** - Fornecedores

Representa um _fornecedor_ de resultados.

A interface `Supplier` do Java 8, nada mais é do que uma interface funcional, basicamente ela não aceita argumentos e retorna um resultado.

Os Suppliers produzem um resultado de um determinado tipo genérico. Ao contrário das _Functions_, os _Supliers_ não aceitam argumentos.

Interface:

```java
public interface Supplier<Tout> {

   T get();
}
```

Exemplos:

Exemplo 1:

```java
Supplier<Person> personSupplier = Person::new;
personSupplier.get();   // new Person
```

Exempo 2:

```java
// Retornar a data atual
Supplier<LocalDate> supplierLocalDate = () -> LocalDate.now();

// Note que criamos uma instância do tipo Supplier que recebe uma lambda expression que retorna a data atual

// Como o Supplier recebe uma expressão lambda, ainda podemos simplificar nosso código da seguinte forma

Supplier<LocalDate> supplierLocalDate = LocalDate::now;
```

<br>

### **Consumers** - Consumidores

Os consumidores representam as operações a serem executadas em um único argumento de entrada

Interface:

```java
public interface Consumer<Tint> {

   void accept(T t);
}
```

Exemplos:

```java
Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
greeter.accept(new Person("Luke", "Skywalker"));
```

#### Referencias

- https://receitasdecodigo.com.br/java/exemplos-supplier-java-8

## Imports

```java
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
```

## Referencias

- https://github.com/loiane/java8-tutorial
