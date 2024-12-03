package com.school.manager.school_manager.helper;

import java.util.HashMap;
import java.util.Map;

public class ResponseHelper {

  public static Map<String, Object> buildResponse(Object data) {
    Map<String, Object> responseBody = new HashMap<>();
    responseBody.put("data", data);
    return responseBody;
  }

  public static Map<String, Object> buildResponse(Object data, String msg)
  {
    Map<String, Object> responseBody = new HashMap<>();
    responseBody.put("data", data);
    responseBody.put("message", msg);

    return responseBody;
  }
}
