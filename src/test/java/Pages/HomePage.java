package Pages;

import Enums.HomePageEnums;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    String path;

    String bookStoreApplication = "//h5[contains(text(),'%s')]";
    String homePageElements = "//div[@class='%s']";
    String list = "//div[@class='%s']";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait( driver, Duration.ofSeconds( 10 ) );
    }

    public void verifyingHomePage() {
        path = System.getProperty( "user.dir" ) + "//src//test//java//DataDriven//ListData.xlsx";
        FileInputStream prop = null;
        try {
            prop = new FileInputStream( path );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook( prop );
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
        XSSFSheet sheet = wb.getSheet( "Sheet1" );
        String ListValue1 = sheet.getRow( 0 ).getCell( 0 ).getStringCellValue();
        String ListValue2 = sheet.getRow( 0 ).getCell( 1 ).getStringCellValue();
        String ListValue3 = sheet.getRow( 0 ).getCell( 2 ).getStringCellValue();
        String ListValue4 = sheet.getRow( 0 ).getCell( 3 ).getStringCellValue();
        String ListValue5 = sheet.getRow( 0 ).getCell( 4 ).getStringCellValue();
        String ListValue6 = sheet.getRow( 0 ).getCell( 5 ).getStringCellValue();
        List<String> expectedValueInList = new ArrayList<String>();
        expectedValueInList.add( ListValue1 );
        expectedValueInList.add( ListValue2 );
        expectedValueInList.add( ListValue3 );
        expectedValueInList.add( ListValue4 );
        expectedValueInList.add( ListValue5 );
        expectedValueInList.add( ListValue6 );
        List<String> actualValueInList = new ArrayList<>();
        List<WebElement> myElements = driver.findElements( By.xpath( String.format( homePageElements, HomePageEnums.Elements.getValue() ) ) );
        for (WebElement e : myElements) {
            actualValueInList.add( e.getText() );
        }
        Assert.assertEquals( expectedValueInList, actualValueInList, "Expected result doesn't match" );
    }

    public void clickingOnBookStoreApplication() {
        wait.until( ExpectedConditions.elementToBeClickable( By.xpath( String.format( bookStoreApplication, HomePageEnums.toolsCategory.getValue() ) ) ) );
        WebElement element = driver.findElement( By.xpath( String.format( bookStoreApplication, HomePageEnums.toolsCategory.getValue() ) ) );
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript( "arguments[0].click();", element );
    }

    public void verifyBookStorePage() {
        path = System.getProperty( "user.dir" ) + "//src//test//java//DataDriven//ListData.xlsx";
        FileInputStream prop = null;
        try {
            prop = new FileInputStream( path );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook( prop );
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
        XSSFSheet sheet = wb.getSheet( "Sheet2" );
        String expectedHeading = sheet.getRow( 0 ).getCell( 0 ).getStringCellValue();
        String actualHeading = driver.findElement( By.xpath( String.format( list, HomePageEnums.ITEMS.getValue() ) ) ).getText();
        Assert.assertEquals( actualHeading, expectedHeading, "Expected does not match" );
    }
}