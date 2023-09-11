package AT.home.works.apitest.API;

import AT.home.works.dto.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.InputStream;

import static io.restassured.RestAssured.given;
public class APIUserMethods {
    @Step("Создать пользователя {user.id} {user.firstName} {user.secondName}")
    public static Response createUser(User user){

        return given()
                .body(user)
                .when()
                .post("/user");
    }

    @Step("Создать пользователя, отправив в тело Json")
    public static Response createUser(InputStream inputStream){

        return given()
                .body(inputStream)
                .when()
                .post("/user");
    }

    @Step("Получить список пользователей")
    public static Response getUsersList(){

        Response response = given()
                .when()
                .get("/users");

        return response;
    }
}