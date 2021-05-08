 package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import com.google.gson.Gson;

import io.restassured.response.Response;

public class GetRequest12 extends TestBase  {

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
								get();
		response.prettyPrint();
		
		List<Map<String,String>> listOfMaps = response.as(ArrayList.class);
		
		System.out.println(listOfMaps.get(1));
		
		SoftAssert softAssert = new SoftAssert();
		//200 tane id oldugunu verify ediniz.
		softAssert.assertTrue(listOfMaps.size()==200, "id sayısı beklenen gibi degil");
		
		//121. elemanın completed degerinin true oldugunu verify ediniz
		softAssert.assertEquals(listOfMaps.get(120).get("completed"), true, "completed degeri beklenenden farklı");
		
		//sondan bir önceki elamanın title'ının "numquam repellendus a magnam" oldugunu verify ediniz
		softAssert.assertEquals(listOfMaps.get(listOfMaps.size()-2).get("title"), "numquam repellendus a magnam");
		softAssert.assertAll();
		
		//Java Object'ini Json formatına cevirme
		Gson gson = new Gson();
		String jsonFromList = gson.toJson(listOfMaps);
		System.out.println(jsonFromList);
	}

}