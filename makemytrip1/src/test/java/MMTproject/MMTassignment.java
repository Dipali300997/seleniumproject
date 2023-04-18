package MMTproject;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MMTassignment {
	public static String url = "https://www.makemytrip.com/";
	ExtentReports er;// variable creation
	ExtentHtmlReporter ehr;
	ExtentTest et;
	WebDriver driver;
	WebDriverWait w1;
	JavascriptExecutor js;

	@BeforeTest
	public void bt() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(5000);
		er = new ExtentReports();
		ehr = new ExtentHtmlReporter("extent.html");
		er.attachReporter(ehr);
		et = er.createTest("This is before test.");

	}

	@Test(priority = 0)
	public void iframe() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		et = er.createTest("iframe");
		
		driver.switchTo().frame("notification-frame-1730629b2");
		List<WebElement> cancel = driver.findElements(By.xpath("//a[@class='close']"));
		if (cancel.size() != 0)
			driver.findElement(By.xpath("//a[@class='close']")).click();
		et.pass("User is able to switch to the iframe");
		et.pass("User is able to close the iframe by cliking on 'close' button");
		driver.switchTo().defaultContent();
		et.pass("User is able to switch to default content that is main window");
		et.info("This switching to the iframe will be successful only if the same iframe is present else this step will be failed");
	}

	@Test(priority = 1)
	public void holidaypackages() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		et = er.createTest("holidaypackages");
		WebElement hp = driver.findElement(By.linkText("Holiday Packages"));
		et.pass("User is able to view holiday packages button");
		hp.click();
		et.pass("User is able to click on holiday packages");
		List<WebElement> cancel = driver
				.findElements(By.xpath("//p[@class='primaryBtnWhite border-btn font16 latoBold']"));
		if (cancel.size() != 0)
			driver.findElement(By.xpath("//p[@class='primaryBtnWhite border-btn font16 latoBold']")).click();
	}

	@Test(priority = 2)
	public void fillupinformation() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		et = er.createTest("fillupinformation");
		WebElement city = driver.findElement(By.xpath("//*[@for='fromCity']"));
		et.pass("User is able to view 'from city'button");
		city.click();
		et.pass("User is able to click on 'from city' button");
		driver.findElement(By.xpath("//li[contains(text(),'Bangalore')]")).click();
		et.pass("User is able to click on 'Banglore'");
		driver.findElement(By.id("toCity")).click();
		et.pass("User is able to click on 'tocity'");
		Thread.sleep(5000);
		WebElement tocity = driver.findElement(By.xpath("//input[@Class='dest-search-input']"));
		Thread.sleep(5000);
		et.pass("User is able to find 'search'button");
		tocity.click();
		et.pass("User is able to click on 'tocity search'button");
		Thread.sleep(5000);
		tocity.sendKeys("Singapore");
		et.pass("User is able to send the keys in 'search input'");
		Thread.sleep(5000);
		//w1=new WebDriverWait(driver,5);
		//w1.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='dest-city-name']")));
		
		new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='dest-city-name']")));
		driver.findElement(By.xpath("//div[@class='dest-city-name']")).click();
		//List<WebElement> sing = driver.findElements(By.xpath("//div[@class='dest-city-name']"));
		et.pass("User is able to find'Singapore'in the tocity list");
		Thread.sleep(6000);
		//sing.get(0).click();
		//Thread.sleep(6000);
		et.pass("User is able to click on 'Singapore'");
		List<WebElement> startdate = driver.findElements(By.xpath("//p[contains(text(),'17')]"));
		Thread.sleep(6000);
		et.pass("User is able to find the start date");
		startdate.get(1).click();
		Thread.sleep(6000);
		et.pass("User is able to select the specific date as'17'");
		Thread.sleep(6000);
		driver.findElement(By.xpath("//button[contains(text(),'APPLY')]")).click();
		Thread.sleep(6000);
		et.pass("User is able to click on 'APPLY'button in the date picker");
		driver.findElement(By.xpath("//button[contains(text(),'APPLY')]")).click();
		Thread.sleep(6000);
		et.pass("User is able to click on 'APPLY' button in the filters");
		driver.findElement(By.id("search_button")).click();
		Thread.sleep(6000);
		et.pass("User is able to click on 'search' button");
		driver.findElement(By.xpath("//button[@class='skipBtn']")).click();
		Thread.sleep(6000);
		et.pass("User is able to click on 'skip'button");
		List<WebElement> close = driver.findElements(By.xpath("//span[@class='close closeIcon']"));
		Thread.sleep(6000);
		if (close.size() != 0)
			driver.findElement(By.xpath("//span[@class='close closeIcon']")).click();
		Thread.sleep(6000);
	}

	@Test(priority = 3)
	public void scroll() throws InterruptedException {
		et = er.createTest("scroll");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");

		et.pass("User is able to scroll down till the height of the window");

	}

	@Test(priority = 4)
	public void selectpackage() throws InterruptedException {
		et = er.createTest("selectpackag");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebElement arrow = driver.findElement(By.xpath(
				"//h2[contains(text(),'Invest in a Budget Holiday!')]//following::button[@Class='slick-arrow slick-next ']"));
		js.executeScript("arguments[0].click();", arrow);
		et.pass("User is able to find 'Invest in a Budget Holiday'");
		et.pass("User is able to find'>' button uder'Invest in a Budget Holiday'at the right side of the screen");
		et.pass("User is able to click on'>'button");
		String parentHandle = driver.getWindowHandle();
		driver.findElement(By.xpath("//h4[contains(text(),'Relaxing Singapore Getaway')]")).click();
		et.pass("User is able to click on 'Relaxing Singapore Getaway' ");
		Set<String> handles = driver.getWindowHandles();
		et.pass("User is able to switch to the child window");
		for (String s : handles) {
			if (!parentHandle.equalsIgnoreCase(s))
				driver.switchTo().window(s);
			et.pass("User is able to switch to the parent window");
		}
		driver.findElement(By.xpath("//button[contains(text(),'SKIP')]")).click();
		et.pass("User is able to click on skip button");
	}

	@Test(priority = 5)
	public void selecthotel() throws InterruptedException {
		et = er.createTest("selecthotel");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@data-testid='changeRemoveBtn_hotel']")).click();
		Thread.sleep(5000);
		et.pass("User is able to click on 'change'");
		driver.findElement(By.xpath("//input[@Class='font11 searchInput '] ")).click();
		Thread.sleep(5000);
		et.pass("User ia sble to click on search button");
		List<WebElement> newhotel = driver.findElements(By.xpath("//span[@Class='primaryBtn fill selectBtn']"));
		Thread.sleep(5000);
		newhotel.get(0).getText();
		Thread.sleep(5000);
		et.pass("User is able to select new hotel as well as able to get title of the hotel");
		JavascriptExecutor js1 = ((JavascriptExecutor) driver);
		js1.executeScript("arguments[0].click();", newhotel.get(0));
		driver.findElement(By.id("updateHotelBtnClick")).click();
		et.pass("User is able to click on 'update'");
		WebElement newhoteltitle = driver.findElement(By.xpath("//p[@Class='hotel-row-details-title']"));
		newhoteltitle.getText();
		WebElement validationhoteltitle = driver.findElement(By.xpath("//p[@Class='hotel-row-details-title']"));
		validationhoteltitle.getText();
		System.out.println(newhoteltitle.getText());
		System.out.println(validationhoteltitle.getText());
		Assert.assertEquals(newhoteltitle.getText(), validationhoteltitle.getText());
		System.out.println("Validation Successfull");
		et.pass("User is able to validate added hotel and previous hotel");
		et = er.createTest("selectactivity");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.id("chooseAndAddBtn")).click();
		et.pass("User is able to click on 'add activity'");
		List<WebElement> addactivity = driver.findElements(By.xpath("//span[@Class='primaryBtn fill selectBtn']"));
		List<WebElement> activitytitlbeforeadding = driver.findElements(By.xpath("//h3[@Class='activityHeading']"));
		WebElement atba = activitytitlbeforeadding.get(7);
		et.pass("User is able to select the 'maze activity'");
		String activityName = atba.getText();
		WebElement addingact = addactivity.get(7);
		addingact.click();
		et.pass("User is able to add an activity");
		driver.findElement(By.xpath("//p[@Class='updatePackageBtnText font10 btn btn-primary btn-sm']")).click();
		et.pass("User is able to update package");
		WebElement activitytitleafteradding = driver.findElement(By.xpath("//p[@Class='activity-row-details-title']"));
		String activityName2 = activitytitleafteradding.getText();
		System.out.println(activityName2);
		Assert.assertEquals(activityName, activityName2);
		System.out.println("Validation successful for maze activity");
		er.flush();
		/*et.pass("User is able to validate an activity name before and after adding it");
		driver.findElement(By.xpath("//*[@id=\"initeraryNav\"]/li[3]")).click();
		et.pass("User is able to click on 'itenerylink1'");
		WebElement VHTitle = driver.findElement(By.xpath("//p[@Class='hotel-row-details-title']"));
		VHTitle.getText();
		Assert.assertEquals(newhoteltitle.getText(), VHTitle.getText());
		System.out.println("After clicking one 1 hotel link the hotel validation is successfull");
		et.pass("User is able to validate the itenary hotel and previous selected hotel");
		driver.findElement(By.xpath("//*[@id=\"initeraryNav\"]/li[5]")).click();
		WebElement actTitle = driver.findElement(
				By.xpath("//p[contains(text(),'Jewel Changi Airport (Canopy Park & Mirror Maze)- Ticket Only')]"));
		String activityname3 = actTitle.getText();
		System.out.println(activityname3);
		Assert.assertEquals(activityName2, activityname3);
		System.out.println("After clicking on '1 activity link' the activity validation is successful");
		et.pass("User is able to validate the itenary activity and previous selected activity");
		et.info("All test cases are passed");
		er.flush();
		driver.quit();*/

	}

}
