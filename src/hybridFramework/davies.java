package hybridFramework;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class davies {

	static WebDriver driver;

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "F:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://davies-group.com/");

	}
	
	@Test(priority = 1)
	public void openTwitterLink() throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
		driver.findElement(By.cssSelector("a[href='https://twitter.com/Davies_Group']")).click();
		// Thread.sleep(5000);
		Set<String> winHandles = driver.getWindowHandles();
		driver.switchTo().window(winHandles.toArray()[1].toString());
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://twitter.com/Davies_Group");
	}

	@Test(priority = 2)
	public void openLinkedn() throws InterruptedException {
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		jse2.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
		driver.findElement(By.xpath("//*[@href='https://www.linkedin.com/company/daviesgroup/']")).click();
		Thread.sleep(3000);
		Set<String> winHandles = driver.getWindowHandles();
		driver.switchTo().window(winHandles.toArray()[1].toString());
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='join-form-submit']")).isDisplayed(), true);
		String url = driver.getCurrentUrl();
		if (url.contains("https://www.linkedin.com")) {
		System.out.println("linkedin Validation -Successful"); 
	    } else {
		System.out.println("linkedin Validation -Unsuccessful");
		}
	}
	
	@Test (priority=3)
	public void Investigation() throws InterruptedException {
		Set<String>winHandles= driver.getWindowHandles();
		driver.switchTo().window(winHandles.toArray()[0].toString());
		driver.findElement(By.linkText("Solutions")).click();
		Thread.sleep(3000);
		JavascriptExecutor jse3 = (JavascriptExecutor)driver;
		jse3.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[contains(text(),'View All')][@class='cta-button__text learn-more__text']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@id='select2--container']")).sendKeys("Fire investigation");
		Thread.sleep(3000);
		JavascriptExecutor jse4 = (JavascriptExecutor)driver;
		jse3.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
	    String Results= driver.findElement(By.xpath("//h2[contains(text(),'Results')]")).getText();
	    System.out.println("Results="+Results);
		}
	
	@Test(priority=4) 
	public void location() {
		WebElement abt =driver.findElement(By.linkText("About")); 
		Actions actions=new Actions(driver); 
		actions.moveToElement(abt).build().perform();
		driver.findElement(By.linkText("Locations")).click();
		driver.findElement(By.xpath("//button[contains(text(),'UK & Ireland')]")).click();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
		WebElement stokeTrent = driver.findElement(By.xpath("//a[text()='UK & Ireland']"));
		jse.executeScript("arguments[0].scrollIntoView();",stokeTrent);
		driver.findElement(By.cssSelector("#marker0_12")).click();
		driver.findElement(By.cssSelector("#desc0_12 p")).isDisplayed();
		Assert.assertEquals(driver.findElement(By.xpath("//*[text()='Stoke on Trent']")).isDisplayed(), true);
		}

	@AfterTest
	public void tearDown() {
		driver.quit();		
		}
}
 