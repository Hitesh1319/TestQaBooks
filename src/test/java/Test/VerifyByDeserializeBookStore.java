package Test;

import org.testng.annotations.Test;

public class VerifyByDeserializeBookStore extends BaseClass{

    @Test
    public void test1() {
        pageFactory.getHomePage().verifyingHomePage();
        pageFactory.getHomePage().clickingOnBookStoreApplication();
        pageFactory.getHomePage().verifyBookStorePage();
        pageFactory.getFetchingBookData().fetchingValueFromWebsite();
    }
}