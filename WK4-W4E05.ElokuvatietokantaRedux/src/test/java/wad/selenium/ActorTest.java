package wad.selenium;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class ActorTest{
    
    private WebDriver driver;

    private final String ACTOR_URL = "http://localhost:8080/actors/";

    
    @Before
    public void setUp() {
        this.driver = new HtmlUnitDriver();
    }

    @Test
    public void addingAndDeletingActor() {
        driver.get(ACTOR_URL);
        assertFalse(driver.getPageSource().contains("Van Damme"));
      
        WebElement element = driver.findElement(By.id("name"));
        element.sendKeys("Van Damme");
        element.submit();
        assertTrue(driver.getPageSource().contains("Van Damme"));
        
        String damme_id = driver.findElement(By.linkText("Van Damme")).getAttribute("href")
                .replace(ACTOR_URL,"");
        driver.findElement(By.id("remove-" + damme_id)).submit();
        
        assertFalse(driver.getPageSource().contains("Van Damme"));
        
    }
    
    @Test
    public void addingAndDeletingTwoActors(){
        driver.get(ACTOR_URL);
        WebElement element;
        assertFalse(driver.getPageSource().contains("Van Damme"));
        assertFalse(driver.getPageSource().contains("Chuck Norris"));
      
        element = driver.findElement(By.id("name"));
        element.sendKeys("Chuck Norris");
        element.submit();
        
        assertFalse(driver.getPageSource().contains("Van Damme"));
        assertTrue(driver.getPageSource().contains("Chuck Norris"));
        
        element = driver.findElement(By.id("name"));
        element.sendKeys("Van Damme");
        element.submit();
        
        assertTrue(driver.getPageSource().contains("Chuck Norris"));
        assertTrue(driver.getPageSource().contains("Van Damme"));
        
        String damme_id = driver.findElement(By.linkText("Van Damme")).getAttribute("href")
                .replace(ACTOR_URL,"");
        
        driver.findElement(By.id("remove-" + damme_id)).submit();
        
        String chuck_id = driver.findElement(By.linkText("Chuck Norris")).getAttribute("href")
                .replace(ACTOR_URL, "");
        
        driver.findElement(By.id("remove-" + chuck_id)).submit();
        
            
        assertTrue(driver.getPageSource().contains("Chuck Norris"));
        assertFalse(driver.getPageSource().contains("Van Damme"));
    }
}