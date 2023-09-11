package AT.home.works.apitest;
import AT.home.works.apitest.Auth.Auth;
import AT.home.works.config.AppConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

public class ConfigInitAPITest {

    protected static AppConfig config;

    @BeforeAll
    public static void configInit() {
        config = new AppConfig();
        RestAssured.baseURI = config.apiUrl;
        RestAssured.filters(
                new Auth(config.userName, config.userPassword),
                new AllureRestAssured(),
                new RequestLoggingFilter(),
                new ResponseLoggingFilter());
    }
}