package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;


import utils.BrowserSetUp;

public class Hooks {

	BrowserSetUp browserSetUp = new BrowserSetUp();

	@Before
	public void enviromentSetUp() throws Exception {
		browserSetUp.setUp();
	}

	@After
	public void tearDown() {
		browserSetUp.quitBrowser();
	}

}
