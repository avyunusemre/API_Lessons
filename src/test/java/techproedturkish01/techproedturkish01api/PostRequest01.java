package techproedturkish01.techproedturkish01api;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class PostRequest01 extends TestBase{
	/*
	 Post Request olusturmak icin gerekenler:
	 	  1) EndPoint sart
	 	  2) Request Body sart
	 	  3) Authorization
	 	  4) Accept Type isteğe bağlıdır. Bazen kullanılır, bazen kullanılmaz
	 	  5) Content Type isteğe bağlıdır.
	 	  
	 GET ile POST Request'lerin farkları nedir?
	 1)GET Request olusturmak icin sadece Endpoint yeterlidir ama 
	 Get request'i data cekmek icin kullanırız. SQL'deki SELECT keyword'ü gibi
	 2)GET data cekmek icin, POST yeni data olusturmak icin kullanılır.
	 
	 NOTE: API Developer'lar olusturuacak data'nın bazı bölumlerinin bos bırakılmamasına karar vermişlerse
	 	   POST Request olusturuluken KESINLIKLE o kısımlara deger verilerek POST Request olusturulmalıdır. Eger buna
	 	   dikkat etmezseniz '400 Bad Request' status code alırsınız.
	 	   
	 NOTE: API Developer'lar olusturuacak data'nın bazı bölumlerinin tekrarlı olmamasına karar vermişlerse
	 	   POST Request olusturuluken KESINLIKLE o kısımlara tekrarlı degerler verilmemelidir.. Eger buna
	 	   dikkat etmezseniz '400 Bad Request' status code alırsınız.
	 	  
	 */
	
	/*
	 				POST SCENARIO
	 Accept type should be JSON
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
	
	//1. Way : Iyi degil
	@Test
	public void post01() {
		
		String jsonRequestBody = "{\r\n" + 
								"\"firstname\" : \"Suleyman\",\r\n" + 
								"\"lastname\" : \"Alptekin\",\r\n" + 
								"\"totalprice\" : 123,\r\n" + 
								"\"depositpaid\" : true,\r\n" + 
								"\"bookingdates\" : {\r\n" + 
								"\"checkin\" : \"2020-05-02\",\r\n" + 
								"\"checkout\" : \"2020-05-05\"\r\n" + 
								"},\r\n" + 
								"\"additionalneeds\": \"Wifi\"\r\n" + 
								"	 }";
		
		Response response = given().
								contentType(ContentType.JSON).//contentType : içerik tipi, Database'e yolladığım data'nin icerik tipi JSON
								spec(spec01).auth().
								basic("admin", "password123").
								body(jsonRequestBody).
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
		softAssert.assertEquals(json.getString("booking.firstname"), "Suleyman");
		
		//lastname assertion
		softAssert.assertEquals(json.getString("booking.lastname"), "Alptekin");
		
		//totalprice assertion
		softAssert.assertEquals(json.getInt("booking.totalprice"), 123);
		
		//depositpaid assertion
		softAssert.assertEquals(json.getBoolean("booking.depositpaid"), true);
		
		//checkin assertion
		softAssert.assertEquals(json.getString("booking.bookingdates.checkin"), "2020-05-02");
		
		//checkout assertion
		softAssert.assertEquals(json.getString("booking.bookingdates.checkout"), "2020-05-05");
		
		//additionalneeds assertion
		softAssert.assertEquals(json.getString("booking.additionalneeds"), "Wifi");
		
		softAssert.assertAll();
	}
	
}
