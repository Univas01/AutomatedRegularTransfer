package com.worldfirst.it.fundsInToRecipients;

import static io.restassured.RestAssured.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.worldfirst.it.common.PayloadConverter;
import com.worldfirst.it.common.RestUtilities;
import com.worldfirst.it.constants.EndPoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class QuoteAndBookTest {
	
	private static Logger log = LogManager.getLogger(QuoteAndBookTest.class.getName());
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	Response response;
	String Id;

	@BeforeMethod
	public void setUp() {
		requestSpec = RestUtilities.getRequestSpecification();
		responseSpec = RestUtilities.getResponseSpecification();
		
	}
	
	@Test
	public void requestAQuoteTest() {
		log.info("***Quote Test***");
		String requestBody = PayloadConverter.generatePayloadString("GLPClientEURUSD.json");
		response = 
		given()
			.spec(RestUtilities.addPayloadToSpec(requestSpec, requestBody))
		.when()
			.post(EndPoints.QUOTE)
		.then()
			.spec(responseSpec)
			.log().ifError()
			.extract().response();	
		JsonPath quoteJsonPath = RestUtilities.getJsonPath(response);
		Id = quoteJsonPath.get("id");
	}
	
	@Test (enabled = true, dependsOnMethods = {"requestAQuoteTest"})
	public void bookAQuoteTest() {
		log.info("***Booking Test***");
		response = 
		given()
			.spec(RestUtilities.createPathParam(requestSpec, "quoteId", Id))
		.when()
			.post(EndPoints.TRANSFER)
		.then()
			.spec(responseSpec)
			.log().ifError()
			.extract().response();	
	}
}
