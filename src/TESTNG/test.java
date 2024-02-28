package TESTNG;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import net.bytebuddy.utility.RandomString;

public class test {
	WebDriver driver;

	@BeforeMethod
	public void openbrowser() {
		System.setProperty("WebDriver.chrome.driver", "D:\\INSTALLER FILE\\chromedriver-win64");
		driver = new ChromeDriver();
		driver.get("https://demo.automationtesting.in/Register.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	}

	@Test(enabled = false)
	public void login() throws InterruptedException, IOException {
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//input[@type=\"radio\"])[1]")).click();
		WebElement ele = driver.findElement(By.xpath("(//input[@type=\"radio\"])[1]"));
		Boolean b = ele.isEnabled();
		System.out.println(b);

//		WebElement list = driver.findElement(By.xpath("//label[normalize-space()='Skills']"));
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView();", list);
//		js.executeScript("arguments[0].value='" + "anil" + "';", "");
//		js.executeScript("window.scrollBy(0,100);", "");

		// driver.findElement(By.xpath("(//select[@type=\"text\"])[1]")).click();
//		List<WebElement> day = driver.findElements(By.xpath("//select[@id='yearbox']"));
//		System.out.println(day.size());

		WebElement year = driver.findElement(By.xpath("//select[@id='yearbox']"));
		Select s = new Select(year);
		List<WebElement> o = s.getOptions();
		for (int i = 0; i < o.size(); i++) {
			// System.out.println(o.get(i).getText());
			if (o.get(i).getText().equals("1998")) {
				o.get(i).click();
			}
		}

		WebElement a3 = driver.findElement(By.xpath("//h2[normalize-space()='Register']"));
		String s1 = "demo";
		String s2 = RandomString.make(3);
		File source = ((TakesScreenshot) a3).getScreenshotAs(OutputType.FILE);
		File dest = new File("C:\\Users\\DELL\\eclipse-workspace\\WB\\ss\\" + s1 + " " + s2 + ".jpg");
		FileHandler.copy(source, dest);

		String name = "test";
		String m = RandomString.make(2);
		File b1 = ((TakesScreenshot) a3).getScreenshotAs(OutputType.FILE);
		File b2 = new File("C:\\Users\\DELL\\eclipse-workspace\\WB\\ss\\" + name + m + ".jpg");
		FileHandler.copy(b1, b2);

		Actions act = new Actions(driver);
		WebElement intractio = driver.findElement(By.xpath("//a[normalize-space()='Interactions']"));
		act.moveToElement(intractio).build().perform();
		driver.findElement(By.xpath("//a[normalize-space()='Drag and Drop']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Static']")).click();

		WebElement a1 = driver.findElement(By.xpath("//img[@id='angular']"));
		WebElement a2 = driver.findElement(By.xpath("//div[@id='droparea']"));

		act.dragAndDrop(a1, a2).build().perform();
		String d1 = "sc";
		String d2 = RandomString.make(2);
		File source1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Path = new File("path\\" + d2 + d1 + ".jpg");
		FileHandler.copy(source1, Path);
	}

	@Test(enabled = false)
	public void readdata() throws EncryptedDocumentException, IOException {
		String path = "D:\\money taken.xlsx";
		FileInputStream file1 = new FileInputStream(path);
		String data = WorkbookFactory.create(file1).getSheet("Sheet1").getRow(0).getCell(0).getStringCellValue();
		System.out.println(data);
	}

	@Test
	public void popup() throws InterruptedException {
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//a[normalize-space()='SwitchTo']"));
		act.moveToElement(ele).build().perform();
//		driver.findElement(By.xpath("//a[normalize-space()='Alerts']")).click();
//		Thread.sleep(5000);
//
//		driver.findElement(By.xpath("//a[normalize-space()='Alert with OK & Cancel']")).click();
//		driver.findElement(By.xpath("//button[normalize-space()='click the button to display a confirm box']")).click();
//		Alert al = driver.switchTo().alert();
//		al.accept();
//	    al.dismiss();

		driver.findElement(By.xpath("//a[normalize-space()='Windows']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Open New Seperate Windows']")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		String parentwindow = driver.getWindowHandle();
		System.out.println(parentwindow);

		Set<String> list = driver.getWindowHandles();
		System.out.println(list.size());
		// System.out.println(list);

		for (String lists : list) {
			if (!parentwindow.equalsIgnoreCase(lists)) {
				driver.switchTo().window(lists);
				String data = driver
						.findElement(By.xpath("//span[@class='navbar-logo']//*[name()='svg']//*[name()='path']"))
						.getText();
				System.out.println(data);
				Thread.sleep(4000);

			}
		}
		driver.switchTo().window(parentwindow);
		String data2 = driver.findElement(By.xpath("//h1[normalize-space()='Automation Demo Site']")).getText();
		System.out.println(data2);
		
		

	}

	@AfterMethod
	public void closebrowser() throws InterruptedException {
		Thread.sleep(6000);
		driver.quit();
	}

}
