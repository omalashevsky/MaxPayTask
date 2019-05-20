package web.functional.components.pageobjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.open;


public class MaxPayDashboard extends BasePage
{
    private final String URL = "/app.php#/app/dashboard";
    private By confirmationWindow = By.id("gdpr-email-modal");

    @Override
    public void goToThisPage()
    {
        open(URL);
    }

    public void verifyConfirmationWindowShouldBeCondition(Condition condition, boolean shouldBe)
    {
        super.verifyElementShouldBeCondition(confirmationWindow, condition, shouldBe);
    }
}
