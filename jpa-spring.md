Extensão específica da JPA do Repository - Spring Data

[Doc](https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html)

```java
// package org.springframework.data.jpa.repository.JpaRepository

public interface JpaRepository<T, ID>
     extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {

    @Override
    List<T> findAll();

    @Override
    List<T> findAll(Sort sort);

    @Override
    List<T> findAllById(Iterable<ID> ids);

    // ------------------------------

    @Override
    <S extends T> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends T> S saveAndFlush(S entity);

    <S extends T> List<S> saveAllAndFlush(Iterable<S> entities);

    // ------------------------------

    void deleteAllInBatch(Iterable<T> entities);

    void deleteAllByIdInBatch(Iterable<ID> ids);

    void deleteAllInBatch();

    // ------------------------------

    T getById(ID id);

    // ------------------------------

    @Override
    <S extends T> List<S> findAll(Example<S> example);

    @Override
    <S extends T> List<S> findAll(Example<S> example, Sort sort);
}

```

Extensão de CrudRepositorypara fornecer métodos adicionais para recuperar entidades usando a abstração de paginação e classificação

[Doc](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html)

```java
// package org.springframework.data.repository.PagingAndSortingRepository

public interface PagingAndSortingRepository<T, ID>
    extends CrudRepository<T, ID> {

    // Retorna todas as entidades classificadas pelas opções fornecidas
    Iterable<T> findAll(Sort sort);

    // Retorna uma {@link Page} de entidades que atendem à restrição de paginação fornecida no objeto {@code Pageable}.
    Page<T> findAll(Pageable pageable);
}

```

Interface para operações CRUD genéricas em um repositório para um tipo específico.

[Doc](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html)

```java
// package org.springframework.data.repository.CrudRepository

public interface CrudRepository<T, ID>
    extends Repository<T, ID> {

    <S extends T> S save(S entity);

    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    // ------------------------------

    Optional<T> findById(ID id);

    // ------------------------------

    boolean existsById(ID id);

    // ------------------------------

    Iterable<T> findAll();

    Iterable<T> findAllById(Iterable<ID> ids);

    // ------------------------------

    long count();

    // ------------------------------

    void deleteById(ID id);

    void delete(T entity);

    void deleteAllById(Iterable<? extends ID> ids);

    void deleteAll(Iterable<? extends T> entities);

    void deleteAll();
}
```

[Doc](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/Repository.html)

```java
// package org.springframework.data.repository.Repository

public interface Repository<T, ID> {

}
```
