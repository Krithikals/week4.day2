package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyntraTestCase {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    //WebElement jackets =  driver.findElement(By.xpath("//a[text()='Jackets']"));
	    WebElement menu = driver.findElement(By.xpath("//a[text()='Men']"));
	    //menu.click();
	    Actions bulider = new Actions(driver);
	    bulider.moveToElement(menu).perform();
	    driver.findElement(By.xpath("//a[text()='Jackets']")).click();
	    String jacketOverall = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
	    
	    String jacketCount = driver.findElement(By.xpath("(//span[@class='categories-num'])[1]")).getText();
	    
	    String rainJacketCount = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();
	    
	    String jacketOverallre = jacketOverall.replaceAll("[^0-9]", "");
	    int jacketOverallInt = Integer.parseInt(jacketOverallre);
	    System.out.println(jacketOverallInt);
	    String jacketCountre = jacketCount.replaceAll("[^0-9]", "");
	    int jacketCountInt = Integer.parseInt(jacketCountre);
	    System.out.println(jacketCountInt);
	    String rainJacketCountre = rainJacketCount.replaceAll("[^0-9]", "");
	    int rainJacketCountInt = Integer.parseInt(rainJacketCountre);
	    System.out.println(rainJacketCountInt);
	    int overallCount= jacketCountInt + rainJacketCountInt;
	    if(jacketOverallInt==overallCount)
	    {
	    	System.out.println("The count is getting matched " + overallCount);
	    }
	    else
	    {
	    	System.out.println("Count doesn't match");
	    }
	    
	    driver.findElement(By.xpath("//label[@class='common-customCheckbox vertical-filters-label']/div")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[@class='brand-more']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@placeholder='Search brand']")).sendKeys("duke");
	    driver.findElement(By.xpath("//label[@class=' common-customCheckbox']/div")).click();
	    driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();
	    Thread.sleep(2000);
	    List<WebElement> items = driver.findElements(By.xpath("//li[@class='product-base']/a/div[2]/h3"));
	    String str="Duke";
	    int i=0;
	    for (WebElement dukeItem : items) {
	    	if(!dukeItem.getText().equalsIgnoreCase(str))
	    	{
	    	 i=i+1;	
	    	}
		}
	    if(i==0)
	    {
	    	System.out.println("All items are Duke");
	    }
	    else System.out.println("The " + i + " are not Duke brand");
       
	    driver.findElement(By.xpath("//span[@class='myntraweb-sprite sort-downArrow sprites-downArrow']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//label[text()='Better Discount']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//img[@class='img-responsive']")).click();
	    Set<String> windowHandles = driver.getWindowHandles();
	    List<String> windowHandleslist= new ArrayList<String>(windowHandles);
	    driver.switchTo().window(windowHandleslist.get(1));
	    
	    WebElement itemscr = driver.findElement(By.xpath("//div[@class='image-grid-image']"));
	    File src= itemscr.getScreenshotAs(OutputType.FILE);
	    File dtn= new File("./spans/mnytra.png");
	    FileUtils.copyFile(src, dtn);
	    driver.findElement(By.xpath("//span[@class='myntraweb-sprite pdp-notWishlistedIcon sprites-notWishlisted pdp-flex pdp-center ']")).click();
	    Thread.sleep(2000);
	    driver.quit();
	}
	

}

