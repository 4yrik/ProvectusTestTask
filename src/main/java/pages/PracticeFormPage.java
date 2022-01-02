package pages;

import entities.Student;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import values.*;
import java.time.Duration;
import java.util.List;

public class PracticeFormPage {

    private final WebDriver driver;

    @FindBy (id = "firstName")
    private WebElement firstNameInput;

    @FindBy (id = "lastName")
    private WebElement lastNameInput;

    @FindBy (id = "userNumber")
    private WebElement mobileNumberInput;

    @FindBy (css = ".custom-radio")
    private List<WebElement> genderButtons;

    @FindBy (id = "submit")
    private WebElement submitButton;

    @FindBy (xpath = "//div[@role='dialog']")
    private WebElement successSubmitPopup;

    public PracticeFormPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PracticeFormPage submitStudent(Student student){
        return fillFirstName(student.getFirstName())
                .fillLastName(student.getLastName())
                .fillMobileNumber(student.getMobileNumber())
                .chooseGender(student.getGender())
                .clickSubmitButton();
    }

    public PracticeFormPage fillFirstName(FirstName firstName){
        firstNameInput.sendKeys(firstName.getFirstName());
        return this;
    }

    public PracticeFormPage fillLastName(LastName lastName){
        lastNameInput.sendKeys(lastName.getLastName());
        return this;
    }

    public PracticeFormPage chooseGender(Gender gender){
        switch (gender) {
            case MALE -> genderButtons.get(0).click();
            case FEMALE -> genderButtons.get(1).click();
            case OTHER -> genderButtons.get(2).click();
        }
        return this;
    }

    public PracticeFormPage fillMobileNumber(MobileNumber number){
        mobileNumberInput.sendKeys(number.getMobileNumber());
        return this;
    }

    public PracticeFormPage clickSubmitButton(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();
        return this;
    }

    public boolean isSubmitSuccessful(){
        return successSubmitPopup.isDisplayed();
    }

    public boolean isFirstNameAlerted(){
        return isInputAlerted(firstNameInput);
    }

    public boolean isLastNameAlerted(){
        return isInputAlerted(lastNameInput);
    }

    public boolean isMobileNumberAlerted(){
        return isInputAlerted(mobileNumberInput);
    }

    private boolean isInputAlerted(WebElement input){
        try{
            new WebDriverWait(driver, Duration.ofSeconds(1))
                    .until((ExpectedCondition<Boolean>) driver -> input.getCssValue("border-color").equals("rgb(220, 53, 69)"));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean isGenderAlerted(){
        for (WebElement element: genderButtons) {
            String labelColor = element.findElement(By.tagName("label")).getCssValue("color");
            if (!labelColor.equals("rgba(220, 53, 69, 1)")) {
                return false;
            }
        }
        return true;
    }
}
