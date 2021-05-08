package techproedturkish01.techproedturkish01api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetRequest09 extends TestBase {
	
	@Test
	public void get01() {
		
		Response response = given().
								spec(spec02).
							when().
								get();
		response.prettyPrint();
		
		//JsonPath objesi olusturun 
		JsonPath json = response.jsonPath();
		
		//Tum employee isimlerini console'a yazdırınız.
		System.out.println(json.getList("data.employee_name")); //getString() metodunu kullanmak da mumkundur.
		
		//Ikinci işçinin isminin Garret Winters oldugunu "verify" ediniz. 
		assertEquals("Name is not expected","Garrett Winters",json.getString("data[1].employee_name"));
		
		//Hard Assertion ile yaptık. Hlbuki soruda "verify" diyor. Bu yuzden soft assertion kullanmalıyız.
		
		/*
		 Soft assertion için 3 adım takip edilmelidir. : 1) SoftAssert class'indan bir obje (softAssert) olusturulur
		 											     2) Objeyi (softAssert) kullanarak assertion yap.
		 											     3) softAssert.assertAll();
		 */
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(json.getString("data[1].employee_name"), "Garrett Winters", "Name is not expected.");
		softAssert.assertAll();
		
		//Isciler arasinda Herrod Chandler'in var oldugunu "verify" ediniz.
		softAssert.assertTrue(json.getList("data.employee_name").contains("Herrod Chandler"), "Herrod Chandler yok");
		softAssert.assertAll();
		
		//24 tane isci oldugunu verify ediniz
		softAssert.assertEquals(json.getList("data.id").size(), 24, "24 isci yok");
		softAssert.assertAll();
		
		//7. iscinin maasının 137500 oldugunu verify ediniz.
		softAssert.assertEquals(json.getString("data[6].employee_salary"), "137500", "Maas istenen gibi degildir.");
	}

}
