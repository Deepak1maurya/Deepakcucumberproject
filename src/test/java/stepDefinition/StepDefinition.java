package stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import PageObjectModel.AddNewCustomer;
import PageObjectModel.LoginPage;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org. junit.Assert;

public class StepDefinition {
	WebDriver driver;
	public 	 LoginPage  lpage;
	public  Logger log;
	public AddNewCustomer addNewCustPg;

	public String generateEmailId()
	{
		return(RandomStringUtils.randomAlphabetic(5));
	}

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {

		WebDriverManager.chromedriver().setup();
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");
		driver= new ChromeDriver(ops);

		driver.manage().window().maximize();
		lpage= new LoginPage(driver);
		log = LogManager.getLogger("StepDef");
		addNewCustPg = new AddNewCustomer(driver);
		log.info("browser lunch");
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {

		driver.get(url);
		log.info("open url");
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String emailid, String pwd) {

		lpage.enterEmail(emailid);
		lpage.enterPassword(pwd);
		log.info("enter email and password");


	}

	@When("Click on Login")
	public void click_on_login() {

		lpage.clickOnLoginButton();
		log.info("click on login button");

	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String exptedTitle) {
		String actualTitle = driver.getTitle();
		if(actualTitle.equals(exptedTitle)) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);

		}
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() {

		lpage.clickOnLogOutButton();
	}

	@Then("close browser")
	public void close_browser() throws InterruptedException {

		Thread.sleep(2000);
		driver.quit();

	}
	///////////////////////////////////////// add new customer////////////////////
	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {

		String actualTitle= driver.getTitle();
		String expectedTitle = "Dashboard / nopCommerce administration";

		if(actualTitle.equals(expectedTitle))
		{
			log.info("user can view dashboard test passed.");
			Assert.assertTrue(true);

		}
		else
		{
			Assert.assertTrue(false);
			log.warn("user can view dashboard test failed.");

		}

	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() {
		addNewCustPg.clickOnCustomersMenu();
		log.info("customer menu clicked");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() {

		addNewCustPg.clickOnCustomersMenuItem();
		log.info("customer menu item clicked");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("click on Add new button")
	public void click_on_add_new_button() {
		addNewCustPg.clickOnAddnew();
		log.info("clicked on add new button.");

	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		String actualTitle = driver.getTitle();
		String expectedTitle = "Add a new customer / nopCommerce administration";

		if(actualTitle.equals(expectedTitle))
		{
			log.info("User can view Add new customer page- passed");

			Assert.assertTrue(true);//pass
		}
		else
		{
			log.info("User can view Add new customer page- failed");

			Assert.assertTrue(false);//fail
		}

	}

	@When("User enter customer info")
	public void user_enter_customer_info() {
		//addNewCustPg.enterEmail("cs129@gmail.com");
		addNewCustPg.enterEmail(generateEmailId() + "@gmail.com");
		addNewCustPg.enterPassword("ram@1008");
		addNewCustPg.enterFirstName("Deepak");
		addNewCustPg.enterLastName("Maurya");
		addNewCustPg.enterGender("mail");
		addNewCustPg.enterDob("12/15/1995");
		addNewCustPg.enterCompanyName("Finemake");
		addNewCustPg.enterAdminContent("Admin content");
		addNewCustPg.enterManagerOfVendor("Vendor 1");

		log.info("customer information entered");

	}

	@When("click on Save button")
	public void click_on_save_button() {

		addNewCustPg.clickOnSave();
		log.info("clicked on save button");

	}


	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String exptectedConfirmationMsg) {

		String bodyTagText = driver.findElement(By.tagName("Body")).getText();
		if(bodyTagText.contains(exptectedConfirmationMsg))
		{
			Assert.assertTrue(true);//pass
			log.info("User can view confirmation message - passed");

		}
		else
		{
			log.warn("User can view confirmation message - failed");

			Assert.assertTrue(false);//fail

		}


	}
	@AfterStep
	public void addScreenShotOfFailedTestCase(Scenario scenario) throws IOException {

		{
			if(scenario.isFailed()) {
				final byte[] screenshot = ((TakesScreenshot)driver) .getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png",scenario.getName()); 
			}   


		}
	}
}



