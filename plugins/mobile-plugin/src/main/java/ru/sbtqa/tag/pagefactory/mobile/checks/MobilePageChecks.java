package ru.sbtqa.tag.pagefactory.mobile.checks;

import org.openqa.selenium.WebElement;
import ru.sbtqa.tag.pagefactory.checks.PageChecks;
import ru.sbtqa.tag.qautils.strategies.MatchStrategy;

public class MobilePageChecks implements PageChecks {

    private static final String WHITESPACES = "\\s+";
    private static final String EMPTY_STRING = "";
    private static final String INPUT = "input";
    private static final String VALUE = "value";
    private static final String SELECT = "select";
    private static final String TITLE = "title";

    @Override
    public boolean checkEquality(Object element, String text) {
        WebElement webElement = (WebElement) element;
        return checkEquality(webElement, text, MatchStrategy.EXACT);
    }

    public boolean checkEquality(Object element, String text, MatchStrategy matchStrategy) {
        WebElement webElement = (WebElement) element;
        String value = getWebElementValue(webElement);

        if (matchStrategy == MatchStrategy.EXACT) {
            return text.replaceAll(WHITESPACES, EMPTY_STRING).equals(value.replaceAll(WHITESPACES, EMPTY_STRING));
        } else {
            return value.replaceAll(WHITESPACES, EMPTY_STRING).contains(text.replaceAll(WHITESPACES, EMPTY_STRING));
        }
    }

    private String getWebElementValue(WebElement webElement) {
        switch (webElement.getTagName()) {
            case INPUT:
                return webElement.getAttribute(VALUE);
            case SELECT:
                return webElement.getAttribute(TITLE);
            default:
                return webElement.getText();
        }
    }

    /**
     * Check that given WebElement has a value attribute, and it is not empty
     *
     * @param element an element to check
     */
    @Override
    public boolean checkEmptiness(Object element) {
        WebElement webElement = (WebElement) element;
        String value = getWebElementValue(webElement);
        return "".equals(value) || value.isEmpty();
    }
}
