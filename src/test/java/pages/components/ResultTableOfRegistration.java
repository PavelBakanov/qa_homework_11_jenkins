package pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class ResultTableOfRegistration {
    public void checkResultOfRegistration(String locator, String key, String value)
    {
        $(locator).$(byTagAndText("td", key)).sibling(0).shouldHave(text(value));
    }

    public void checkNegativeResult(String locator)
    {
        $(locator).shouldNotBe(visible);
    }

}
