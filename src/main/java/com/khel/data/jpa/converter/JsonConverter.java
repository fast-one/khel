package com.khel.data.jpa.converter;

import com.khel.data.Exception.DataConversionException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by RKasturi on 3/31/2017.
 */
@Converter(autoApply = true)
public class JsonConverter implements AttributeConverter<JSONObject, String>
{
  @Override
  public String convertToDatabaseColumn(JSONObject attribute)
  {
    return attribute.toJSONString();
  }

  @Override
  public JSONObject convertToEntityAttribute(String dbData)
  {
    JSONParser parser = new JSONParser();
    JSONObject jsonObject = null;
    try
    {
      jsonObject = (JSONObject) parser.parse(dbData);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
      throw new DataConversionException(e.getMessage(), e);
    }
    return jsonObject;
  }
}
