package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import java.util.List;

public class InventoryPage {
	WebDriver driver;

	private By appLogo = By.xpath("//div[@class='app_logo']");
	private By inventoryItems = By.xpath("//div[@class='inventory_item']");
	private By priceLocator = By.xpath(".//div[@class='inventory_item_price']");
	private By addToCartButtonLocator = By.xpath(".//button[text()='Add to cart']");
	private By cartBadge = By.xpath("//span[@data-test='shopping-cart-badge']");

	public InventoryPage(WebDriver driver) {
		this.driver = driver;
	}
	
    public String getAppLogoText() {
        String logoText = driver.findElement(appLogo).getText();
        return logoText;
    }

	public void addHighestPricedItemToCart() {
		List<WebElement> items = driver.findElements(inventoryItems);
		double maxPrice = 0;
		WebElement highestItemButton = null;

		for (WebElement item : items) {
			String priceText = item.findElement(priceLocator).getText();
			double price = Double.parseDouble(priceText.replace("$", ""));
			if (price > maxPrice) {
				maxPrice = price;
				highestItemButton = item.findElement(addToCartButtonLocator);
			}
		}

		if (highestItemButton != null) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			wait.until(ExpectedConditions.elementToBeClickable(highestItemButton));
			highestItemButton.click();
			System.out.println("Highest priced item $" + maxPrice + " added to cart");
		}
	}

	public int getCartItemCount() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(cartBadge)));
		String cartItemsCount = driver.findElement(cartBadge).getText();
		return Integer.valueOf(cartItemsCount);
	}
}
