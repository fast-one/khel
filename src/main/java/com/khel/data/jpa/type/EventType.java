package com.khel.data.jpa.type;

public enum EventType
{
 USER_CREATE(1),
 PERSON_UPDATE(2),
 ADDRESS_CREATE(3),;

 private int type;

 private EventType(int type)
 {
  this.type = type;
 }
}
