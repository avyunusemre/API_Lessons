package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetRequest08 extends TestBase{

	/*
	 When I send a GET request to REST API URL
	 https://restful-bookerherokuapp.com/booking/5
	 Then
	 HTTP Status Code should be 200
	 And Response format should be "application/JSON"
	 And response body should be like;
	 {
	 	"firstname" : "Sally",
	 	"lastname" : "Ericsson",
	 	"totalprice" : 116,
	 	"depositpaid" : true,
	 	"bookingdates" : {
	 		"checkin" : "2019-11-28",
	 		"checkout" : "2020-03-07"
	 	}
	 }
	 */
	
	@Test
	public void get01() {
		
		spec01.pathParam("bookingid", 5);
		
		Response response = given().
								spec(spec01).
							when().
								get("/booking/{bookingid}");
		
		response.prettyPrint();
		
		//JSON formatındaki dataların içinde gezmemizi kolaylaştırır.
		JsonPath json = response.jsonPath();
		
		System.out.println(json.getString("firstname"));
		assertEquals("First name is not excepted","Eric",json.getString("firstname"));
		
		System.out.println(json.getString("lastname"));
		assertEquals("Last name is not excepted","Ericsson",json.getString("lastname"));
		
		System.out.println(json.getInt("totalprice"));
		assertEquals("Total price is not expected",649,json.getInt("totalprice"));
		
		System.out.println(json.getBoolean("depositpaid"));
		assertEquals("deposit paid is not expected", true, json.getBoolean("depositpaid"));
		
		System.out.println(json.getString("bookingdates.checkin"));
		assertEquals("Check in is not expected","2016-08-29",json.getString("bookingdates.checkin"));
		
		System.out.println(json.getString("bookingdates.checkout"));
		assertEquals("Check out is not expected","2018-05-24",json.getString("bookingdates.checkout"));
	}
	
}
