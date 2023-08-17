package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.Assert.assertEquals;

public class Main {

    final static String url="http://demo.guru99.com/V4/sinkministatement.php";

    //This will fetch the response body as is and log it. given and when are optional here
    @Test
    public  void getResponseBody(){
        //Gets Response Header
        given().when().get(url).then().log().headers();
        System.out.println("The headers in the response "+
                given().queryParam("CUSTOMER_ID","68195")
                        .queryParam("PASSWORD","1234!")
                        .queryParam("Account_No","1").
                        get(url).
                        then().extract().headers());
        //Get Response Body
        given().queryParam("CUSTOMER_ID","68195")
                .queryParam("PASSWORD","1234!")
                .queryParam("Account_No","1") .when().get(url).then().log().body();
    }
    @Test
    public  void getResponseStatus(){
        //Gets the Status Code which return value of Request then print it
        int statusCode= given().queryParam("CUSTOMER_ID","68195")
                .queryParam("PASSWORD","1234!")
                .queryParam("Account_No","1")
                .when().get(url).getStatusCode();
        System.out.println("The response status is "+statusCode);

        //Get the Status Code which return value of Request then assert with expected value
        given().when().get(url).then().assertThat().statusCode(200);
    }

    @Test
    public  void getResponseTime(){
        //Gets the Response time in milliseconds
        System.out.println("The time taken to fetch the response "+get(url)
                .timeIn(TimeUnit.MILLISECONDS) + " milliseconds");
    }
    @Test
    public  void getResponseContentType(){
        //Gets the Response Content Type(i.e Json,text)
        System.out.println("The content type of response "+
                get(url).then().extract()
                        .contentType());
    }
    @Test
    public  void getSpecificPartOfResponseBody(){

        ArrayList<Integer> ages;
        ages = when().get(url).then().extract().path("data.employee_age");


        int sumOfAll=0;
        for(Integer a :ages){

            System.out.println("The age value fetched is "+a);
            sumOfAll=sumOfAll+ a;
        }
        System.out.println("The total amount of ages is " + sumOfAll);

    }



}
