package web.functional.components.pageobjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import web.functional.components.users.SimpleUser;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SandboxSignUpPage extends BasePage
{
    private static final String URL = "/#/signup";
    private By enterNewLoginBox = By.id("email");
    private By enterNewPasswordBox = By.id("password");
    private By confirmNewPasswordBox = By.id("confirm");
    private By acceptTerms = By.xpath("//input[@name='agreeTos']/following-sibling::span[position()=1]");
    private By registerButton = By.xpath("//button[@type='submit']");

    @Override
    public void goToThisPage()
    {
        open(URL);
    }

    public SimpleUser createNewRandomUser()
    {
        MaxPayDashboard maxPayDashboard = new MaxPayDashboard();
        SimpleUser simpleUser = new SimpleUser(SimpleUser.generateCorrectLogin(), SimpleUser.generateCorrectPassword());
        goToThisPage();
        enterNewUserName(simpleUser.getLogin());
        enterNewPassword(simpleUser.getPassword());
        confirmNewPassword(simpleUser.getPassword());
        acceptTermsAndConditions();
        clickOnRegisterButton();
        maxPayDashboard.verifyConfirmationWindowShouldBeCondition(Condition.visible, true);

        return simpleUser;
    }

    public void enterNewUserName(String userName)
    {
        $(enterNewLoginBox).setValue(userName);
    }

    public void enterNewPassword(String password)
    {
        $(enterNewPasswordBox).setValue(password);
    }

    public void confirmNewPassword(String password)
    {
        $(confirmNewPasswordBox).setValue(password);
    }

    public void acceptTermsAndConditions()
    {
        $(acceptTerms).click();
    }

    public void clickOnRegisterButton()
    {
        $(registerButton).click();
    }
}
