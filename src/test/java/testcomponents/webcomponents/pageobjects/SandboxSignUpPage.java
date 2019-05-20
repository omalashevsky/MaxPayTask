package testcomponents.webcomponents.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import testcomponents.webcomponents.users.SimpleUser;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SandboxSignUpPage extends BasePage
{
    private final String url = Configuration.baseUrl.concat("/#/signup");
    private By enterLoginBox = By.id("email");
    private By enterPasswordBox = By.id("password");
    private By confirmPasswordBox = By.id("confirm");
    private By acceptTerms = By.xpath("//input[@name='agreeTos']/following-sibling::span[position()=1]");
    private By registerButton = By.xpath("//button[@type='submit']");
    private By confirmationWindow = By.id("gdpr-email-modal");

    @Override
    public void goToThisPage()
    {
        open(url);
    }

    public SimpleUser createUserByMailPass(String userName, String password)
    {
        goToThisPage();
        enterUserName(userName);
        enterPassword(password);
        confirmPassword(password);
        acceptTermsAndConditions();
        clickOnRegisterButton();
        $(confirmationWindow).waitUntil(Condition.visible, 8000);

        return new SimpleUser(userName, password);

    }

    public void enterUserName(String userName)
    {
        $(enterLoginBox).setValue(userName);
    }

    public void enterPassword(String password)
    {
        $(enterPasswordBox).setValue(password);
    }

    public void confirmPassword(String password)
    {
        $(confirmPasswordBox).setValue(password);
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
