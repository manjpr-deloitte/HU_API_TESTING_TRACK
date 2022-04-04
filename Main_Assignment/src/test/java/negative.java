import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
public class negative {
    @Test(priority = 1)
    public void invalid() throws IOException {
        RestAssured.useRelaxedHTTPSValidation();
        excelData ed = new excelData();
        String emai = ed.getString(1, 1);
        String pass = ed.getString(1, 2);
        data dt = new data(emai, pass);
        Response response = given().
                body(dt).
                contentType(ContentType.JSON).
                when().
                post("https://api-nodejs-todolist.herokuapp.com/user/login").
                then().
                log().body().
                statusCode(HttpStatus.SC_OK).extract().response();
        JSONObject jsonObject = new JSONObject(response.asString());
        //System.out.println(jsonObject.getJSONObject("user").get("email"));
        Object obj = jsonObject.getJSONObject("user").get("email");
        assertThat(obj, is(emai));
      /*  Object ObjToken = jsonObject.get("token");
        excelData excelData = new excelData();
        excelData.writeToken(ObjToken);
        // System.out.println(ObjToken); */
    }
}
