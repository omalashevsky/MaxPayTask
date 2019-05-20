package webfunctionaltests;

import testcomponents.webcomponents.pageobjects.SandboxLoginPage;
import testcomponents.webcomponents.pageobjects.SandboxSignUpPage;
import com.codeborne.selenide.Condition;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testcomponents.webcomponents.users.SimpleUser;

public class TestLoginPage
{
    private SandboxLoginPage sandboxLoginPage = new SandboxLoginPage();
    private SandboxSignUpPage sandboxSignUpPage = new SandboxSignUpPage();
    private SimpleUser simpleUser;

    @BeforeClass
    public void createUser()
    {
        simpleUser = sandboxSignUpPage.createUserByMailPass(SimpleUser.generateCorrectLogin(), SimpleUser.generateCorrectPassword());
    }

    @Test
    public void verifyUserCanLoginSuccess()
    {
        sandboxLoginPage.goToThisPage();
        sandboxLoginPage.enterLogin(simpleUser.getLogin());
        sandboxLoginPage.enterPassword(simpleUser.getPassword());
        sandboxLoginPage.clickOnEnterButton();
    }

    @Test
    public void verifyEmailAddressIsMandatoryError()
    {
        String mailError = "Пожалуйста, введите действующий email адрес";

        sandboxLoginPage.goToThisPage();
        sandboxLoginPage.enterPassword(simpleUser.getPassword());
        sandboxLoginPage.clickOnEnterButton();
        sandboxLoginPage.verifyLoginErrorShouldHaveElementCondition(Condition.text(mailError), true);
        sandboxLoginPage.verifyPasswordErrorElementCondition(Condition.visible, false);
    }

    @Test
    public void verifyPasswordIsMandatoryError()
    {
        String passwordError = "Пожалуйста, введите пароль";

        sandboxLoginPage.goToThisPage();
        sandboxLoginPage.enterLogin(simpleUser.getLogin());
        sandboxLoginPage.clickOnEnterButton();
        sandboxLoginPage.verifyPasswordErrorShouldHaveElementCondition(Condition.text(passwordError), true);
        sandboxLoginPage.verifyLoginErrorElementCondition(Condition.visible, false);
    }

    @Test
    public void verifyIncorrectLoginError()
    {

    }

    @Test
    public void verifyIncorrectPasswordError()
    {

    }
}
