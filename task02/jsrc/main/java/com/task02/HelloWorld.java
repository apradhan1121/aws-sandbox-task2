package com.task02;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.model.RetentionSetting;

import java.util.HashMap;

import java.util.Map;

@LambdaHandler(lambdaName = "hello_world", roleName = "hello_world-role",
		isPublishVersion = false,
		logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED)
public class HelloWorld implements RequestHandler<Map<String, Object>, Map<String, Object>> {

	@Override
	public Map<String, Object> handleRequest(Map<String, Object> request, Context context) {
		Map<String, Object> response = new HashMap<>();

		String resource = (String) request.get("resource");
		String httpMethod = (String) request.get("httpMethod");

		if ("/hello".equals(resource) && "GET".equals(httpMethod)) {
			response.put("statusCode", 200);
			response.put("message", "Hello from Lambda");
		} else {
			response.put("statusCode", 400);
			response.put("message", String.format("Bad request syntax or unsupported method." +
					"Request path: %s. HTTP method: %s", resource, httpMethod));
		}

		return response;
	}
}
