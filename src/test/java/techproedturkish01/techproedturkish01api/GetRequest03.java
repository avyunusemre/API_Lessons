package techproedturkish01.techproedturkish01api;

import static org.junit.Assert.assertEquals;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;


public class GetRequest03 {
	
	/*
	 Positive Scenario:
	 When I send a GET request to REST API URL
	 https://restful-booker.herokuapp.com.booking/1
	 And Accept type is "application/JSON"
	 Tehn
	 HTTP Status Code should be 200
	 And Response format should be "application/JSON"
	 And first name should be "Mary"
	 And last name should be "Wilson"
	 And check in date should be "2015-02-16"
	 And check out date should be "2017-06-20"
	 */
	
	@Test
	public void get01() {
		
		Response response = given().
								accept("application/json").
							when().
								get("https://restful-booker.herokuapp.com/booking/7");
		
		response.prettyPrint();
		
		//1. Yol
		response.
		then().
		assertThat().
		statusCode(200).
		contentType("application/json").
		body("firstname", Matchers.equalTo("Jim")).
		body("lastname", Matchers.equalTo("Jones")).
		body("totalprice", Matchers.equalTo(417)).
		body("depositpaid", Matchers.equalTo(false)).
		body("bookingdates.checkin", Matchers.equalTo("2017-12-30")).
		body("bookingdates.checkout", Matchers.equalTo("2019-07-13")).
		body("additionalneeds", Matchers.equalTo(null));
		
		
		//tekrarlı body kullanmadan
		response.
		then().
		assertThat().
		statusCode(200).
		contentType("application/json").
		body("firstname", Matchers.equalTo("Jim"),
				"lastname", Matchers.equalTo("Jones"),
				"totalprice", Matchers.equalTo(417),
				"depositpaid", Matchers.equalTo(false),
				"bookingdates.checkin", Matchers.equalTo("2017-12-30"),
				"bookingdates.checkout", Matchers.equalTo("2019-07-13"),
				"additionalneeds", Matchers.equalTo(null));
		
		
		
		//2. Yol 
		assertEquals(200, response.getStatusCode());
		
	}

}
