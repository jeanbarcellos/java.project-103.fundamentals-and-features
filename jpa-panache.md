Extensão específica da JPA do Repository - Panache

```java
// package io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
//
// import javax.persistence.EntityManager;
// import javax.persistence.LockModeType;
// import io.quarkus.hibernate.orm.panache.PanacheQuery;
// import io.quarkus.panache.common.Sort
// import io.quarkus.panache.common.Parameters

public interface PanacheRepositoryBase<Entity, Id> {

  EntityManager getEntityManager();

  EntityManager getEntityManager(Class<?> clazz);

  // ------------------------------

  Entity findById(Id id);

  Entity findById(Id id, LockModeType lockModeType);

  Optional<Entity> findByIdOptional(Id id);

  Optional<Entity> findByIdOptional(Id id, LockModeType lockModeType);

  PanacheQuery<Entity> find(String query, Object... params);

  PanacheQuery<Entity> find(String query, Sort sort, Object... params);

  PanacheQuery<Entity> find(String query, Map<String,Object> params);

  PanacheQuery<Entity> find(String query, Sort sort, Map<String,Object> params);

  PanacheQuery<Entity> find(String query, Parameters params);

  PanacheQuery<Entity> find(String query, Sort sort, Parameters params);

  PanacheQuery<Entity> findAll();

  PanacheQuery<Entity> findAll(Sort sort);

  // ------------------------------

  List<Entity> list(String query, Object... params);

  List<Entity> list(String query, Sort sort, Object... params);

  List<Entity> list(String query, Map<String,Object> params);

  List<Entity> list(String query, Sort sort, Map<String,Object> params);

  List<Entity> list(String query, Parameters params);

  List<Entity> list(String query, Sort sort, Parameters params);

  List<Entity> listAll();

  List<Entity> listAll(Sort sort);

  // ------------------------------

  Stream<Entity> stream(String query, Object... params);

  Stream<Entity> stream(String query, Sort sort, Object... params);

  Stream<Entity> stream(String query, Map<String,Object> params);

  Stream<Entity> stream(String query, Sort sort, Map<String,Object> params);

  Stream<Entity> stream(String query, Parameters params);

  Stream<Entity> stream(String query, Sort sort, Parameters params);

  Stream<Entity> streamAll(Sort sort);

  Stream<Entity> streamAll();

  // ------------------------------

  public long count();

  public long count(String query, Object... params);

  long count(String query, Map<String,Object> params);

  public long count(String query, Parameters params);

  // ------------------------------

  void persist(java.lang.Iterable<Entity> entities);

  void persist(Stream<Entity> entities);

  void persist(Entity firstEntity, Entity... entities);

  void persist(Entity entity);

  void persistAndFlush(Entity entity);

  boolean isPersistent(Entity entity);

  public void flush();

  // ------------------------------

  public int update(String query, Object... params);

  int update(String query, Map<String,Object> params);

  public int update(String query, Parameters params);

  // ------------------------------

  boolean deleteById(Id id);

  void delete(Entity entity);

  public long delete(String query, Object... params);

  long delete(String query, Map<String,Object> params);

  public long delete(String query, Parameters params);

  public long deleteAll();

}
```
