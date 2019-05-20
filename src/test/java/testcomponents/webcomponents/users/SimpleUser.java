package testcomponents.webcomponents.users;

import org.apache.commons.lang3.RandomStringUtils;

public class SimpleUser
{
    private String login;
    private String password;

    public SimpleUser(String login, String password)
    {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public static String generateCorrectLogin()
    {
        return new StringBuilder("qa")
            .append("Test")
            .append(RandomStringUtils.randomAlphabetic(5))
            .append("@maxpay.com")
            .toString();
    }

    public static String generateCorrectPassword()
    {
        return new StringBuilder("!")
                .append("Test")
                .append(RandomStringUtils.randomAlphanumeric(8))
                .append(2)
                .toString();
    }
}
