package techproedturkish01.techproedturkish01api;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostRequest03 extends TestBase {
	
							/*
							POST SCENARIO
						
						When I send a GET request to REST API URL
						https://restful-bookerherokuapp.com/booking
						Then
						HTTP Status Code should be 200
						And Response format should be "application/JSON"
						And response body should be like;
						{
						"firstname" : "Suleyman",
						"lastname" : "Alptekin",
						"totalprice" : 123,
						"depositpaid" : true,
						"bookingdates" : {
						"checkin" : "2020-05-02",
						"checkout" : "2020-05-05"
						},
						"additionalneeds": "Wifi"
						}
						*/
	
	@Test
	public void post01() {
		
		Response response = createRequestBodyByMap();
		
		response.prettyPrint();
		
		//Status code 200 olmalı
		response.
			then().
			assertThat().
			statusCode(200);
		
		//Json path kullanarak assertion yapmak
		JsonPath json = response.jsonPath();
		SoftAssert softAssert = new SoftAssert();
		
		//firstname assertion 
		softAssert.assertEquals(json.getString("booking.firstname"), requestBodyMap.get("firstname"));
		
		//lastname assertion
		softAssert.assertEquals(json.getString("booking.lastname"), requestBodyMap.get("lastname"));
		
		//totalprice assertion
		softAssert.assertEquals(json.getInt("booking.totalprice"), requestBodyMap.get("totalprice"));
		
		//depositpaid assertion
		softAssert.assertEquals(json.getBoolean("booking.depositpaid"), requestBodyMap.get("depositpaid"));
		
		//checkin assertion
		softAssert.assertEquals(json.getString("booking.bookingdates.checkin"), bookingDateMap.get("checkin"));
		
		//checkout assertion
		softAssert.assertEquals(json.getString("booking.bookingdates.checkout"), bookingDateMap.get("checkout"));
		
		//additionalneeds assertion
		softAssert.assertEquals(json.getString("booking.additionalneeds"), requestBodyMap.get("additionalneeds"));
		
		softAssert.assertAll();
	}
	
	
}
