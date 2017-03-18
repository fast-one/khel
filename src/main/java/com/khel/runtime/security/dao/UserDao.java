package com.khel.runtime.security.dao;

import com.khel.runtime.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserDao extends RevisionRepository<User, Long, Long>, JpaRepository<User, Long>
{

  /**
   * Return a UserAccount by username
   *
   * @param login
   * @return user
   */
  @Transactional(readOnly = true)
  @Query(" SELECT u FROM User u WHERE (u.userName = :login or u.email = :login or u.mobile = :login)")
  User findByUsername(@Param("login") String login);

}
