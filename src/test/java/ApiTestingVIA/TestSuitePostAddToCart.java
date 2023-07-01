package ApiTestingVIA;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class TestSuitePostAddToCart {
	
	CommonActionsStore obj = new CommonActionsStore();	
		
		//Testing adding valid id to the cart
		@Test
		public void TC01PostAddToCartResponseOk200() {
			
					
			JSONObject requestParams = new JSONObject();
			requestParams.put("cookie", obj.userToken);
			requestParams.put("flag", true);
			requestParams.put("id", obj.validId);
			requestParams.put("prod_id", 1);

			given().
				header("Content-Type", "application/json").		
				body(requestParams.toJSONString()).
			when().
				post(obj.addToCartApiURL).
			then().log().all().
				statusCode(200).
				time(lessThan(1000L));
		}
		
		//Testing adding the same valid id to the cart
		@Test
		public void TC02PostAddToCartResponseOk200() {
			
					
			JSONObject requestParams = new JSONObject();
			requestParams.put("cookie", obj.userToken);
			requestParams.put("flag", true);
			requestParams.put("id", obj.validId);
			requestParams.put("prod_id", 1);

			given().
				header("Content-Type", "application/json").		
				body(requestParams.toJSONString()).
			when().
				post(obj.addToCartApiURL).
			then().log().all().
				statusCode(200).
				time(lessThan(1000L));
		}
		
		//Testing adding an item with non-existing user
		@Test
		public void TC03PostAddToCartResponseError400() {
			
					
			JSONObject requestParams = new JSONObject();
			requestParams.put("cookie", "asdfg");
			requestParams.put("flag", true);
			requestParams.put("id", obj.validId);
			requestParams.put("prod_id", 1);

			given().
				header("Content-Type", "application/json").		
				body(requestParams.toJSONString()).
			when().
				post(obj.addToCartApiURL).
			then().log().all().
				statusCode(400).
				time(lessThan(1000L));
		}
		
		//Testing adding an item with non product item id
		@Test
		public void TC04PostAddToCartResponseError400() {
			
					
			JSONObject requestParams = new JSONObject();
			requestParams.put("cookie", obj.userToken);
			requestParams.put("flag", true);
			requestParams.put("id", "assdfgh");
			requestParams.put("prod_id", 1);

			given().
				header("Content-Type", "application/json").		
				body(requestParams.toJSONString()).
			when().
				post(obj.addToCartApiURL).
			then().log().all().
				statusCode(400).
				time(lessThan(1000L));
		}
		
		//Testing adding an item without a cookie parameter
		@Test
		public void TC05PostAddToCartNoCookieError400() {
			
					
			JSONObject requestParams = new JSONObject();
			requestParams.put("flag", true);
			requestParams.put("id", obj.validId);
			requestParams.put("prod_id", 1);

			given().
				header("Content-Type", "application/json").		
				body(requestParams.toJSONString()).
			when().
				post(obj.addToCartApiURL).
			then().log().all().
				statusCode(400).
				body("errorMessage", equalTo("Bad parameter, token malformed.")).
				time(lessThan(1000L));
		}
		
		//Testing adding an item without a flag parameter
		@Test
		public void TC06PostAddToCartNoFlagError400() {
			
					
			JSONObject requestParams = new JSONObject();
			requestParams.put("cookie", obj.userToken);
			requestParams.put("id", obj.validId);
			requestParams.put("prod_id", 1);

			given().
				header("Content-Type", "application/json").		
				body(requestParams.toJSONString()).
			when().
				post(obj.addToCartApiURL).
			then().log().all().
				statusCode(400).
				body("errorMessage", equalTo("Bad parameter, flag is incorrect.")).
				time(lessThan(1000L));
		}
		
		//Testing adding an item without product item id parameter
		@Test
		public void TC07PostAddToCartNoItemIdError400() {
			
					
			JSONObject requestParams = new JSONObject();
			requestParams.put("cookie", obj.userToken);
			requestParams.put("flag", true);
			requestParams.put("id", obj.validId);;

			given().
				header("Content-Type", "application/json").		
				body(requestParams.toJSONString()).
			when().
				post(obj.addToCartApiURL).
			then().log().all().
				statusCode(400).
				body("errorMessage", equalTo("Bad parameter, token malformed.")).
				time(lessThan(1000L));
		}
		
		//Testing adding an item without product id parameter
		@Test
		public void TC07PostAddToCartNoProdIdError400() {
			
					
			JSONObject requestParams = new JSONObject();
			requestParams.put("cookie", obj.userToken);
			requestParams.put("flag", true);
			requestParams.put("prod_id", 999);

			given().
				header("Content-Type", "application/json").		
				body(requestParams.toJSONString()).
			when().
				post(obj.addToCartApiURL).
			then().log().all().
				statusCode(400).
				body("errorMessage", equalTo("Bad parameter, token malformed.")).
				time(lessThan(1000L));
		}



}
