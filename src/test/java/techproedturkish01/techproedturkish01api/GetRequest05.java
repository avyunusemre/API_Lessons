package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetRequest05 {
	
	/*
	 Positive Scenario:
	 When I send a GET request to REST API URL
	 https://restful-bookerherokuapp.com/booking/5
	 And Accept type is "application/JSON"
	 Then
	 HTTP Status Code should be 200
	 And Response format should be "application/JSON"
	 And "firstname" should be "Jim"
	 And "totalprice" should be 809
	 And check in date should be "2015-06-12"
	
	 */

	@Test
	public void get01() {
		
		Response response = given().
							when().
								get("https://restful-booker.herokuapp.com/booking/5");
		
		response.prettyPrint();
		
		response.
		then().
		assertThat().
		statusCode(200).
		contentType(ContentType.JSON).
		body("firstname", Matchers.equalTo("Jim"),
				"totalprice", Matchers.equalTo(605),
				"bookingdates.checkin", Matchers.equalTo("2015-10-06"));
	}
}
