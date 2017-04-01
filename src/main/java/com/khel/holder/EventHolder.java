package com.khel.holder;

import com.khel.data.jpa.type.EventType;
import org.springframework.core.NamedThreadLocal;

public class EventHolder
{
  private static final ThreadLocal<EventType> eventHolder = new NamedThreadLocal<EventType>("RecordEvent");

  /**
   * Reset ThreadLocal storage
   */
  public static void reset()
  {
    eventHolder.remove();
  }

  /**
   * Set ThreadLocal storage
   * Reset if null is passed in
   *
   * @param event
   */
  public static void set(EventType event)
  {
    if (event == null)
    {
      reset();
    }
    else
    {
      eventHolder.set(event);
    }
  }

  /**
   * Get the ThreadLocal stored RecordEvent
   *
   * @return
   */
  public static EventType get()
  {
    return eventHolder.get();
  }
}
