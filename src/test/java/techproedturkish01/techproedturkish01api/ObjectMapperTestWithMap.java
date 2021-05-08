package techproedturkish01.techproedturkish01api;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import Utilities.JsonUtil;
import io.restassured.response.Response;

public class ObjectMapperTestWithMap extends TestBase {
	
	@Test
	public void javaToJson() {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(101, "Ali");
		map.put(102, "Can");
		map.put(103, "Remziye");
		
		String jsonFromMap = JsonUtil.convertJavaToJson(map);
		
		System.out.println(jsonFromMap);
	}

	@Test
	public void jsonToJava() {
		Response response = given().
								spec(spec01).
							when().
								get("/booking/3");
		response.prettyPrint();
		
		//API^'dan gelen Json formatındaki data'yı Map'e cevirdim ==> De-Serialization
		Map<String,Object> jsonToMapApi = JsonUtil.convertJsonToJava(response.asString(), Map.class);
		System.out.println(jsonToMapApi);
		
		/*
		 1)API'dan gelen Json formatındaki data'yı Map'e cevirdim
		 2)Testcase'de bana verilen data'yi Map'e cevircem
		 3)1. adımda olusturdugum Map ile 2. adımda olusturdugum Map'deki data'ları karsılastırarak
		 	verification yapacagım
		 */
		
		Map<String, Object> jsonToMapTestCase = new HashMap<>();
		jsonToMapTestCase.put("firstname", "Eric");
		jsonToMapTestCase.put("lastname", "Smith");
		jsonToMapTestCase.put("totalprice", 931);
		jsonToMapTestCase.put("depositpaid", true);
		
		response.
				then().
				assertThat().
				statusCode(200);
		
		assertEquals(jsonToMapTestCase.get("firstname"), jsonToMapApi.get("firstname"));
		assertEquals(jsonToMapTestCase.get("lastname"), jsonToMapApi.get("lastname"));
		assertEquals(jsonToMapTestCase.get("totalprice"), jsonToMapApi.get("totalprice"));
		assertEquals(jsonToMapTestCase.get("depositpaid"), jsonToMapApi.get("depositpaid"));
		
	}
}
