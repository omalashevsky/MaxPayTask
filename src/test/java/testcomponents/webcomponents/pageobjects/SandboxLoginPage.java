package testcomponents.webcomponents.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SandboxLoginPage extends BasePage
{
    private final String url = Configuration.baseUrl.concat("/#/signin");
    private By enterLoginBox = By.id("login-email");
    private By enterPasswordBox = By.id("login-password");
    private By enterButton = By.xpath("//button[@type='submit']");
    private By emailError = By.id("login-email-error");
    private By passwordError = By.id("login-password-error");
    private By wrongMailPasswordAlert = By.className("col-xs-12");

    @Override
    public void goToThisPage()
    {
        open(url);
    }

    public void enterLogin(String login)
    {
        $(enterLoginBox).waitUntil(Condition.enabled, 3000).setValue(login);
    }

    public void enterPassword(String password)
    {
        $(enterPasswordBox).waitUntil(Condition.enabled, 3000).setValue(password);
    }

    public void clickOnEnterButton()
    {
        $(enterButton).click();
    }

    public String loginErrorText()
    {
        return $(emailError).getText();
    }

    public String passwordErrorText()
    {
        return $(passwordError).getText();
    }

    public void verifyPasswordErrorElementCondition(Condition condition, boolean shouldBe)
    {
        verifyElementShouldBeCondition(passwordError, condition, shouldBe);
    }

    public void verifyPasswordErrorShouldHaveElementCondition(Condition condition, boolean shouldHave)
    {
        verifyElementShouldHaveCondition(passwordError, condition, shouldHave);
    }

    public void verifyLoginErrorElementCondition(Condition condition, boolean shouldBe)
    {
        verifyElementShouldBeCondition(emailError, condition, shouldBe);
    }

    public void verifyLoginErrorShouldHaveElementCondition(Condition condition, boolean shouldHave)
    {
        verifyElementShouldHaveCondition(emailError, condition, shouldHave);
    }

    private void verifyElementShouldBeCondition(By by, Condition condition, boolean shouldBe)
    {
        SelenideElement selenideElement = $(by);
        if(shouldBe)
            selenideElement.shouldBe(condition);
        else
            selenideElement.shouldNotBe(condition);
    }

    private void verifyElementShouldHaveCondition(By by, Condition condition, boolean shouldHave)
    {
        SelenideElement selenideElement = $(by);
        if(shouldHave)
            selenideElement.shouldHave(condition);
        else
            selenideElement.shouldNotHave(condition);
    }

}
