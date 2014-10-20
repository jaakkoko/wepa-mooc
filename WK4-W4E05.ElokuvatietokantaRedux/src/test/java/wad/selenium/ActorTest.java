package wad.selenium;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.SpringApplication;
import wad.Application;


public class ActorTest{
    
    private WebDriver driver;

    private final String ACTOR_URL = "http://localhost:8080/actors";
    
    @Before
    public void setUp() {
        // käynnistää Spring-sovelluksen -- JSP-kirjastojen lataaminen
        // tällä hetkellä kuitenkin buginen, joten ajetaan testit 
        // manuaalisesti käynnistettyyn sovellukseen

        //SpringApplication.run(Application.class);
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
        
       
        
    }
}