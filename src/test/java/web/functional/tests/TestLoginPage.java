package web.functional.tests;

import com.codeborne.selenide.Condition;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.functional.components.pageobjects.MaxPayDashboard;
import web.functional.components.pageobjects.SandboxLoginPage;
import web.functional.components.pageobjects.SandboxSignUpPage;
import web.functional.components.users.SimpleUser;

public class TestLoginPage
{
    private SandboxLoginPage sandboxLoginPage = new SandboxLoginPage();
    private SandboxSignUpPage sandboxSignUpPage = new SandboxSignUpPage();
    private MaxPayDashboard maxPayDashboard = new MaxPayDashboard();
    private SimpleUser simpleUser;

    @BeforeClass
    public void createUser()
    {
        simpleUser = sandboxSignUpPage.createNewRandomUser();
    }

    @Test
    public void verifyUserCanLoginSuccess()
    {
        sandboxLoginPage.goToThisPage();
        sandboxLoginPage.enterLogin(simpleUser.getLogin());
        sandboxLoginPage.enterPassword(simpleUser.getPassword());
        sandboxLoginPage.clickOnEnterButton();
        maxPayDashboard.verifyConfirmationWindowShouldBeCondition(Condition.visible, true);
    }

    @Test
    public void verifyEmailAddressIsMandatoryError()
    {
        String mailError = "Пожалуйста, введите действующий email адрес";
        sandboxLoginPage.goToThisPage();
        sandboxLoginPage.clearLoginBox();
        sandboxLoginPage.enterPassword(simpleUser.getPassword());
        sandboxLoginPage.clickOnEnterButton();
        sandboxLoginPage.verifyLoginErrorElementShouldHaveCondition(Condition.text(mailError), true);
        sandboxLoginPage.verifyPasswordErrorElementShouldBeCondition(Condition.visible, false);
    }

    @Test
    public void verifyPasswordIsMandatoryError()
    {
        String passwordError = "Пожалуйста, введите пароль";
        sandboxLoginPage.goToThisPage();
        sandboxLoginPage.clearPasswordBox();
        sandboxLoginPage.enterLogin(simpleUser.getLogin());
        sandboxLoginPage.clickOnEnterButton();
        sandboxLoginPage.verifyPasswordErrorElementShouldHaveCondition(Condition.text(passwordError), true);
        sandboxLoginPage.verifyLoginErrorElementShouldBeCondition(Condition.visible, false);
    }

    @Test(dataProvider = "provideDataForTestingIncorrectLoginPassword")
    public void verifyIncorrectLoginPasswordError(String login, String password)
    {
        String expectedErrorMessage = "Некорректны пароль или email";
        sandboxLoginPage.goToThisPage();
        sandboxLoginPage.enterLogin(login);
        sandboxLoginPage.enterPassword(password);
        sandboxLoginPage.clickOnEnterButton();
        sandboxLoginPage.verifyIncorrectPassLoginShouldHaveCondition(Condition.text(expectedErrorMessage), true);
    }

    @DataProvider(name = "provideDataForTestingIncorrectLoginPassword")
    public Object[][] provideLoginPassword()
    {
        return new Object[][]
                {
                        {simpleUser.getLogin(), SimpleUser.generateCorrectPassword()},
                        {SimpleUser.generateCorrectLogin(), simpleUser.getPassword()},
                };
    }
}
