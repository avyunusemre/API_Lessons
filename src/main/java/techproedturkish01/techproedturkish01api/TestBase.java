package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Before;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	protected RequestSpecification spec01;
	protected RequestSpecification spec02;
	protected RequestSpecification spec03;
	Map<String,String> bookingDateMap = new HashMap<>();
	Map<String, Object> requestBodyMap = new HashMap<>();
	JSONObject jsonBookingDatestBody = new JSONObject();
	JSONObject jsonRequestBody = new JSONObject();
	
	@Before
	public void setUp01() {
		spec01 = new RequestSpecBuilder().
							setBaseUri("https://restful-booker.herokuapp.com").
							build();
	}

	@Before
	public void setUp02() {
		spec02 = new RequestSpecBuilder().
				setBaseUri("http://dummy.restapiexample.com/api/v1/employees").
				build(); 
	}
	
	@Before
	public void setUp03() {
		spec03 = new RequestSpecBuilder().
				setBaseUri("https://jsonplaceholder.typicode.com/todos").
				build(); 
	}
	
	protected Response createRequestBodyByJsonObjectClass() {
		
		jsonBookingDatestBody.put("checkin", "2020-05-02");
		jsonBookingDatestBody.put("checkout", "2020-05-05");
		
		jsonRequestBody.put("firstname", "Suleyman");
		jsonRequestBody.put("lastname", "Alptekin");
		jsonRequestBody.put("totalprice", 123);
		jsonRequestBody.put("depositpaid", true);
		jsonRequestBody.put("bookingdates", jsonBookingDatestBody);
		jsonRequestBody.put("additionalneeds", "Wifi");
		
		Response response = given().
								contentType(ContentType.JSON).
								spec(spec01).
								auth().
								basic("admin", "password123").
								body(jsonRequestBody.toString()).
							when().
								post("/booking");
		return response;
		
	};
	
	protected Response createRequestBodyByMap() {
		
		bookingDateMap.put("checkin", "2020-05-02");
		bookingDateMap.put("checkout", "2020-05-05");
		
		requestBodyMap.put("firstname","Suleyman");
		requestBodyMap.put("lastname","Alptekin");
		requestBodyMap.put("totalprice", 123);
		requestBodyMap.put("depositpaid", true);
		requestBodyMap.put("bookingdates",bookingDateMap);
		requestBodyMap.put("additionalneeds","Wifi");
		
		Response response = given().
				contentType(ContentType.JSON).//contentType : içerik tipi, Database'e yolladığım data'nin icerik tipi JSON
				spec(spec01).auth().
				basic("admin", "password123").
				body(requestBodyMap).
				when().
				post("/booking");

		return response;
	}
	
	
}
