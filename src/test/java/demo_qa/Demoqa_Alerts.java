package demo_qa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Demoqa_Alerts {
        WebDriver driver;

        @BeforeMethod
        public void setUp(){
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("https://demoqa.com/");
        }
        @Test
        public void acceptAlert() throws InterruptedException {
            WebElement alert=driver.findElement(By.xpath("(//div[@class='card mt-4 top-card'])[3]"));
            alert.click();
            Thread.sleep(3000);

            Actions actions =new Actions(driver);
            actions.sendKeys(Keys.PAGE_DOWN).perform();
            Thread.sleep(3000);

            WebElement alert1=driver.findElement(By.xpath("(//li[@class='btn btn-light '])[12]"));
            alert1.click();
            Thread.sleep(3000);

            WebElement clickMe1=driver.findElement(By.xpath("//button[@id='alertButton']"));
            clickMe1.click();
            String text = driver.switchTo().alert().getText();
            Assert.assertEquals("You clicked a button", text);
            driver.switchTo().alert().accept();
            Thread.sleep(3000);

            WebElement clickMe2=driver.findElement(By.xpath("//button[@id='timerAlertButton']"));
            clickMe2.click();
            Thread.sleep(7000);
            String text1 = driver.switchTo().alert().getText();
            Assert.assertEquals("This alert appeared after 5 seconds", text1);
            driver.switchTo().alert().accept();
            Thread.sleep(3000);

            WebElement clickMe3=driver.findElement(By.xpath("//button[@id='confirmButton']"));
            clickMe3.click();
            Thread.sleep(3000);
            driver.switchTo().alert().accept();
            Thread.sleep(3000);
            String confirm=driver.findElement(By.xpath("//span[@id='confirmResult']")).getText();
            Assert.assertEquals(confirm, "You selected Ok");
            Thread.sleep(3000);

            WebElement clickMe4=driver.findElement(By.xpath("//button[@id='promtButton']"));
            clickMe4.click();
            Thread.sleep(3000);
            driver.switchTo().alert().sendKeys("Jacob");
            Thread.sleep(3000);
            driver.switchTo().alert().accept();
            Thread.sleep(2000);
            String prompt=driver.findElement(By.xpath("//span[@id='promptResult']")).getText();
            Assert.assertEquals(prompt, "You entered Jacob");

        }
        @AfterMethod
        public void tearDown(){
            driver.quit();
        }



    }
