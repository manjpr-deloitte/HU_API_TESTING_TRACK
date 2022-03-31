import io.restassured.RestAssured;
import org.json.JSONArray;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.io.File;
public class Mini_Assignment_1
{
    @Test
    public void get()
    {
        RestAssured.useRelaxedHTTPSValidation();
        Response response=given().
                header("content-type","application/json").
                when().
                get("https://jsonplaceholder.typicode.com/posts").
                then().
                statusCode(200).log().status().log().headers().extract().response();
                assertThat(response.path("[39].userId"), is(equalTo(4)));
                JSONArray arr = new JSONArray(response.asString());
                int flag = 1;
                for(int i=0;i<arr.length();i++){

                     Object obj = arr.getJSONObject(i).get("title");
                    if( !(obj instanceof String) ) {
                         flag = 0;
                         break;
            }
        }
        assertThat(flag,is(equalTo(1)));
    }
    @Test
    public void put(){
        RestAssured.useRelaxedHTTPSValidation();
        File jasonData = new File("src//test//Resources//putdata.json");
        given().
                body(jasonData).
        when().
                put("https://reqres.in/api/users").
        then().
                statusCode(200).log().status().log().headers().
                contentType("application/json");
    }
}

