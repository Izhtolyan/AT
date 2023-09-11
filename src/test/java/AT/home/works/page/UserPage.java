package AT.home.works.page;

import java.util.ArrayList;
import java.util.List;

import AT.home.works.dto.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserPage extends AbstractPage {
    @FindBy(css = "table tbody tr")
    private List<WebElement> userRows;

    @FindBy(css = "#root table")
    private WebElement vanillaUsersTable; //user table for vanilla Selenium example

    private static final int ID_COL = 0;
    private static final int FIRST_NAME_COL = 1;
    private static final int LAST_NAME_COL = 2;
    private static final int AGE_COL = 3;
    private static final int SEX_COL = 4;
    private static final int MONEY_COL = 5;

    public UserPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private List<WebElement> findUsersTrs() {
        return vanillaUsersTable.findElements(By.cssSelector("tbody tr"));
    }

    public List<User> parseUsers() {
        List<WebElement> usersTrs = findUsersTrs();
        List<User> userList = new ArrayList<>();
        for (WebElement usersTr : usersTrs) {
            WebElement idColumn = usersTr.findElements(By.cssSelector("td")).get(ID_COL);
            WebElement firstNameColumn = usersTr.findElements(By.cssSelector("td")).get(FIRST_NAME_COL);
            WebElement lastNameColumn = usersTr.findElements(By.cssSelector("td")).get(LAST_NAME_COL);
            WebElement ageColumn = usersTr.findElements(By.cssSelector("td")).get(AGE_COL);
            WebElement sexColumn = usersTr.findElements(By.cssSelector("td")).get(SEX_COL);
            WebElement moneyColumn = usersTr.findElements(By.cssSelector("td")).get(MONEY_COL);

            Integer id = Integer.parseInt(idColumn.getText());
            Integer age = Integer.parseInt(ageColumn.getText());
            Double money = Double.parseDouble(moneyColumn.getText());

            User user = new User(id, firstNameColumn.getText(),lastNameColumn.getText(),  money, age, User.Sex.valueOf(sexColumn.getText()));
            userList.add(user);
        }
        return userList;
    }
}