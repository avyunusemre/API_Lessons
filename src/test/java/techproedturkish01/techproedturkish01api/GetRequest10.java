package techproedturkish01.techproedturkish01api;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Collections;
import java.util.List;

public class GetRequest10 extends TestBase {

	/*
	 When I send a GET request to REST API URL
	 http://dummy.restapiexample.com/api/v1/employees
	 Then
	 HTTP Status Code should be 200
	 1) 10'dan buyuk tum id'leri console'a yazdır.
	 10'dan buyuk 14 tane id oldugunu verify et
	 2)30'dan kucuk tum yasları console'a yazdır.
	 30'dan kucuk en buyuk yasın 23 oldugunu verify et
	 3)Maası 350000'den cok olan iscilerin isimlerini console'a yazdır
	 Charde Marshall'ın maasinin 350000'den buyuk oldugunu verify et
	 */
	
	@Test
	public void get01() {
		
		Response response = given().
								spec(spec02).
							when().
								get();
		response.prettyPrint();
		
		response.
			then().
			assertThat().
			statusCode(200);
		
		JsonPath json = response.jsonPath();
		SoftAssert softAssert = new SoftAssert();
		
		//10'dan buyuk tum id'leri console'a yazdır.
		List<String> idList = json.getList("data.findAll{Integer.valueOf(it.id)>10}.id");
		System.out.println(idList);
		//verify
		softAssert.assertEquals(idList.size(), 14, "Eleman sayisi istenen gibi degil");
		
		//30'dan kucuk tum yaslıları console'a yazdır
		List<String> yasList = json.getList("data.findAll{Integer.valueOf(it.employee_age)<30}.employee_age");
		System.out.println(yasList);
		//verify
		Collections.sort(yasList);
		softAssert.assertTrue(yasList.get(yasList.size()-1).equals("23"),"Yas istenen gibi degil");
		
		//3)Maası 350000'den cok olan iscilerin isimlerini console'a yazdır
		List<String> salaryList = json.getList("data.findAll{Integer.valueOf(it.employee_salary)>350000}.employee_name");
		System.out.println(salaryList);
		// Charde Marshall'ın maasinin 350000'den buyuk oldugunu verify et
		softAssert.assertTrue(salaryList.contains("Charde Marshall"));
		softAssert.assertAll();
		
	}
}
