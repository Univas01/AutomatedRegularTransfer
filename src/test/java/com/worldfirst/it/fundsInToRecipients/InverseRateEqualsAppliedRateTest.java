package com.worldfirst.it.fundsInToRecipients;

import static io.restassured.RestAssured.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.worldfirst.it.common.PayloadConverter;
import com.worldfirst.it.common.RestUtilities;
import com.worldfirst.it.constants.EndPoints;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class InverseRateEqualsAppliedRateTest {
	
	private static Logger log = LogManager.getLogger(InverseRateEqualsAppliedRateTest.class.getName());
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	Response quoteResponse;
	String quoteId;

	@BeforeMethod
	public void setUp() {
		requestSpec = RestUtilities.postRequestSpecification();
		responseSpec = RestUtilities.postResponseSpecification();
		
	}
	
	@Test
	public void InverseRateEqualsAppliedRate() {
		log.info("***InverseRateEqualsRate Test***");
		String requestBody = PayloadConverter.generatePayloadString("GLPClientUSDEUR.json");
		quoteResponse = 
		given()
			.spec(RestUtilities.addPayloadToSpec(requestSpec, requestBody))
		.when()
			.post(EndPoints.QUOTE)
		.then()
			.spec(responseSpec)
			.extract().response();	
		String getQuoteresponse = RestUtilities.getQuoteResponse(quoteResponse);
		JsonPath quoteJsonPath = RestUtilities.jsonParser(getQuoteresponse);
		log.info(getQuoteresponse);
		quoteId = quoteJsonPath.get("id");
		Assert.assertEquals(quoteJsonPath.get("deal.appliedRate"), quoteJsonPath.get("deal.inverseRate"));
	}
}
