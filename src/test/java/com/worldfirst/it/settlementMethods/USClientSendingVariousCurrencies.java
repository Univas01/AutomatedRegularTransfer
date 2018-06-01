package com.worldfirst.it.settlementMethods;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.worldfirst.it.common.RestUtilities;
import com.worldfirst.it.constants.EndPoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;


public class USClientSendingVariousCurrencies {
	
	private static Logger log = LogManager.getLogger(USClientSendingVariousCurrencies.class.getName());
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	Response response;

	@BeforeMethod
	public void setUp() {
		requestSpec = RestUtilities.getRequestSpecification();
		responseSpec = RestUtilities.getResponseSpecification();
	}
	
	@Test
	public void USClientSendsUSDTest() {
		log.info("***USClientSendsUSD Test***");
		
		Map<String, String> client = new HashMap<String, String>();
		client.put("AccountNo", "2120417009C");
		client.put("Currency", "USD");
		
		response = 
		given()
			.spec(RestUtilities.createPathParams(requestSpec, client))
		.when()
			.get(EndPoints.SETTLEMENT_METHOD)
		.then()
			.spec(responseSpec)
            .log().all()
			.extract().response();
	}
	
	@Test
	public void USClientSendsGBPTest() {
		log.info("***USClientSendsGBP Test***");
		
		Map<String, String> client = new HashMap<String, String>();
		client.put("AccountNo", "2120417009C");
		client.put("Currency", "GBP");
		
		response = 
		given()
			.spec(RestUtilities.createPathParams(requestSpec, client))
		.when()
			.get(EndPoints.SETTLEMENT_METHOD)
		.then()
			.spec(responseSpec)
            .log().all()
			.extract().response();
	}
	
	@Test
	public void USClientSendsEURTest() {
		log.info("***USClientSendsEUR Test***");
		
		Map<String, String> client = new HashMap<String, String>();
		client.put("AccountNo", "2120417009C");
		client.put("Currency", "EUR");
		
		response = 
		given()
			.spec(RestUtilities.createPathParams(requestSpec, client))
		.when()
			.get(EndPoints.SETTLEMENT_METHOD)
		.then()
			.spec(responseSpec)
            .log().all()
			.extract().response();
	}
	
	@Test
	public void USClientSendsAUDTest() {
		log.info("***USClientSendsAUD Test***");
		
		Map<String, String> client = new HashMap<String, String>();
		client.put("AccountNo", "2120417009C");
		client.put("Currency", "AUD");
		
		response = 
		given()
			.spec(RestUtilities.createPathParams(requestSpec, client))
		.when()
			.get(EndPoints.SETTLEMENT_METHOD)
		.then()
			.spec(responseSpec)
            .log().all()
			.extract().response();
	}
	
	@Test
	public void USClientSendsCADTest() {
		log.info("***USClientSendsCAD Test***");
		
		Map<String, String> client = new HashMap<String, String>();
		client.put("AccountNo", "2120417009C");
		client.put("Currency", "CAD");
		
		response = 
		given()
			.spec(RestUtilities.createPathParams(requestSpec, client))
		.when()
			.get(EndPoints.SETTLEMENT_METHOD)
		.then()
			.spec(responseSpec)
            .log().all()
			.extract().response();
	}
	
	@Test
	public void USClientSendsPLNTest() {
		log.info("***USClientSendsPLN Test***");
		
		Map<String, String> client = new HashMap<String, String>();
		client.put("AccountNo", "2120417009C");
		client.put("Currency", "PLN");
		
		response = 
		given()
			.spec(RestUtilities.createPathParams(requestSpec, client))
		.when()
			.get(EndPoints.SETTLEMENT_METHOD)
		.then()
			.spec(responseSpec)
            .log().all()
			.extract().response();
	}
}
