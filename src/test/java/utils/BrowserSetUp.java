package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserSetUp {

	public static WebDriver driver;
	public static ConfigReader configReader;
	static Properties pro;

	public void setUp() throws Exception {

		// Disable Chrome's built-in password manager and credential saving prompts.
		// This prevents pop-up dialogs or banners asking to save passwords while
		// running automated test
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_leak_detection", false);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		configReader = new ConfigReader();
		pro = configReader.init_properties();
		String browsername = pro.getProperty("Browser");
		switch (browsername) {
		case "Chrome":
			driver = new ChromeDriver(options);
			break;

		case "Firefox":
			driver = new FirefoxDriver();
			break;

		default:
			throw new IllegalArgumentException("Browser not supported: " + browsername);
		}

		driver.manage().window().maximize();
	}

	public void quitBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public String getApplicationUrl() throws Exception {
		configReader = new ConfigReader();
		pro = configReader.init_properties();
		String url = pro.getProperty("base.url");
		return url;
	}

}
