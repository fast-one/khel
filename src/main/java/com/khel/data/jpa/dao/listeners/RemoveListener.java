package com.khel.data.jpa.dao.listeners;

import javax.persistence.PreRemove;

/**
 * Created by RKasturi on 12/21/2016.
 */

public class RemoveListener
{

  @PreRemove
  public void blockPreRemove(Object ob) throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Remove/Delete operation is not supported for entity: "+ob.getClass().getSimpleName());
  }

}
