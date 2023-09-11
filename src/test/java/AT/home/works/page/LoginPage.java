package AT.home.works.page;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage{
    @FindBy(css = "input[name=email]")
    private WebElement loginInput;

    @FindBy(css = "input[name=password]")
    private WebElement passwordInput;

    @FindBy(css = "button[type=submit]")
    private WebElement submitBtn;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Заполнить поле ввода логина")
    public void fillLoginInput(String text){
        loginInput.clear();
        loginInput.sendKeys(text);
    }
    @Step("Заполнить поле ввода пароля")
    public void fillPasswordInput(String text){
        passwordInput.clear();
        passwordInput.sendKeys(text);
    }
    @Step("Нажать кнопку отправить")
    public void submitForm(){
        submitBtn.click();
    }
    private Alert findAlert(){
        return wait.until(ExpectedConditions.alertIsPresent()); //показалось, что так правильнее
        // return wait.until(driver -> driver.switchTo().alert());
    }
    public String getAlertText(){
        return findAlert().getText();
    }

    public void dismissAlert(){
        findAlert().dismiss();
    }
}
