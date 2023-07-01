package playwrightTestingVia;
import org.testng.annotations.Test;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

import com.microsoft.playwright.*;


public class TestSuiteAddToCart {
	
	CommonObjects obj = new CommonObjects();
	Playwright playwright = Playwright.create();
    Browser browser = playwright.chromium().launch();
    Page page = browser.newPage();
    
    @Test
    public void TC01AddToCart() {
    	
    	//login as regular user
    	obj.SignIn("test", "test");
    	
    	//click on the first available product from the landing page
    	page.locator(obj.viewFirstProduct).click();
    	assertEquals(obj.basicURL + "prod.html?idp_=1", page.url());
    	
    	//add the product to the cart
    	page.locator(obj.addToCartBtn).click();
    	assertThat(page.getByText("Product added.")).isVisible();
    	page.locator(obj.okBtn).click();
    	
    	//order the product from the view cart page
    	page.locator(obj.cartMenu).click();
    	assertEquals(obj.basicURL + "cart.html", page.url());
    	page.locator(obj.placeOrderBtn).click();
    	
    }
    


}
