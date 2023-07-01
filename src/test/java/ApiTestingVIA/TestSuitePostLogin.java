package ApiTestingVIA;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;


public class TestSuitePostLogin {
	
	CommonActionsStore obj = new CommonActionsStore();
	
	//Testing login with valid token
	@Test
	public void TC01PostLoginResponseOk200() {
		
				
		JSONObject requestParams = new JSONObject();
		requestParams.put("token", obj.userToken);
		
		String login = 
		given().
			header("Content-Type", "application/json; charset=utf-8").		
			body(requestParams.toJSONString()).
		when().
			post(obj.loginApiURL).
		then().log().all().
			statusCode(200).
			contentType("application/json").
			time(lessThan(1000L)).
			body("Item.expiration", not(hasValue(nullValue()))).
			body("Item.token", equalTo(obj.userToken)).
			body("Item.username", equalTo("test")).
			extract().path("login");
		
		System.out.println(login);
	
	}
	
	
	//Testing login with invalid token
	@Test
	public void TC02PostLoginResponseError400() {
		
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("token", "qwerty");
		
		String login = 
		given().
			header("Content-Type", "application/json; charset=utf-8").		
			body(requestParams.toJSONString()).
		when().
			post(obj.loginApiURL).
		then().log().all().
			statusCode(200).
			contentType("application/json").
			time(lessThan(1000L)).
			body("errorMessage", equalTo("Bad parameter, token malformed.")).
			extract().path("login");
		
		System.out.println(login);
	
	}
	
	//Testing login with expired token
	@Test
	public void TC03PostLoginResponseError400() {
		
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("token", obj.expiredToken);
		
		String login = 
		given().
			header("Content-Type", "application/json; charset=utf-8").		
			body(requestParams.toJSONString()).
		when().
			post(obj.loginApiURL).
		then().log().all().
			statusCode(400).
			contentType("application/json").
			time(lessThan(1000L)).
			body("errorMessage", equalTo("Token is expired")).
			extract().path("login");
		
		System.out.println(login);
	
	}

}
