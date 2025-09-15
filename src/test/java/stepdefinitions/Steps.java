package stepdefinitions;

import io.cucumber.java.en.*;
import utils.BrowserSetUp;
import utils.ConfigReader;
import pages.InventoryPage;
import pages.LoginPage;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.Properties;

public class Steps {
	public static ConfigReader configReader;
	static Properties pro;
	private LoginPage loginPage;
	private InventoryPage inventoryPage;

	BrowserSetUp setUp = new BrowserSetUp();

	@Given("I am on the SauceDemo login page")
	public void i_am_on_the_sauce_demo_login_page() throws Exception {
		loginPage = new LoginPage(setUp.getDriver());
		try {
			loginPage.open(setUp.getApplicationUrl());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@When("I login with username {string} and password {string}")
	public void i_login_with_username_and_password(String username, String password) throws FileNotFoundException {
		configReader = new ConfigReader();
		pro = configReader.init_properties();
		
		loginPage.login(pro.getProperty("username"),pro.getProperty("password"));
		inventoryPage = new InventoryPage(setUp.getDriver());
		String logoText = inventoryPage.getAppLogoText();
		assertTrue("Logo text does not match! :" + logoText, logoText.equals("Swag Labs"));
	}

	@When("I add the highest priced item to the cart")
	public void i_add_the_highest_priced_item_to_the_cart() {
		inventoryPage = new InventoryPage(setUp.getDriver());
		inventoryPage.addHighestPricedItemToCart();
	}

	@Then("I should see the item added to the cart")
	public void i_should_see_the_item_added_to_the_cart() {
		assertTrue("Expected cart count to be 1 but was " + inventoryPage.getCartItemCount(),
				inventoryPage.getCartItemCount() == 1);

	}

}
