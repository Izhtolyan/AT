package AT.home.works.apitest.Auth;
import io.restassured.filter.FilterContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.spi.AuthFilter;

import static io.restassured.RestAssured.given;

public class Auth implements AuthFilter {
    private final String username;
    private final String password;

    public Auth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx
    ) {
        AT.home.works.dto.Auth.AuthRequest authRequest = new AT.home.works.dto.Auth.AuthRequest(username, password);
        AT.home.works.dto.Auth.AuthResponse authResponse = given().auth().none().and().disableCsrf().and()
                .contentType(ContentType.JSON).body(authRequest)
                .when().post("/login").as(AT.home.works.dto.Auth.AuthResponse.class);
        String token = authResponse.accessToken;
        requestSpec.header("Authorization", "Bearer " + token);
        requestSpec.contentType(ContentType.JSON);

        return ctx.next(requestSpec, responseSpec);
    }


}

