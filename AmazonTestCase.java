package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonTestCase {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("oneplus 9 pro",Keys.ENTER);
		List<WebElement> items = driver.findElements(By.xpath("//span[@class='a-price']//span[2]//span[2]"));
		String price = items.get(0).getText();
		System.out.println(price);
		String priceRe = price.replaceAll("[^0-9]", "");
		double priceVal= Double.parseDouble(priceRe);
		System.out.println(priceVal);
		Thread.sleep(2000);
		Actions bulider= new Actions(driver);
		WebElement rating = driver.findElement(By.xpath("//i[@class='a-icon a-icon-star-small a-star-small-4 aok-align-bottom']"));
		bulider.moveToElement(rating).click().perform();
		Thread.sleep(2000);
		String ratingVal = driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-beside-button a-text-bold']")).getText();
		System.out.println("The percentage of 5 star rating " + ratingVal);
		WebElement fiveStar = driver.findElement(By.xpath("//table[@id='histogramTable']//tr//td[3]//a"));
		System.out.println(fiveStar.getText());
		bulider.moveToElement(items.get(0)).perform();
		driver.findElement(By.xpath("//a[@class='a-link-normal a-text-normal']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList= new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandlesList.get(1));
		WebElement itemImg = driver.findElement(By.xpath("//div[@id='imgTagWrapperId']"));
		File src= itemImg.getScreenshotAs(OutputType.FILE);
		File dtn= new File("./snaps/amazon.png");
		FileUtils.copyFile(src, dtn);
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		Thread.sleep(2000);
		Set<String> windowHandlesset = driver.getWindowHandles();
		List<String> windowHandlesList2= new ArrayList<String>(windowHandlesset);
		driver.switchTo().window(windowHandlesList2.get(1));
		Thread.sleep(2000);
		String subCart = driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']")).getText();
		System.out.println(subCart);
		//String[] split = subCart.split(".");
		String replaceAll = subCart.replaceAll("[^0-9 .]", "");
		System.out.println(replaceAll);
		double subCartDouble= Double.parseDouble(replaceAll);
		System.out.println(subCartDouble);
		
		if(subCartDouble==priceVal)
		{
			System.out.println("Test passed as price matched");
		}
		else System.out.println("Test failed");
		
	}

}
