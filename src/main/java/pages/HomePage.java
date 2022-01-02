package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import java.util.List;

public class HomePage {

    private final WebDriver driver;

    @FindBy (css = ".category-cards .card")
    private List<WebElement> categories;

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.driver.get("https://demoqa.com/");
        PageFactory.initElements(driver, this);
    }

    public FormsPage navigateToFormsPage(){
        categories.get(1).click();
        return new FormsPage(driver);
    }
}
