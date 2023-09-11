package AT.home.works.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SortBy extends AbstractPage{

    public static final By.ByXPath ID_SORT_BUTTON = new By.ByXPath("//button[@class='btn btn-secondary'][2]");
    public static final By.ByXPath FIRST_NAME_SORT_BUTTON = new By.ByXPath("//button[@class='btn btn-secondary'][3]");
    public static final By.ByXPath LAST_NAME_SORT_BUTTON = new By.ByXPath("//button[@class='btn btn-secondary'][4]");
    public static final By.ByXPath AGE_SORT_BUTTON = new By.ByXPath("//button[@class='btn btn-secondary'][5]");
    public static final By.ByXPath SEX_SORT_BUTTON = new By.ByXPath("//button[@class='btn btn-secondary'][6]");
    public static final By.ByXPath MONEY_SORT_BUTTON = new By.ByXPath("//button[@class='btn btn-secondary'][7]");

    @FindBy(css = "button[class='btn btn-secondary']:nth-child(3)")
    private WebElement ID_Sort_Button;

    @FindBy(css = "button[class='btn btn-secondary']:nth-child(4)")
    private WebElement FirstName_Sort_Button;

    @FindBy(css = "button[class='btn btn-secondary']:nth-child(5)")
    private WebElement LastName_Sort_Button;

    @FindBy(css = "button[class='btn btn-secondary']:nth-child(6)")
    private WebElement Age_Sort_Button;

    @FindBy(css = "button[class='btn btn-secondary']:nth-child(7)")
    private WebElement Sex_Sort_Button;

    @FindBy(css = "button[class='btn btn-secondary']:nth-child(8)")
    private WebElement Money_Sort_Button;

    @FindBy(css = "button[class='btn btn-secondary']:nth-child(8)")
    private WebElement Money_Sort_Button_Double;


    public SortBy(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void sortByLastName(){
        LastName_Sort_Button.click();
    }
}
