package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import java.nio.file.Files;
import java.nio.file.Path;

public class GoogleSearchTest {
    WebDriver driver;


@BeforeClass
public void setup() throws Exception {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");

    // Optional: set a unique temp user-data-dir
    Path tempProfile = Files.createTempDirectory("chrome-profile");
    options.addArguments("--user-data-dir=" + tempProfile.toAbsolutePath().toString());

    driver = new ChromeDriver(options);
}



    @Test
    public void testGoogleSearch() {
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("OpenAI ChatGPT");
        searchBox.submit();

        Assert.assertTrue(driver.getTitle().toLowerCase().contains("openai"));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
