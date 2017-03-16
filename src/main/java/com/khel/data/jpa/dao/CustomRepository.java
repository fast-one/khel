package com.khel.data.jpa.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by RKasturi on 1/6/2017.
 */
@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable> extends JpaRepository<T, ID>
{
  //block deleteAllInBatch
  @Override
  @Query(value = "select 'Operation Not Supported' from dual", nativeQuery = true)
  void deleteAllInBatch();

  //block deleteInBatch
  @Override
  @Query(value = "select 'Operation Not Supported' from dual", nativeQuery = true)
  void deleteInBatch(Iterable<T> entities);

  //block delete
  @Override
  @Query(value = "select 'Operation Not Supported' from dual", nativeQuery = true)
  void delete(ID id);

  //block delete
  @Override
  @Query(value = "select 'Operation Not Supported' from dual", nativeQuery = true)
  void delete(T entity);

  //block delete
  @Override
  @Query(value = "select 'Operation Not Supported' from dual", nativeQuery = true)
  void delete(Iterable<? extends T> entities);

  //block deleteAll
  @Override
  @Query(value = "select 'Operation Not Supported' from dual", nativeQuery = true)
  void deleteAll();

}
