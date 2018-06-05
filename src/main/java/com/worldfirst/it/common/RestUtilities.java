package com.worldfirst.it.common;

import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.worldfirst.it.constants.Auth;
import com.worldfirst.it.constants.BaseURI;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class RestUtilities {
	
	private static Logger log = LogManager.getLogger(RestUtilities.class.getName());
	public static String ENDPOINT;
	public static RequestSpecBuilder REQUEST_BUILDER;
	public static RequestSpecification REQUEST_SPEC;
	public static ResponseSpecBuilder RESPONSE_BUILDER;
	public static ResponseSpecification RESPONSE_SPEC;
	
	public static void setEndPoint(String endPoint) {
		ENDPOINT = endPoint;
	}
	
	public static RequestSpecification getRequestSpecification () {
		
		Map <String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Basic d2ZvOmFoeDlqYWkxdWFmYWgyTW8=");
		headers.put("Content-Type", "application/json");
		headers.put("X-Correlation-ID", "1234");
		headers.put("X-Originator", "WFO");
		AuthenticationScheme authScheme = RestAssured.basic(Auth.USER, Auth.PASSWORD);
		REQUEST_BUILDER = new RequestSpecBuilder();
		REQUEST_BUILDER.setBaseUri(BaseURI.BASE_URI);
		REQUEST_BUILDER.addHeaders(headers);
		REQUEST_BUILDER.setAuth(authScheme);
		REQUEST_BUILDER.setRelaxedHTTPSValidation();
		REQUEST_SPEC = REQUEST_BUILDER.build();
		return REQUEST_SPEC;
		
	}
	
	public static RequestSpecification postRequestSpecification () {
		
		Map <String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Basic d2ZvOmFoeDlqYWkxdWFmYWgyTW8=");
		headers.put("Content-Type", "application/json");
		headers.put("X-Correlation-ID", "1234");
		headers.put("X-Originator", "WFO");
		AuthenticationScheme authScheme = RestAssured.basic(Auth.USER, Auth.PASSWORD);
		REQUEST_BUILDER = new RequestSpecBuilder();
		REQUEST_BUILDER.setBaseUri(BaseURI.BASE_URI);
		REQUEST_BUILDER.addHeaders(headers);
		REQUEST_BUILDER.setAuth(authScheme);
		REQUEST_BUILDER.setRelaxedHTTPSValidation();
		REQUEST_SPEC = REQUEST_BUILDER.build();
		return REQUEST_SPEC;
		
	}
	
	public static ResponseSpecification getResponseSpecification() {
		
		RESPONSE_BUILDER = new ResponseSpecBuilder();
		RESPONSE_BUILDER.expectContentType(ContentType.JSON);
		RESPONSE_BUILDER.expectStatusCode(200);
		RESPONSE_BUILDER.expectResponseTime(lessThan(60L), TimeUnit.SECONDS);
		RESPONSE_SPEC = RESPONSE_BUILDER.build();
		return RESPONSE_SPEC; 
	}
	
	public static ResponseSpecification postResponseSpecification() {
		
		RESPONSE_BUILDER = new ResponseSpecBuilder();
		RESPONSE_BUILDER.expectContentType(ContentType.JSON);
		RESPONSE_BUILDER.expectStatusCode(201);
		RESPONSE_BUILDER.expectResponseTime(lessThan(60L), TimeUnit.SECONDS);
		RESPONSE_SPEC = RESPONSE_BUILDER.build();
		return RESPONSE_SPEC; 
	}

	public static RequestSpecification addPayloadToSpec(RequestSpecification rspec, String requestBody){
		return  rspec.body(requestBody);
	}
	
	public static RequestSpecification createQueryParam(RequestSpecification rspec, String key, String value){
		return  rspec.queryParam(key, value);
	}
	
	public static RequestSpecification createQueryParams(RequestSpecification rspec, Map<String, String> queryMaps){
		return rspec.queryParams(queryMaps);
	}

	public static RequestSpecification createPathParam(RequestSpecification rspec, String key, String value){
		return rspec.pathParam(key, value);
	}

	public static RequestSpecification createPathParams(RequestSpecification rspec, Map<String, String> pathMaps){
		return rspec.pathParams(pathMaps);
	}
	
	public static String getQuoteResponse(Response res) {
		String response = res.getBody().prettyPrint();
		return response;
	}
	
	public static JsonPath jsonParser(String respo) {
		JsonPath quoteJsonPath = new JsonPath(respo);
		return quoteJsonPath;
	}
}
