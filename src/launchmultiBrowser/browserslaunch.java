package launchmultiBrowser;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;

public class browserslaunch {
	
	private static final String Capabilities = null;
	private WebDriver driver;
	
	
	public static WebDriver browsersChrome() throws Exception{
		
		System.setProperty("webdriver.chrome.driver","C:\\Selenium Requirements\\Selenium\\chromedriver.exe");
		//DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		
		options.addArguments("test-type");
		
	//	capabilities.setCapability("chrome.binary", "C:\\Selenium Requirements\\Selenium\\chromedriver.exe");
	//	capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	//	Capabilities {acceptInsecureCerts: false, acceptSslCerts: false, applicationCacheEnabled: false, browserConnectionEnabled: false, browserName: chrome, chrome: {chromedriverVersion: 2.41.578737 (49da6702b16031..., userDataDir: C:\Users\ADMIN~1.PPA\AppDat...}, cssSelectorsEnabled: true, databaseEnabled: false, goog:chromeOptions: {debuggerAddress: localhost:50119}, handlesAlerts: true, hasTouchScreen: false, javascriptEnabled: true, locationContextEnabled: true, mobileEmulationEnabled: false, nativeEvents: true, networkConnectionEnabled: false, pageLoadStrategy: normal, platform: XP, platformName: XP, rotatable: false, setWindowRect: true, takesHeapSnapshot: true, takesScreenshot: true, unexpectedAlertBehaviour: , unhandledPromptBehavior: , version: 71.0.3578.98, webStorageEnabled: true};
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	
		return driver;	
	}

}
