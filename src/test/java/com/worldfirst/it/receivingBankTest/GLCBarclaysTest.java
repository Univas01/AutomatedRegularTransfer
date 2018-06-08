package com.worldfirst.it.receivingBankTest;

import static io.restassured.RestAssured.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.worldfirst.it.common.PayloadConverter;
import com.worldfirst.it.common.RestUtilities;
import com.worldfirst.it.constants.EndPoints;
import com.worldfirst.it.constants.PathForTransfer;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GLCBarclaysTest {
	
	/** Return BarclaysBank for GL Corporate Client that buys EUR with USD.
	 *  Get the dealId from this test and query database as follows;
	 *  Select recBank from bo_deals where id = <dealId>
	 */ 
	
	private static Logger log = LogManager.getLogger(GLCBarclaysTest.class.getName());
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	Response quoteResponse, bookResponse;
	String quoteId;

	@BeforeMethod
	public void setUp() {
		requestSpec = RestUtilities.postRequestSpecification();
		responseSpec = RestUtilities.postResponseSpecification();
	}
	
	@Test
	public void quoteGLCBarclaysTest() {
		log.info("***Quote Test***");
		String requestBody = PayloadConverter.generatePayloadStringF2R("USCClientSendsGBPBuyEUR.json");
		quoteResponse = 
		given()
			.spec(RestUtilities.addPayloadToSpec(requestSpec, requestBody))
		.when()
			.post(EndPoints.QUOTE)
		.then()
			.spec(responseSpec)
			.log().ifError()
			.extract().response();	
		String getQuoteresponse = RestUtilities.getQuoteResponse(quoteResponse);
		JsonPath quoteJsonPath = RestUtilities.jsonParser(getQuoteresponse);
		log.info(getQuoteresponse);
		quoteId = quoteJsonPath.get("id");
	}
	
	@Test (enabled = true, dependsOnMethods = {"quoteGLCBarclaysTest"})
	public void bookGLCBarclaysTest() {
		log.info("***Book Test***");
		bookResponse = 
		given()
			.spec(RestUtilities.createPathParam(requestSpec, "quoteId", quoteId))
			.basePath(PathForTransfer.TRANSFER)
		.when()
			.post(EndPoints.BOOK)
		.then()
			.spec(responseSpec)
			.log().ifError()
			.extract().response();
		String getBookresponse = RestUtilities.getQuoteResponse(bookResponse);
		log.info(getBookresponse);
	}
}
