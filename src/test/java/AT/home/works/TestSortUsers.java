package AT.home.works;
import AT.home.works.config.AppConfig;
import AT.home.works.dto.User;
import AT.home.works.page.LoginPage;
import AT.home.works.page.SortBy;
import AT.home.works.page.UserPage;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v115.performance.Performance;
import org.openqa.selenium.devtools.v115.performance.model.Metric;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@TestInstance(Lifecycle.PER_CLASS)
    public class TestSortUsers {
    private ChromeDriver driver;

    private WebDriverWait wait;

    private AppConfig config;

    private DevTools devTools;

    @BeforeAll
    public void configInit() {
        config = new AppConfig();
    }

    @BeforeEach
    public void init() throws MalformedURLException {
        //   System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        devTools = driver.getDevTools();
        devTools.createSession();
    }

    @AfterEach
    public void tearDown() {

        driver.quit();
    }
    @Test

    public void testGoogleSearch() throws InterruptedException {

            // Optional. If not specified, WebDriver searches the PATH for chromedriver.
           // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
          // WebDriver driver = new ChromeDriver();

            driver.get("http://www.google.com/");

            Thread.sleep(5000);  // Let the user actually see something!

            WebElement searchBox = driver.findElement(By.name("q"));

            searchBox.sendKeys("ChromeDriver");

            searchBox.submit();

            Thread.sleep(5000);  // Let the user actually see something!

        }

    @Test
    public void loginTest() {
        driver.get(config.baseUrl);
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.fillLoginInput(config.userName);
        loginPage.fillPasswordInput(config.userPassword);
        loginPage.submitForm();
        String alertText = loginPage.getAlertText();
        Assertions.assertTrue(alertText.contains("Successful"), "Alert text doesn't contains info about successful auth");
    }

    @Test
    public void sortUsersByLastName_AscendingTest() {
        devTools.send(Performance.enable(Optional.empty()));
        List<Metric> perfMetrics = devTools.send(Performance.getMetrics());

        driver.get(config.baseUrl + "/read/users");
        UserPage userPage = new UserPage(driver, wait);

        sendPerfMetricsToReport(perfMetrics);

        SortBy sortBy = new SortBy(driver, wait);

        //одиночный клик для сортировки Фамилии по возрастанию
        sortBy.sortByLastName();

        List<String> usersLastNames = new ArrayList<>(userPage.parseUsers().stream()
                .map(User::toLastName).toList());

        List<String> userLastNameCopy = new ArrayList<>(usersLastNames);

        Collections.sort(userLastNameCopy);

        Assertions.assertEquals(userLastNameCopy, usersLastNames, "Sorting users by Ascending LastName in users table is incorrect");

    }

    @Attachment(value = "Метрика производительности:", type = "text/html")
    public String sendPerfMetricsToReport(List<Metric> perfMetrics) {

        List<String> perfMetricsStringList = new ArrayList<>();

        for (Metric metric : perfMetrics) {
            perfMetricsStringList.add(metric.getName() + "=" + metric.getValue());
        }

        return String.join("\n", perfMetricsStringList);
    }

}