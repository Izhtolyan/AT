package AT.home.works.apitest;

import AT.home.works.dto.User;
import io.qameta.allure.Description;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static AT.home.works.apitest.API.APIUserMethods.createUser;
import static AT.home.works.apitest.API.APIUserMethods.getUsersList;

public class TestsAPIUsers extends ConfigInitAPITest {

    @Test
    public void respTestUserList() {
        List<User> responseBody;

        Response getUsersResponse = getUsersList();

        responseBody = getUsersResponse
                .then()
                .extract()
                .body()
                .as(new TypeRef<List<User>>() {});

        List<Integer> ids = responseBody.stream()
                .map(User::getId).toList();
        ids.forEach(System.out::println);
    }

    @Test
    @Description("Тест на создание пользователя с невалидными данными, в ответ приходит 400")
    public void Negativ_Test() throws IOException {

        InputStream testData = ConfigInitAPITest.class.getClassLoader().getResourceAsStream("TestUser.json");

        Response createUser = createUser(testData);

        createUser.then()
                .statusCode(400);
    }

}
