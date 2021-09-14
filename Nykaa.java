package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver= new ChromeDriver();
	driver.get("https://www.nykaa.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	Thread.sleep(1000);
	Actions bulider=new Actions(driver);
	WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
	bulider.moveToElement(brands).perform();
	Thread.sleep(3000);
	//driver.findElement(By.xpath("//a[text()='Popular']")).click();
	driver.findElement(By.xpath("//ul[@class='l-vertical-list']/li[5]/a")).click();
	Set<String> windowHandles = driver.getWindowHandles();
	List<String> windowList=new ArrayList<String>(windowHandles);
	driver.switchTo().window(windowList.get(1));
	System.out.println(driver.getTitle());
    WebElement scroll = driver.findElement(By.xpath("(//div[@class='clearfix'])[2]"));
    scroll.click();
    driver.findElement(By.xpath("(//div[@class='category-wrap-top'])[1]//div[2]")).click();
    driver.findElement(By.xpath("(//div[@class='category-wrap-top'])[2]//div[2]")).click();
    driver.findElement(By.xpath("//span[@class='filter-name-count']/following-sibling::div")).click();
    
    List<WebElement> itemList = driver.findElements(By.xpath("//div[@class='m-content__product-list__title']//span"));
    List<WebElement> buttonList = driver.findElements(By.xpath("//div[@class='card-bottom-actions']//button"));
    List<WebElement> priceList = driver.findElements(By.xpath("//span[@class='post-card__content-price-offer']"));
    int size= itemList.size();
    String str="L'Oreal Paris Colour Protect Shampoo";
    String price,priceReplace = null;
    for (int i = 0; i < size; i++) {
    	String text = itemList.get(i).getText();
    	System.out.println(text);
     if(text.equals(str))
     {
    	 bulider.moveToElement(itemList.get(i)).perform();
    	 buttonList.get(i).click();
    	 Thread.sleep(3000);
    	 //List<WebElement> drop = driver.findElements(By.xpath("//span[@class='fill-canvas']"));
    	 //drop.get(1).click();
    	 driver.findElement(By.xpath("(//span[@class='size-custom-radio'])[2]")).click();
    	 driver.findElement(By.xpath("//div[@class='details-btn-wrap']//div[2]/button")).click();
          price = priceList.get(i).getText();
         priceReplace=price.replaceAll("[^0-9]", "");
    	 //System.out.println(priceReplace);
    	 break;
     }
	}
    int parseInt = Integer.parseInt(priceReplace);
    System.out.println("The Price in Search Functionality " + parseInt);
    driver.findElement(By.xpath("//div[@class='AddBagIcon']")).click();
    Thread.sleep(3000);
    WebElement serError = driver.findElement(By.xpath("//div[@class='server-error-popup ']//button"));
    boolean serErrorDis = serError.isDisplayed();
    if(serErrorDis)
    {
    	serError.click();
    }
    Thread.sleep(2000);
    //driver.findElement(By.xpath("//div[@class='close']")).click();
    String cartprice = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
    String cartPriceRe=cartprice.replaceAll("[^0-9]", "");
    int cartPriceReInt= Integer.parseInt(cartPriceRe);
    System.out.println("The overall Price is " + cartPriceReInt);
    driver.findElement(By.xpath("//div[@class='second-col']/button")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@class='btn full big']")).click();
    String finalPrice = driver.findElement(By.xpath("(//div[@class='value'])[2]/span")).getText();
    int finalPriceInt= Integer.parseInt(finalPrice);
    if(finalPriceInt==cartPriceReInt)
    {
    	System.out.println("The cart price & final price is same " + finalPriceInt);
    }
    else
    {
    	System.out.println("The cart price & final price are not same " + finalPriceInt);
    }
    driver.quit();
	//JavascriptExecutor js = (JavascriptExecutor) driver;
	//js.executeScript("arguments[0].scrollIntoView(true);", scroll);
	

	}

}
