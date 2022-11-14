package Enums;

public enum HomePageEnums {
    toolsCategory("Book Store Application"),
    Richard("Richard E. Silverman"),
    Elements("card mt-4 top-card"),
    ITEMS("main-header");

    private String value;

    HomePageEnums(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}