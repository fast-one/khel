package com.khel.aspect;

import com.khel.data.jpa.type.EventType;
import com.khel.holder.EventHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by RKasturi on 2/20/2017.
 */
@Aspect
@Component
public class RecordEventAspect
{
  /**
   * advice to capture event
   *
   * @param event
   * @return
   * @throws Throwable
   */
//  @Pointcut("@annotation(event)")
  @Before("@annotation(event)")
  public void captureEvent(Event event) throws Throwable
  {
    //set event type in the context
    setEvent(event);
  }

  private void setEvent(Event event)
  {
    EventType eventType = EventHolder.get();
    if (eventType == null)
    {
      EventHolder.set(event.type());
    }
  }
}
