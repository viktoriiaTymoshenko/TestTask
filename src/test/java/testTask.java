import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;


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
        void testSetup() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\vity0116\\New folder\\Desktop\\Test_Task\\Test\\src\\test\\java\\Resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
	}
	
	@Test
	void test() throws InterruptedException {
		Thread.sleep(2000);
		driver.get("https://yandex.ru/");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='text']")).sendKeys("Яндекс почта");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button [contains (., 'Найти')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Яндекс.Почта — бесплатная электронная почта")).click();
		for (String windHandle : driver.getWindowHandles()) {
			driver.switchTo().window(windHandle);
		}
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		driver.findElement(By.xpath("//div [@class= 'FooterButtons']/a[contains (., 'Войти')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='passp-field-login']")).sendKeys("!6");
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
