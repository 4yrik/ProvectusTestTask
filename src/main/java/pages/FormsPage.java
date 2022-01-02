package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class FormsPage {

    private final WebDriver driver;

    @FindBy (css = "div.show #item-0")
    private WebElement practiceFormButton;

    public FormsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PracticeFormPage navigateToPracticeForm(){
        practiceFormButton.click();
        return new PracticeFormPage(driver);
    }
}
