package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JQuerySelectable {

	public static void main(String[] args) throws IOException {
    
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://jqueryui.com/selectable/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.switchTo().frame(0);
		List<WebElement> list = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		WebElement source = list.get(0);
		WebElement target = list.get(3);
		Actions bulider=new Actions(driver);
		bulider.clickAndHold(source).moveToElement(target).release().perform();
		File src=driver.getScreenshotAs(OutputType.FILE);
		File dtn=new File("./snaps/jqueryselectable.png");
		FileUtils.copyFile(src, dtn);
	}

}
