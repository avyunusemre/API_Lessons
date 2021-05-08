package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.*;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostRequest04 extends TestBase {
	
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

	/*
 	  POJO: Plain Old Java Object
	 */
	
	@Test
	public void post01() {
		
		BookingDates bookingDates = new BookingDates("2020-05-02","2020-05-05");
		Booking booking = new Booking("Suleyman","Alptekin",123,true,bookingDates,"Wifi");
		
		Response response = given().
								contentType(ContentType.JSON).
								spec(spec01).
								auth().
								basic("admin", "password123").
								body(booking).
							when().
								post("/booking");
		
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
		softAssert.assertEquals(json.getString("booking.firstname"), booking.getFirstname());
		
		//lastname assertion
		softAssert.assertEquals(json.getString("booking.lastname"), booking.getLastname());
		
		//totalprice assertion
		softAssert.assertEquals(json.get("booking.totalprice"), booking.getTotalprice());
		
		//depositpaid assertion
		softAssert.assertEquals(json.getBoolean("booking.depositpaid"), booking.getDepositpaid());
		
		//checkin assertion
		softAssert.assertEquals(json.getString("booking.bookingdates.checkin"), booking.getBookingdates().getCheckin());
		
		//checkout assertion
		softAssert.assertEquals(json.getString("booking.bookingdates.checkout"), booking.getBookingdates().getCheckout());
		
		//additionalneeds assertion
		softAssert.assertEquals(json.getString("booking.additionalneeds"), booking.getAdditionalneeds());
		
		softAssert.assertAll();
	}
	
	

}
