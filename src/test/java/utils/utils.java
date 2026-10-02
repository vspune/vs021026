package utils;
import java.io.FileOutputStream;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.io.PrintStream;
import java.security.PublicKey;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;
import java.util.stream.Stream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class utils {

    public static RequestSpecification req;
    public static ResponseSpecification res;

    public RequestSpecification RequestSpecification() {

        try {

            PrintStream log = new PrintStream(
                    new FileOutputStream("logging.txt", true));

            req = new RequestSpecBuilder()
                    .setBaseUri(getglobal("baseurl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON)
                    .build();
            return req;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResponseSpecification responseSpecification() {

        try {

            res = new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectContentType(ContentType.JSON)
                    .build();

            return res;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getglobal(String key) throws IOException {

        Properties prop = new Properties();

        InputStream fis = utils.class
                .getClassLoader()
                .getResourceAsStream("global.properties");

        if (fis == null) {
            throw new IOException("global.properties not found");
        }

        prop.load(fis);

        return prop.getProperty(key);
    }
    
    public String getJsonPath(Response response, String key) {

        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.getString(key);
    }
    
    


}


