package stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


import enumpack.constants;
import io.cucumber.core.options.Constants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.AddPlace;
import testdata.test_data;
import utils.utils;

public class stepdef1 {
	
	Response response;
	test_data testdata=new test_data();
	AddPlace payload; //payload refers to the AddPlace object that contains your API request data.
	utils utils = new utils();
	public static String placeId;
	String placeidfromOutput;

	@Given("Add place payload {string},{string},{string}")
	public void add_place_payload(String name, String language, String address) {
		utils.RequestSpecification();
		utils.responseSpecification();
		payload=testdata.addplace(name,language,address);	 
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String methodname) {
		
		String testConstants=constants.valueOf(resource).getresourceString();
		//System.out.println(testConstants);
		
		RequestSpecification request = given()
		        .spec(utils.req)
		        .body(payload);
		
		if (methodname.equalsIgnoreCase("POST")) {
		    response = request
		            .when()
		            	.post(testConstants)
		            .then()
		            	.spec(utils.res)
		            	.extract()
		            	.response();
		    placeId = response.jsonPath().getString("place_id");
		    System.out.println("placeId after POST = " + placeId);
		    
		} else if (methodname.equalsIgnoreCase("GET")) {
			  request.queryParam("place_id", placeId);
		    response = request
		            .when()
		            	.get(testConstants)
		            .then()
		            	.spec(utils.res)
		            	.extract()
		            	.response();
		}
		//System.out.println(response.asPrettyString());
	}
	
	@Then("response should be {int}")
	public void response_should_be(Integer int1) {
		assertEquals(int1,response.getStatusCode());
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String Key, String expvalue) {
		
		 String actualValue=response.jsonPath().getString(Key);
		 System.out.println("Key is :" + Key);
		 System.out.println("ActualValue is :" + actualValue);
		 assertEquals(expvalue, actualValue);
	 
	}
	
	@Then("{string} returned in response is {string}")
	public void returned_in_response_is(String scope, String App) {
		 String actualValue=response.jsonPath().getString(scope);
		 assertEquals(actualValue, App);
	}
	
	@Then("verify place_id creatd maps to {string} using {string}")
	public void verify_place_id_creatd_maps_to_using(String expectedname, String getapstring) {

		    String testConstants = constants.valueOf(getapstring).getresourceString();

		    response = given()
		    			.spec(utils.req)
		    			.queryParam("place_id", placeId)
		            .when()
		            	.get(testConstants)
		            .then()
		            	.spec(utils.res)
		            	.extract()
		            	.response();

		    // Extract "address" from response using common JSON utility
		    String actualAddress = utils.getJsonPath(response, "address");

		    // Verify expected address
		    assertEquals("Pune", actualAddress);
		   
		    user_calls_with_http_request(getapstring,"GET"); // we called the feature method direct
		    
		    String actualname	= utils.getJsonPath(response, "name");
		    //System.out.println("name from feature file" + expectedname);
		    //System.out.println("name from response" + actualname);
		    assertEquals(expectedname,actualname);
		    	    	    
//		    String placeidfromOutput= response.jsonPath().getString("place_id");
//		    System.out.println(placeidfromOutput);
//		    System.out.println(placeId);
			    
	    	
	}


	@Given("delete place payload")
	public void delete_place_payload() {
		
	//String testConstants = constants.valueOf(id).getresourceString();
		System.out.println("placeId before delete = " + placeId);
		 response = given()
		            .spec(utils.req)
		            .body(testdata.deletedapi(placeId))
		            .when()
		            .post(constants.deleteplaceapi.getresourceString())
		            .then()
		            .spec(utils.res)
		            .extract()
		            .response();
	
		 System.out.println("Delete Status Code: " + response.getStatusCode());
		 System.out.println("Delete Response Body: " + response.asString());
	 		
	
	}

}
