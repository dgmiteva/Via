package ApiTestingVIA;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class TestSuiteDeleteItem {
	
	CommonActionsStore obj = new CommonActionsStore();
	
	//Add a product to the cart
	@Test
	public void TC01PostAddToCartResponseOk200() {
				
		JSONObject requestParams = new JSONObject();
		requestParams.put("cookie", "dGVzdDE2ODg1MzU=");
		requestParams.put("flag", true);
		requestParams.put("id", "20b708a6-cd4c-fd2b-be49-d72dfe2df94b");
		requestParams.put("prod_id", 1);

		given().
			header("Content-Type", "application/json").		
			body(requestParams.toJSONString()).
		when().
			post(obj.addToCartApiURL);

	}
	
	//
	@Test
	public void TC03PostViewCartResponseOk200() {
		
				
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
			extract().path("cartList");
		
		System.out.println(cartList);
	
	}

	
	//Delete the item added at TC01 and check response
	@Test
	public void TC04PostDeleteItemCartResponseOk200() {
		
				
		JSONObject requestParams = new JSONObject();
		requestParams.put("id", "20b708a6-cd4c-fd2b-be49-d72dfe2df94b");
		given().
			header("Content-Type", "application/json").		
			body(requestParams.toJSONString()).
		when().
			post(obj.deleteItemURL).
		then().log().all().
			statusCode(200).
			body("errorMessage", equalTo("Item deleted.")).
			time(lessThan(1000L));
	}
	
	//Check the cart list for the deleted item
	@Test
	public void TC05PostViewCartResponseOk200() {
		
				
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
			extract().path("cartList");
		
		System.out.println(cartList);
	
	}}
	

		