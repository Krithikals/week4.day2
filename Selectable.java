package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Selectable {

	public static void main(String[] args) throws IOException {
	
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.get("http://www.leafground.com/pages/selectable.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement item1 = driver.findElement(By.xpath("//ol[@id='selectable']/li[1]"));
		WebElement item3 = driver.findElement(By.xpath("//ol[@id='selectable']/li[4]"));
		Actions bulider=new Actions(driver);
		bulider.clickAndHold(item1).moveToElement(item3).release().perform();
		File src= driver.getScreenshotAs(OutputType.FILE);
		File dtn=new File("./snaps/selectable.png");
		FileUtils.copyFile(src, dtn);
	
		
	}

}
