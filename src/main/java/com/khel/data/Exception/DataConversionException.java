package com.khel.data.Exception;

/**
 * Created by RKasturi on 3/31/2017.
 */
public class DataConversionException extends RuntimeException
{
  public DataConversionException(String message)
  {
    super(message);
  }

  public DataConversionException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public DataConversionException(Throwable cause)
  {
    super(cause);
  }

  protected DataConversionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
  {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
