package com.khel.config;

import com.khel.data.jpa.revision.CustomRevisionEntity;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * By default revision number is Integer, following is done to override the behavior
 * please look at https://github.com/spring-projects/spring-data-envers/issues/4
 */
public class CustomRepositoryFactoryBean<R extends JpaRepository<T, ID>, T,
        ID extends Serializable> extends EnversRevisionRepositoryFactoryBean
{
  public CustomRepositoryFactoryBean()
  {
    setRevisionEntityClass(CustomRevisionEntity.class);
  }
}
