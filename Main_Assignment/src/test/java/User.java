import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.File;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class User {
    /*@Test
     public void call(){
        // given().
         //        baseUri("https://api-nodejs-todolist.herokuapp.com").        when().
                 get("https://api-nodejs-todolist.herokuapp.com/#jump-User-RegisterUser").                then().
                 statusCode(200);
               //  contentType("application/json");
     }*/
    @Test(priority = 1)
    public void userRegister() throws IOException {
        RestAssured.useRelaxedHTTPSValidation();
        // File jsonData = new File("src//test//resources//jsondata.json");
        for (int i = 1; i <= 5; i++) {
            excelData ed = new excelData();
            String nam = ed.getString(i, 0);
            String emai = ed.getString(i, 1);
            String pass = ed.getString(i, 2);
            int age = ed.getAge(i, 3);
            data dt = new data(nam, emai, pass, age);
            Response response=given().
                    body(dt).
                    contentType(ContentType.JSON).
                    when().
                    post("https://api-nodejs-todolist.herokuapp.com/user/register").
                    then().
                    log().body().

                    statusCode(HttpStatus.SC_CREATED).extract().response();
           // JSONArray j=new JSONArray(response.asString());
            // Object o = j.getJSONObject(i).get("token");
           // System.out.println(o);
            JSONObject jsonObject = new JSONObject(response.asString());
            Object ObjToken = jsonObject.get("token");
           // System.out.println(ObjToken);
            excelData excelData = new excelData();
            excelData.writeToken(ObjToken,i,4);
            //contentType("application/json");
        }
    }
    @Test(priority = 2)
    public void userLogin() throws IOException {

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
       /* Object ObjToken = jsonObject.get("token");
        excelData excelData = new excelData();
        excelData.writeToken(ObjToken);
        // System.out.println(ObjToken);*/
    }
}