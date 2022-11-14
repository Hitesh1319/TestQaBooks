package Pages;

import Enums.HomePageEnums;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class FetchingBookData {
    WebDriver driver;

    public FetchingBookData(WebDriver driver) {
        this.driver = driver;
    }

    By gitPocketGuide = By.xpath( "//div[@class='action-buttons']//span[@id='see-book-Git Pocket Guide']//a" );
    String author = "//div[contains(text(),'%s')]";

    public void fetchingValueFromWebsite() {
        RestAssured.baseURI = "https://demoqa.com";
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.get( "/BookStore/v1/Books" );
        ResponseBody responseBody = response.getBody();
        String responseBod = responseBody.asString();
        JsonElement fileElement = JsonParser.parseString( responseBod );
        JsonObject fileObject = fileElement.getAsJsonObject();
        JsonArray jsonArrayOfBooks = fileObject.get( "books" ).getAsJsonArray();
        List<PojoClass> books = new ArrayList<>();
        for (JsonElement booksElement : jsonArrayOfBooks.getAsJsonArray()) {
            JsonObject JsonObject = booksElement.getAsJsonObject();
            PojoClass bookData = new PojoClass( JsonObject.get( "title" ).getAsString(), JsonObject.get( "author" ).getAsString(), JsonObject.get( "publisher" ).getAsString() );
            books.add( bookData );
        }
        System.out.println( books );
        PojoClass pojoClass = books.get( 0 );
        String titleExpected = pojoClass.getTitle();
        String titleActual = driver.findElement( gitPocketGuide ).getText();
        Assert.assertEquals( titleActual, titleExpected, "Expected condition does not match" );
        String authorExpected = pojoClass.getAuthor();
        String authorActual = driver.findElement( By.xpath( String.format( author, HomePageEnums.Richard.getValue() ) ) ).getText();
        Assert.assertEquals( authorActual, authorExpected, "Expected condition does not match" );
    }
}