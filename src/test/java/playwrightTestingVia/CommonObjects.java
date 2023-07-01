/*The purpose of this class is to describe
 * common page elements and actions which can be reused in all test cases*/

package playwrightTestingVia;

import static org.testng.Assert.assertEquals;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class CommonObjects {
	
	//define basic locators
	String basicURL = "https://www.demoblaze.com";
	String signInBtn = "login2";
	String username = "loginusername";
	String password = "loginpassword";
	String loginBtn = "Log in";
	String viewFirstProduct = ".cardtitle a";
	String addToCartBtn = "Add to cart";
	String okBtn = "OK";
	String cartMenu = "cartur";
	String placeOrderBtn = "Place Order";
	
	Playwright playwright = Playwright.create();
    Browser browser = playwright.chromium().launch();
    Page page = browser.newPage();
	
    //define common sign in method
	public void SignIn (String user, String pass) {
		
	      page.navigate(basicURL);
	      page.locator(signInBtn).click();
	      page.locator(username).fill("test");
	      page.locator(password).fill("test");
	      page.locator(loginBtn).click();
		
	}

}
