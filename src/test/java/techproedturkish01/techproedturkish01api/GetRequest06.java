package techproedturkish01.techproedturkish01api;

import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetRequest06 extends TestBase{
	//TestBase class olusturup her testte kullanılan dataları bu class'a koyup 
	//tekrar tekrar aynı seyleri yazmaktan kurtulacağız.
	
	/*
 	 When I send a GET request to REST API URL
	 https://restful-bookerherokuapp.com/booking/5
	 Then
	 HTTP Status Code should be 200
	 And Response format should be "application/JSON"
	 And response body should be like;
	 {
	 	"firstname" : "Mark",
	 	"lastname" : "Ericsson",
	 	"totalprice" : 310,
	 	"depositpaid" : false,
	 	"bookingdates" : {
	 		"checkin" : "2019-11-16",
	 		"checkout" : "2020-06-19"
	 	}
	 }
	 */
	@Test
	public void get01() {
		Response response = given().
								spec(spec01).
							when().
								get("/booking/5");
		
		response.prettyPrint();
		
		response.
		then().
		assertThat().
		statusCode(200).
		contentType(ContentType.JSON).
		body("firstname", equalTo("Mark"),
			 "lastname", equalTo("Ericsson"),
			 "totalprice", equalTo(310),
			 "depositpaid", equalTo(false),
			 "bookingdates.checkin", equalTo("2019-11-16"),
			 "bookingdates.checkout", equalTo("2020-06-19"));
		
	}
	
	
	

}
