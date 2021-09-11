package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JquerySortable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://jqueryui.com/sortable/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.switchTo().frame(0);
		WebElement item1 = driver.findElement(By.xpath("//ul[@id='sortable']//li[1]"));
		//System.out.println(item1.getText());
		WebElement item3 = driver.findElement(By.xpath("//ul[@id='sortable']//li[5]"));
		//System.out.println(item3.getText());
		Point location = item3.getLocation();
		int x= location.getX();
		int y =location.getY();
		System.out.println(y);
		Actions bulider = new Actions(driver);
		//bulider.dragAndDropBy(item1, 0, 20).perform();
		bulider.dragAndDropBy(item1, x, y).perform();
	}

}
