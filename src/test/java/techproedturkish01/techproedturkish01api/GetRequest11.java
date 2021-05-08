package techproedturkish01.techproedturkish01api;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import com.google.gson.Gson;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class GetRequest11 extends TestBase{
	
	/*
	 GSON : GSON, 1) Json formatındaki data'ları Java Objectlerine donusturur. (De-Serialization)
	 			  2) Java Objectler'ini Json formatındaki data'lara cevirir.   (Serialization)
	 			  
	 			  NOT: GSON ile aynı işi yapan ObjectMapper Class'da var.
	 */
	
	@Test
	public void get01() {
		
		Response response = given().
								spec(spec03).
							when().
								get("/2");
		response.prettyPrint();
		
		HashMap<String,String> map = response.as(HashMap.class);//De-Serialization
		
		System.out.println(map);
		
		System.out.println(map.keySet()); // [id, completed, title, userId]
		System.out.println(map.values()); // [2.0, false, quis ut nam facilis et officia qui, 1.0]
		
		SoftAssert softAssert = new SoftAssert();
		//completed key'inin degerini false oldugunu verfiy ediniz.
		softAssert.assertEquals(map.get("completed"), false, "false olmaliydi ama degil");
		
		//userId, id ve title degerlerini verify ediniz.
		softAssert.assertEquals(map.get("userId"), 1);
		
		//id verify
		softAssert.assertEquals(map.get("id"), 2);
		
		//title verify
		softAssert.assertEquals(map.get("title"), "quis ut nam facilis et officia qui");
		softAssert.assertAll();
		  
		//Java Objecti Json'a cevirme
		Gson gson = new Gson();
		String jsonFromMap = gson.toJson(map);
		System.out.println(jsonFromMap);
	}
	
}
