package com.khel.runtime.security.dao;

import com.khel.runtime.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleDao extends RevisionRepository<Role, Long, Long>, JpaRepository<Role, Long>
{

  /**
   * Find a role by name
   * 
   * @param name
   * @return
   */
  public Role findByName(String name);

}
