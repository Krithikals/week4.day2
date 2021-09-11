package week4.day2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MouseHover {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.get("http://www.leafground.com/pages/mouseOver.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement src = driver.findElement(By.linkText("TestLeaf Courses"));
		Actions bulider= new Actions(driver);
		bulider.moveToElement(src).perform();
		Thread.sleep(2000);
		List<WebElement> link = driver.findElements(By.className("listener"));
		for (WebElement linkName : link) {
			System.out.println(linkName.getAttribute("href"));
		}
		link.get(2).click();
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		
	}

}
