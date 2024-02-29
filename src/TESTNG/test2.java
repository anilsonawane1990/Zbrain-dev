package TESTNG;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import net.bytebuddy.utility.RandomString;

public class test2 {
	WebDriver driver;

	@SuppressWarnings("deprecation")
//	@Parameters("browser")
	@BeforeMethod
	public void openbrowser() {

		System.setProperty("WebDriver.chrome.driver", "D:\\INSTALLER FILE\\chromedriver-win64");
		driver = new ChromeDriver();

//		if (browser.equalsIgnoreCase("chrome")) {
//			System.setProperty("WebDriver.chrome.driver", "D:\\INSTALLER FILE\\chromedriver-win64");
//			driver = new ChromeDriver();
//		} else if (browser.equalsIgnoreCase("Edge")) {
//			System.setProperty("WebDriver.edge.driver", "D:\\INSTALLER FILE\\edgedriver_win64");
//			driver = new EdgeDriver();
//		}
		driver.manage().window().maximize();
		driver.get("https://demo.automationtesting.in/Register.html");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void page() throws IOException {
		driver.findElement(By.xpath("//a[normalize-space()='WebTable']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();
		WebElement ele = driver.findElement(By.xpath("//label[normalize-space()='Password']"));
		WebElement ele1 = driver.findElement(By.xpath("//input[@id='firstpassword']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
		js.executeScript("arguments[0].value='" + "anil" + "';", ele1);

		WebElement list = driver.findElement(By.xpath("//select[@id='Skills']"));
		Select s = new Select(list);
		List<WebElement> skill = s.getOptions();
		System.out.println(skill.size());

		for (int i = 0; i < skill.size(); i++) {
			String data = skill.get(i).getText();
			System.out.println(data);
			if (skill.get(i).getText().equals("Troubleshooting")) {
				skill.get(i).click();
			}
		}
		WebElement isselect = driver.findElement(By.xpath("//input[@id='checkbox3']"));
		System.out.println(isselect.isSelected());

		WebElement img = driver.findElement(By.xpath("//img[@id='imagetrgt']"));

		String n = "ss";
		String n1 = RandomString.make(2);
		File source = ((TakesScreenshot) img).getScreenshotAs(OutputType.FILE);
		File dest = new File("C:\\Users\\DELL\\eclipse-workspace\\WB\\ss\\" + n + " " + n1 + ".jpg");
		FileHandler.copy(source, dest);

//		List<WebElement> link = driver.findElements(By.tagName("a"));
//		System.out.println(link.size());
//		for (WebElement links : link) {
//			System.out.println(links.getAttribute("href"));
//		}

		List<WebElement> link1 = driver.findElements(By.tagName("a"));
		System.out.println(link1.size());
		int brklink = 0;
		for (WebElement links1 : link1) {
			String data = links1.getAttribute("href");
			if (data.isEmpty() || data == null) {
				System.out.println("link is null or empty");
			}
			URL url = new URL(data);
			HttpsURLConnection httpcode = (HttpsURLConnection) url.openConnection();
			httpcode.connect();
			if (httpcode.getResponseCode() >= 400) {
				System.out.println(httpcode.getResponseCode() + "-->" + data + "link is broken");
				brklink++;
			} else {
				System.out.println(httpcode.getResponseCode() + "-->" + data + "link is valid");
			}
		}
		System.out.println("totla link" + brklink);

	}

	@AfterMethod
	public void closebrowser() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
