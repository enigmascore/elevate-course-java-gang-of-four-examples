package com.javaspeak.designpatterns.go4.behavioural.templatemethod;

/**
 * In the Template Method pattern a class such as this one extends the abstract class and provides
 * implementation for the abstract methods.  In the abstract class it is extending there is a
 * method ( buildPage() ) which calls the abstract methods in the correct order.
 *
 * @author John Dickerson - 22 February 2020
 */
public class HomePage extends AbstractHtmlPage {

    /**
     * Creates a HomePage.
     */
    public HomePage() {

    }


    @Override
    public String getTitle() {

        return "My Home Page";
    }


    @Override
    public String getHeader() {

        return """
                <a href="www.google.com">Google</a>""";
    }


    @Override
    public String getContent() {

        return "All knowledge can be had from google";
    }


    @Override
    public String getFooter() {

        return "Design Production";
    }
}
