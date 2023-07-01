package playwrightTestingVia;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import com.microsoft.playwright.*;


public class TestSuiteLogin {
	
	CommonObjects obj = new CommonObjects();
	Playwright playwright = Playwright.create();
    Browser browser = playwright.chromium().launch();
    Page page = browser.newPage();

    
    @Test
    public void TC01SignInValidUSer() {
    	obj.SignIn("test", "test");
    	assertEquals(obj.basicURL + "index.html", page.url());
    	assertThat(page.getByText("Welcome test")).isVisible();
    }
    
    @Test
    public void TC02SignInWithoutUserAndPass() {
    	obj.SignIn("", "");
    	assertThat(page.getByText("Please fill out Username and Password.")).isVisible();
    }
    
    @Test
    public void TC03SignInWithoutUser() {
    	obj.SignIn("", "test");
    	assertThat(page.getByText("Please fill out Username and Password.")).isVisible();
    }
	
    @Test
    public void TC04SignInWithoutPass() {
    	obj.SignIn("test", "");
    	assertThat(page.getByText("Please fill out Username and Password.")).isVisible();
    }
    
    @Test
    public void TC05SignInWithInvalidUser() {
    	obj.SignIn("qwerty", "test");
    	assertThat(page.getByText("Username or Password are incorrect.")).isVisible();
    }
	
    @Test
    public void TC06SignInWithInvalidPass() {
    	obj.SignIn("test", "qwerty");
    	assertThat(page.getByText("Username or Password are incorrect.")).isVisible();
    }
}
