package web.functional.components.pageobjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SandboxLoginPage extends BasePage
{
    private static final String URL = "/#/signin";
    private By enterLoginBox = By.id("login-email");
    private By enterPasswordBox = By.id("login-password");
    private By enterButton = By.xpath("//button[@type='submit']");
    private By emailErrorElement = By.id("login-email-error");
    private By passwordErrorElement = By.id("login-password-error");
    private By incorrectMailPasswordErrorBox = By.xpath("//div[@class='form-group']//div[@class='col-xs-12']//p");

    @Override
    public void goToThisPage()
    {
        open(URL);
    }

    public void enterLogin(String login)
    {
        $(enterLoginBox).setValue(login);
    }

    public void enterPassword(String password)
    {
        $(enterPasswordBox).setValue(password);
    }

    public void clickOnEnterButton()
    {
        $(enterButton).click();
    }

    public String loginErrorText()
    {
        return $(emailErrorElement).getText();
    }

    public String passwordErrorText()
    {
        return $(passwordErrorElement).getText();
    }

    public void clearPasswordBox()
    {
        $(enterPasswordBox).clear();
    }

    public void clearLoginBox()
    {
        $(enterLoginBox).clear();
    }

    public void verifyIncorrectPassLoginShouldHaveCondition(Condition condition, boolean shouldHave)
    {
        super.verifyElementShouldHaveCondition(incorrectMailPasswordErrorBox, condition, shouldHave);
    }

    public void verifyPasswordErrorElementShouldBeCondition(Condition condition, boolean shouldBe)
    {
        super.verifyElementShouldBeCondition(passwordErrorElement, condition, shouldBe);
    }

    public void verifyPasswordErrorElementShouldHaveCondition(Condition condition, boolean shouldHave)
    {
        super.verifyElementShouldHaveCondition(passwordErrorElement, condition, shouldHave);
    }

    public void verifyLoginErrorElementShouldBeCondition(Condition condition, boolean shouldBe)
    {
        super.verifyElementShouldBeCondition(emailErrorElement, condition, shouldBe);
    }

    public void verifyLoginErrorElementShouldHaveCondition(Condition condition, boolean shouldHave)
    {
        super.verifyElementShouldHaveCondition(emailErrorElement, condition, shouldHave);
    }

}
