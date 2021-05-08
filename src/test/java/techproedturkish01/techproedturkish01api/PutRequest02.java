package techproedturkish01.techproedturkish01api;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

public class PutRequest02 extends TestBase {
	
	
	@Test
	public void put01() {
		
		Response response = given().
							spec(spec03).
						when().
							get("/112");
		
		response.prettyPrint();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("title", "Suleyman");
		jsonObject.put("userId", 77);
		jsonObject.put("completed", true);
		
		Response responseAfterPut = given().
										contentType(ContentType.JSON).
										spec(spec03).
										body(jsonObject.toString()).
									when().
										put("/200");
		
		responseAfterPut.prettyPrint();
		
		responseAfterPut.
					then().
					assertThat().
					statusCode(200);
		
		JsonPath json = responseAfterPut.jsonPath();
		SoftAssert softAssert = new SoftAssert();
		
		//completed degerini verify ediniz.
		softAssert.assertEquals(json.getBoolean("completed"), jsonObject.get("completed"));
		
		//title degerini verify ediniz
		softAssert.assertEquals(json.getString("title"), jsonObject.get("title"));
		
		//userId degerini verify ediniz
		softAssert.assertEquals(json.getInt("userId"), jsonObject.get("userId"));
		
		softAssert.assertAll();
		
	
	}

}
