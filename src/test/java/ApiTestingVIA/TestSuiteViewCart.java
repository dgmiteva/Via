package ApiTestingVIA;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class TestSuiteViewCart {
	
	CommonActionsStore obj = new CommonActionsStore();
	
	//Testing login with valid token
	@Test
	public void TC01PostViewCartResponseOk200() {
		
				
		JSONObject requestParams = new JSONObject();
		requestParams.put("cookie", obj.userToken);
		requestParams.put("flag", "true");
		
		//obtain response
		String cartList = 
		given().
			header("Content-Type", "application/json; charset=utf-8").		
			body(requestParams.toJSONString()).
		when().
			post(obj.viewCartApiURL).
		then().log().all().
			statusCode(200).
			contentType("application/json").
			time(lessThan(1000L)).
			//body("Items.cookie[0]", equalTo(obj.userToken)).
			body("Items.id[0]", equalTo("20b708a6-cd4c-fd2b-be49-d72dfe2df94b")).
			body("Items.prod_id[0]", equalTo(1)).
			//body("Items.cookie[1]", equalTo(obj.userToken)).
			body("Items.id[1]", equalTo("21d57c13-f782-0119-ca41-abc29a6cac07")).
			body("Items.prod_id[1]", equalTo(2)).
			extract().path("cartList");
		
		System.out.println(cartList);
	
	}
	
	@Test
	public void TC02PostViewCartResponseError400() {
		
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("cookie", "qwerty");
		requestParams.put("flag", "true");
		
		//obtain response
		String cartList = 
		given().
			header("Content-Type", "application/json; charset=utf-8").		
			body(requestParams.toJSONString()).
		when().
			post(obj.viewCartApiURL).
		then().log().all().
			statusCode(200).
			contentType("application/json").
			time(lessThan(1000L)).
			body("errorMessage", equalTo("Bad parameter, token malformed.")).
			extract().path("cartList");
		
		System.out.println(cartList);
	
	}

}
