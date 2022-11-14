package Pages;

import Enums.HomePageEnums;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    String bookStoreApplication = "//h5[contains(text(),'%s')]";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void verifyingHomePage() {
        List<WebElement> myElements = driver.findElements(By.xpath(String.format(bookStoreApplication, HomePageEnums.toolsCategory.getValue())));
        for (WebElement e : myElements) {
            System.out.println(e.getText());
        }
    }

    public void clickingOnBookStoreApplication() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(bookStoreApplication, HomePageEnums.toolsCategory.getValue()))));
        WebElement element = driver.findElement(By.xpath(String.format(bookStoreApplication, HomePageEnums.toolsCategory.getValue())));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void fetchingValueFromWebsite() {
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.get("https://demoqa.com/BookStore/v1/Books");
        ResponseBody responseBody = response.getBody();
        String responseBod = responseBody.asString();
        JsonPath jsonPath = response.jsonPath();
        String s = jsonPath.get("books").toString();
        JsonElement fileElement = JsonParser.parseString(responseBod);
        JsonObject fileObject = fileElement.getAsJsonObject();
        JsonArray jsonArrayOfBooks = fileObject.get("books").getAsJsonArray();
        ArrayList<PojoClass> books = new ArrayList<>();
        for (JsonElement booksElement : jsonArrayOfBooks.getAsJsonArray()) {
            JsonObject JsonObject = booksElement.getAsJsonObject();
            PojoClass bookData = new PojoClass( JsonObject.get( "title" ).toString(), JsonObject.get( "author" ).toString(), JsonObject.get( "publisher" ).toString() );
            books.add( bookData );
        }
        System.out.println(books);
    }
}