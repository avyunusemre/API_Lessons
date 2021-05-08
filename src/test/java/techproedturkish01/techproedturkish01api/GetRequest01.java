package techproedturkish01.techproedturkish01api;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetRequest01 {
	
//Rest-Assured kullanarak API Testing yapacağız
	
	@Test
	public void getMethod01() {
		
		given().
		when().
			get("https://restful-booker.herokuapp.com/booking").
		then().
			assertThat().
			statusCode(200);
	}
	
	//GET ile aldığım data'yı console'da görmek istiyorum.
	@Test
	public void getMethod02() {
		Response response = given().
							when().
								get("https://restful-booker.herokuapp.com/booking/3");
		
		response.prettyPrint();
		
		//statuscode'u console'da görmek istiyorum
		System.out.println("Status code : " + response.getStatusCode());
		
		//statusline'ı console'de gormek icin
		System.out.println("Status line : " + response.getStatusLine());
		
		//Response body'deki data'nın content(icerik) type
		System.out.println("Content type : " + response.getContentType());
		
		 //Headers'daki bilgileri almak
		System.out.println(response.getHeaders());
		
		//Headers'tan spesific bir bilgi almak
		System.out.println(response.getHeader("Date"));
		
		//Assertion yapalim
		
		//1)Status code 200
		//assertThat() kullanırsanız "Hard Assertion" yapıyorsunuz demektir.
		//Yani ilk hatada code execution durur ve hata raporu verilir. 
		//Ilk hatadan sonraki kodlar çalıştırılmaz. 
		response.
		then().
		assertThat().
		contentType("application/json; charset=utf-8").
		statusCode(200);
	}
	
	
}
