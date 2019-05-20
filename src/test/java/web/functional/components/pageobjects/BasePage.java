package web.functional.components.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage
{
    private static final String SANDBOX_PAGE_BASE_URL = "https://my-sandbox.maxpay.com";

    public BasePage()
    {
        Configuration.timeout = 7000;
        Configuration.baseUrl = SANDBOX_PAGE_BASE_URL;
    }

    public abstract void goToThisPage();

    protected void verifyElementShouldBeCondition(By by, Condition condition, boolean shouldBe)
    {
        SelenideElement selenideElement = $(by);
        if(shouldBe)
            selenideElement.shouldBe(condition);
        else
            selenideElement.shouldNotBe(condition);
    }

    protected void verifyElementShouldHaveCondition(By by, Condition condition, boolean shouldHave)
    {
        SelenideElement selenideElement = $(by);
        if(shouldHave)
            selenideElement.shouldHave(condition);
        else
            selenideElement.shouldNotHave(condition);
    }
}
