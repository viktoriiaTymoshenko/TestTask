import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;
import java.nio.file.Paths;
import org.apache.commons.lang3.RandomStringUtils;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class testTask {

	public static WebDriver driver;
	@BeforeEach
        void testSetup() throws IOException {
		System.setProperty("webdriver.chrome.driver",Paths.get(System.getProperty("user.dir")).toRealPath()+"\\src\\test\\java\\Resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
	}
	
	@Test
	void test() throws InterruptedException {
		driver.get("https://yandex.ru/");
		driver.findElement(By.xpath("//input[@id='text']")).sendKeys("Яндекс почта");
		driver.findElement(By.xpath("//button [contains (., 'Найти')]")).click();
		driver.findElement(By.linkText("Яндекс.Почта — бесплатная электронная почта")).click();
		for (String windHandle : driver.getWindowHandles()) {
			driver.switchTo().window(windHandle);
		}
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		driver.findElement(By.xpath("//div [@class= 'FooterButtons']/a[contains (., 'Войти')]")).click();
		driver.findElement(By.xpath("//input[@id='passp-field-login']")).sendKeys(RandomStringUtils.randomAlphanumeric(10));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button [contains (., 'Войти')]")).click();
		Thread.sleep(2000);
		assertEquals(driver.findElement(By.xpath("//div[@class='passp-form-field__error']")).getText(), "Такой логин не подойдет");

	}

	@AfterEach
	  void closeBrowser(){
		driver.manage().deleteAllCookies();
		driver.quit();
	        } 
}
