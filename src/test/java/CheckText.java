import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CheckText {
    private final static String URL = "https://reqres.in/";
    private final String EXPECTED_SUPPORT_TEXT = "To keep ReqRes free, contributions towards server costs are appreciated!";

    @Test
    public void checkText() {
        Specification.installSpecification(Specification.requestSpecification(URL),
                Specification.responseSpecification(200));
        Support support = given()
                .when()
                .get("/api/unknown/2")
                .then().log().all()
                .extract().body().jsonPath().getObject("support", Support.class);
        Assert.assertEquals(support.getText(), EXPECTED_SUPPORT_TEXT);

    }
}
