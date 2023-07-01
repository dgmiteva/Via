/*The purpose of this class is to describe
 * common page elements and actions which can be reused in all test cases*/

package ApiTestingVIA;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CommonActionsStore {
	
	String baseURL = "https://api.demoblaze.com/";
	String loginApiURL = baseURL + "check";
	String addToCartApiURL = baseURL + "addtocart";
	String viewCartApiURL = baseURL + "viewcart";
	String deleteItemURL = baseURL + "deleteitem";
	String userToken = "dGVzdDE2ODg1MzU=";
	String expiredToken = "dGVzdDE2ODg1MzZ=";
	String validId = "21d57c13-f782-0119-ca41-abc29a6cac079";
	
	//Check response time in milliseconds
	public void checkResponseTime(String apiURL) {
		given().
			get(apiURL).
		then().
			time(lessThan(200L));
	}
	
	public void checkHeaders(String apiURL) {
		given().
			get(apiURL).
		then().
			contentType("application/json; charset=utf-8");
	}
	
	public void checkMissingAuthorizationHeaderGet(String apiURL) {
		given().
			get(apiURL).
		then().
			statusCode(401).
			body("error", equalTo("Missing Authorization header."));
	}

}
