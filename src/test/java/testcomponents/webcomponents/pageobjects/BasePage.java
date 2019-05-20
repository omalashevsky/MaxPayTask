package testcomponents.webcomponents.pageobjects;

import com.codeborne.selenide.Configuration;

public abstract class BasePage
{
    private static final String SANDBOX_PAGE_BASE_URL = "https://my-sandbox.maxpay.com";

    public BasePage()
    {
        Configuration.timeout = 7000;
        Configuration.baseUrl = SANDBOX_PAGE_BASE_URL;
    }

    public abstract void goToThisPage();
}
