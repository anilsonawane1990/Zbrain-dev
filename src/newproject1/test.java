package newproject1;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import java.io.FileInputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import net.bytebuddy.utility.RandomString;

public class test {
	public static WebDriver driver;

	@SuppressWarnings("deprecation")
	@Parameters("brow")
	@BeforeMethod
	public void openpage(String brow) {
		if (brow.equalsIgnoreCase("c")) {
			System.setProperty("webDriver.chrome.driver", "D:\\INSTALLER FILE\\chromedriver-win64");
			driver = new ChromeDriver();
		} else if (brow.equalsIgnoreCase("e")) {
			System.setProperty("webDriver.edge.driver", "D:\\INSTALLER FILE\\edgedriver_win64");
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demo.automationtesting.in/Register.html");
	}

	@Test(enabled = false)
	public void page() throws IOException {
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//a[normalize-space()='SwitchTo']"));
		act.moveToElement(ele).build().perform();
		// act.contextClick(ele).build().perform(); right click
		// act.clickAndHold(ele).build().perform();
		// act.doubleClick(ele)
		// act.dragAndDrop(ele, ele)
		driver.findElement(By.xpath("//a[normalize-space()='Windows']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Open New Seperate Windows']")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();

		String parent = driver.getWindowHandle();
		System.out.println(parent);
		Set<String> child = driver.getWindowHandles();

		for (String c : child) {
			if (parent.equalsIgnoreCase(c)) {
				driver.switchTo().window(c);
				System.out.println(driver.getTitle());
				String name = "file";
				String n = RandomString.make(3);
				String Path = "C:\\Users\\DELL\\eclipse-workspace\\newproject1\\SS\\";
				File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				File dest = new File(Path + name + n + ".jpg");
				FileHandler.copy(source, dest);

			}
		}

	}

	@Test(enabled = false)
	public void exceldata() throws EncryptedDocumentException, IOException {
		String path = "C:\\Users\\DELL\\eclipse-workspace\\newproject1\\read data through Apache POI in selenium.xlsx";
		FileInputStream f = new FileInputStream(path);
//		String data = WorkbookFactory.create(f).getSheet("Sheet1").getRow(0).getCell(0).getStringCellValue();
//		System.out.println(data);
		//
		XSSFWorkbook boo = new XSSFWorkbook(f);
		XSSFSheet sheet = boo.getSheetAt(0);

		int row = sheet.getLastRowNum();
		System.out.println(row);
		int cell = sheet.getRow(1).getLastCellNum();
		System.out.println(cell);

		for (int i = 0; i <= row; i++) {
			XSSFRow r = sheet.getRow(i);
			for (int j = 0; j < cell; j++) {
				XSSFCell c = r.getCell(j);
				switch (c.getCellType()) {
				case STRING:
					System.out.print(c.getStringCellValue());
					break;
				case NUMERIC:
					System.out.print(c.getNumericCellValue());
					break;
				case BOOLEAN:
					System.out.print(c.getBooleanCellValue());
					break;
				}
				System.out.print("  || ");
			}
			System.out.println();
		}

	}

	@Test(enabled = false)
	public void iframe() throws InterruptedException {
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//a[normalize-space()='SwitchTo']"));
		act.moveToElement(ele).build().perform();

		driver.findElement(By.xpath("//a[normalize-space()='Frames']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Iframe with in an Iframe']")).click();
		driver.switchTo().frame(1);
		Thread.sleep(3000);
		String text = driver.findElement(By.xpath("//h5[normalize-space()='Nested iFrames']")).getText();
		System.out.println(text);
		Thread.sleep(3000);
		driver.switchTo().frame(0);
		WebElement text1 = driver.findElement(By.xpath("//input[@type='text']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + "anil" + "';", text1);
		js.executeScript("window.scrollBy(0,100)", "");
		js.executeScript("arguments[0].scrolIntoView();", text1);
	}

	@Test(enabled = false)
	public void dropdown() {
		driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();
		WebElement text1 = driver.findElement(By.xpath("//label[normalize-space()='Date Of Birth']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", text1);
		WebElement ele2 = driver.findElement(By.xpath("//select[@id='yearbox']"));
		Select s = new Select(ele2);

		List<WebElement> l = s.getOptions();
		System.out.println(l.size());
		for (int i = 0; i < l.size(); i++) {
			String data = l.get(i).getText();
			System.out.println(data);
			if (l.get(i).getText().equals("1998")) {
				l.get(i).click();
			}
		}
	}

	@Test(enabled = true)
	public void popups() {
//		ChromeOptions co=new ChromeOptions();
//		co.addArguments("--disable-notifications");
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//a[normalize-space()='SwitchTo']"));
		act.moveToElement(ele).build().perform();
		driver.findElement(By.xpath("//a[normalize-space()='Alerts']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Alert with OK & Cancel']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='click the button to display a confirm box']")).click();
		Alert al = driver.switchTo().alert();
		al.accept();
		driver.findElement(By.xpath("//a[normalize-space()='Alert with Textbox']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='click the button to demonstrate the prompt box']"))
				.click();
		al.sendKeys("Anil");
		al.accept();
	}

	@Test(enabled = false)
	public void getalllink() throws IOException {
		List<WebElement> link = driver.findElements(By.tagName("a"));
		System.out.println(link.size());

//		for (WebElement l : link) {
//			String s = l.getAttribute("href");
//			System.out.println(s);
//		}
		int count = 0;
		for (WebElement l1 : link) {
			String data = l1.getAttribute("href");
			if (data.isEmpty() || data == null) {
				System.out.println("link is empty or null");

			}
			URL url = new URL(data);
			HttpsURLConnection conection = (HttpsURLConnection) url.openConnection();
			conection.connect();
			if (conection.getResponseCode() >= 400) {
				System.out.println(data + "link is invalid" + conection.getResponseCode());
				count++;
			} else {
				System.out.println(data + "link is valid" + conection.getResponseCode());
			}

		}
		System.out.println("all link" + count);

	}

	@Test(enabled = false)
	public void vaits() {
		String ele = "//label[normalize-space()='Male']";
		WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement ele1 = mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ele)));
		ele1.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void Webdrivermethod() {
		driver.manage().window().maximize();
		driver.get(null);
		driver.getTitle();
		driver.getCurrentUrl();
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("");
		driver.close();
		driver.quit();
		Point p = new Point(100, 20);
		driver.manage().window().setPosition(p);
		System.out.println(driver.manage().window().getPosition());
		Dimension d = new Dimension(120, 34);
		driver.manage().window().setSize(d);
		System.out.println(driver.manage().window().getSize());

		// WebElement method
		driver.findElement(By.xpath("//label[normalize-space()='Male']")).click();
		driver.findElement(By.xpath("//label[normalize-space()='Male']")).clear();
		WebElement ele = driver.findElement(By.xpath("//label[normalize-space()='Male']"));
		ele.sendKeys("Err");

		String text = driver.findElement(By.xpath("//label[normalize-space()='Male']")).getText();
		System.out.println(text);
		Boolean b = driver.findElement(By.xpath("//label[normalize-space()='Male']")).isDisplayed();
		System.out.println(b);

		Boolean b1 = driver.findElement(By.xpath("//label[normalize-space()='Male']")).isEnabled();
		System.out.println(b1);
		Boolean b2 = driver.findElement(By.xpath("//label[normalize-space()='Male']")).isSelected();
		System.out.println(b2);
		Assert.assertEquals(false, false);
		SoftAssert sf = new SoftAssert();
		sf.assertEquals(0, 0);
		sf.assertAll();
	}

	@AfterMethod
	public void cloasepage() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
		;
	}

}
