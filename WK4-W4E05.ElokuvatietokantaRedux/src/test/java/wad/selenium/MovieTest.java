package wad.selenium;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class MovieTest{
    
    private WebDriver driver;

    private final String MOVIE_URL = "http://localhost:8080/movies/";
    private final String ACTOR_URL = "http://localhost:8080/actors/";

    
    @Before
    public void setUp() {
        this.driver = new HtmlUnitDriver();
    }
    
    @Test
    public void movieTest() {
        driver.get(MOVIE_URL);
        WebElement element;
        
        assertFalse(driver.getPageSource().contains("Bloodsport"));
        assertFalse(driver.getPageSource().contains("Van Damme"));
        
        element = driver.findElement(By.id("name"));
        element.sendKeys("Bloodsport");
        
        element = driver.findElement(By.id("lengthInMinutes"));
        element.sendKeys("92");
        
        element.submit();
        
        assertTrue(driver.getPageSource().contains("Bloodsport"));
        assertFalse(driver.getPageSource().contains("Van Damme"));
        
        driver.get(ACTOR_URL);
        
        assertFalse(driver.getPageSource().contains("Van Damme"));
        
        element = driver.findElement(By.id("name"));
        element.sendKeys("Van Damme");
        element.submit();
        
        assertTrue(driver.getPageSource().contains("Van Damme"));
        
        driver.findElement(By.linkText("Van Damme")).click();
        
        driver.findElement(By.id("add-to-movie")).click();
        
        driver.get(MOVIE_URL);
        
        assertTrue(driver.getPageSource().contains("Bloodsport"));
        assertTrue(driver.getPageSource().contains("Van Damme"));
    }
}