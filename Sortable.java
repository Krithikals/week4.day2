package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sortable {

	public static void main(String[] args) throws InterruptedException {
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver= new ChromeDriver();
	driver.get("http://www.leafground.com/pages/sortable.html");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	WebElement item1 = driver.findElement(By.xpath("//ul[@id='sortable']/li[text()='Item 1']/span"));
	//System.out.println(item1.getText());
	WebElement item3 = driver.findElement(By.xpath("//ul[@id='sortable']/li[text()='Item 4']/span"));
	//System.out.println(item3.getText());
	Point location = item3.getLocation();
	int x= location.getX();
	int y =location.getY();
	System.out.println(y);
	Actions bulider = new Actions(driver);
	//bulider.dragAndDropBy(item1, 0, 20).perform();
	bulider.dragAndDropBy(item1, x, y).perform();
	//bulider.clickAndHold(item1).moveByOffset(0, y).release().perform();
	//bulider.click(item1).clickAndHold(item1).moveToElement(item3).moveByOffset(0, 10).release().build().perform();
	
	}

}
