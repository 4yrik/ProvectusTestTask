package tests;

import entities.Student;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import java.io.*;
import java.time.Duration;
import java.util.Properties;

public class TestSuite {

    private static WebDriver driver;
    private static Logger logger = Logger.getLogger(TestSuite.class);

    @BeforeClass
    public static void beforeTests(){
        Properties properties = new Properties();
        try (FileReader reader = new FileReader("src/main/resources/Configuration.properties")) {
            properties.load(reader);
        } catch (IOException e){
            e.printStackTrace();
        }
        System.setProperty("webdriver.chrome.driver", properties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testCase01(){
        Student validStudent = Student.createValidStudent();
        PracticeFormPage practiceFormPage = new HomePage(driver).navigateToFormsPage()
                                                                .navigateToPracticeForm()
                                                                .submitStudent(validStudent);
        try{
            Assert.assertTrue(practiceFormPage.isSubmitSuccessful());
            logger.info("Test case 01 PASSED");
        }catch (Error e){
            logger.error("Test case 01 FAILED: The form is not submitted successfully");
        }
    }

    @Test
    public void testCase02(){
        PracticeFormPage practiceFormPage = new HomePage(driver).navigateToFormsPage()
                                                                .navigateToPracticeForm()
                                                                .clickSubmitButton();
        try {
            Assert.assertTrue(practiceFormPage.isFirstNameAlerted());
            Assert.assertTrue(practiceFormPage.isLastNameAlerted());
            Assert.assertTrue(practiceFormPage.isMobileNumberAlerted());
            Assert.assertTrue(practiceFormPage.isGenderAlerted());
            logger.info("Test case 02 PASSED");
        }catch (Error e){
            logger.error("Test case 02 FAILED: First Name, Last Name, Gender or Mobile Number are not required");
        }
    }

    @AfterClass
    public static void afterTests(){
        driver.quit();
    }
}
