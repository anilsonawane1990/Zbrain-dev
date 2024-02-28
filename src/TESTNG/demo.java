package TESTNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

public class demo {
	WebDriver driver;

	@BeforeMethod
	public void openpage() {
		System.setProperty("Webdriver.chrome.driver", "D:\\INSTALLER FILE\\chromedriver-win64");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}
}
