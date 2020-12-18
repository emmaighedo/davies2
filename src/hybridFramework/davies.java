package hybridFramework;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class davies {
	
	static WebDriver driver;
	
	public static void main (String []args) throws InterruptedException {
		
		
		System.setProperty("webdriver.chrome.driver", "F:\\chromedriver.exe");
		
		/*Launch the Browser
		Navigate to "https://davies-group.com/"
		Click "twitter" button
		
		Verification Point:
			Capture the current url and compare with the expected
		
		Click "Linkedin" Button
		Verification Point:
			Capture the current url and compare with the expected*/
			
		
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("https://davies-group.com/");
	
		driver.findElement(By.xpath("//*[@href='https://twitter.com/Davies_Group']")).click();
									
		
		Set<String>winHandles= driver.getWindowHandles();
		driver.switchTo().window(winHandles.toArray()[1].toString());
		String url= driver.findElement(By.xpath("//*[@id=\'react-root\']")).getText();
	
		  if (url.equals("https://twitter.com/Davies_Group")){
			  System.out.println("Twitter Validation successful- Passed"); 
		} 
		else {
				  System.out.println("Twitter Validation unsuccessful- Failed" ); 
		}
			driver.switchTo().window(winHandles.toArray()[0].toString());
		 driver.findElement(By.xpath("//*[@href='https://www.linkedin.com/company/daviesgroup/']")).click();
		  String url2= driver.findElement(By.xpath("//*[@href='https://www.linkedin.com/company/daviesgroup/']")).getText();
		  if (url2.equals("https://www.linkedin.com/company/daviesgroup/")){
			  System.out.println("Twitter Validation successful- Passed"); 
		} 
		else {
				  System.out.println("Twitter Validation unsuccessful- Failed" ); 
				  
		}
		  
			/*	  Click on 'Solutions' link
		  Click "View All" button
		  Enter "Fire Verification" on the search Area*/
		  
		  driver.findElement(By.linkText("Solutions")).click();
		driver.findElement(By.xpath("//*[@class='cta-button__circle view-all__circle']")).click();
		//driver.findElement(By.xpath("//span[@id='select2--container']")).sendkeys(Fire Investigation").click();
		

		
	
		
		 /** Click on 'About' link
		 * Click on 'Location' link Click on 'UK & Ireland' button 
		 * Click on 'Stoke on Trent' address on the map
		 */
		
		
		
		  WebElement abt = driver.findElement(By.linkText("About"));
		  Actions actions=new Actions(driver); 
		  actions.moveToElement(abt).build().perform();
		  driver.findElement(By.linkText("Locations")).click();
		  driver.findElement(By.xpath("//button[contains(text(),'UK & Ireland')]")).
		  click(); driver.findElement(By.xpath("//*[@id=\"marker0_12\"]")).click();
		  String Message=driver.findElement(By.xpath("//*[text()='Stoke on Trent']")).getText();
		  
		  if (Message.contains ("Stoke on Trent" )){
		  System.out.println("Stoke Office Captured- Passed"); 
		  } else {
		  System.out.println("Stoke Office Not Captured- Failed" ); 
		 
		  driver.quit();
		  }
}
}