package Enums;

public enum HomePageEnums {
    toolsCategory("Book Store Application");

    private String title;

    HomePageEnums(String title) {
        this.title = title;
    }

    public String getValue() {
        return title;
    }
}