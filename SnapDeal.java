package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get("https://www.snapdeal.com/");
	driver.findElement(By.id("pushDenied")).click();
	WebElement fashion = driver.findElement(By.xpath("(//span[@class='catText'])[6]"));
	Actions bulider=new Actions(driver);
	bulider.moveToElement(fashion).perform();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
	String shoes = driver.findElement(By.xpath("//div[text()='Sports Shoes']/following::div")).getText();
	System.out.println("Total shoes " + shoes);
	driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
	driver.findElement(By.xpath("//i[@class='sd-icon sd-icon-expand-arrow sort-arrow']")).click();
	List<WebElement> sortdrp = driver.findElements(By.xpath("//li[@class='search-li']"));
	sortdrp.get(0).click();
	Thread.sleep(4000);
	List<WebElement> items = driver.findElements(By.xpath("//div[@class='product-price-row clearfix']//span[2]"));
	int size=items.size();
	System.out.println("The size of the array is "+ size);
	List<Integer> cpyItems= new ArrayList<Integer>();
	
	for (int i = 0; i < size; i++) {
		String price = items.get(i).getText();
		String priceRe= price.replaceAll("[^0-9]", "");
		//System.out.println(priceRe);
		int priceint= Integer.parseInt(priceRe);
		cpyItems.add(priceint);
	}
	Collections.sort(cpyItems);
	System.out.println("Comparing");
	for (int i = 0; i < size; i++) {
		String price = items.get(i).getText();
		String priceRe= price.replaceAll("[^0-9]", "");
		//System.out.println(priceRe);
		int priceint= Integer.parseInt(priceRe);
		if(cpyItems.get(i)!=priceint)
		{
		System.out.println("Items are not sorted");
		break;
	    }
	}
	
	driver.findElement(By.xpath("//label[@for='Brand-HASTAGMANVI']")).click();
	Thread.sleep(2000);
	WebElement shoe = driver.findElement(By.xpath("//div[@class='product-tuple-image ']"));
	bulider.moveToElement(shoe).perform();
	driver.findElement(By.xpath("//div[@class='clearfix row-disc']/div")).click();
	Thread.sleep(2000);
	//Set<String> windowHandles = driver.getWindowHandles();
	//List<String> windowList= new ArrayList<String>(windowHandles);
	//driver.switchTo().window(windowList.get(1));
	WebElement shoeImg = driver.findElement(By.xpath("//img[@itemprop='image']"));
	Thread.sleep(2000);
	File src=shoeImg.getScreenshotAs(OutputType.FILE);
	File dtn= new File("./snaps/shoes.png");
	FileUtils.copyFile(src, dtn);
	String priceShoe = driver.findElement(By.xpath("//div[@class='lfloat']//div[2]/span[1]")).getText();
	String dis = driver.findElement(By.xpath("//div[@class='lfloat']//div[2]/span[2]")).getText();
	System.out.println("The Price of Shoe is " + priceShoe + "with discount of " + dis);
	driver.findElement(By.xpath("//div[@class='close close1 marR10']")).click();
	driver.close();
	}
	
	}


