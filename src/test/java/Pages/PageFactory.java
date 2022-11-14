package Pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {

    WebDriver driver;

    protected HomePage homePage;
    protected FetchingBookData fetchingBookData;

    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage( driver );
        }
        return homePage;
    }

    public FetchingBookData getFetchingBookData() {
        if (fetchingBookData == null) {
            fetchingBookData = new FetchingBookData( driver );
        }
        return fetchingBookData;
    }
}
