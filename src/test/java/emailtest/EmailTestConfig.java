package emailtest;

public class EmailTestConfig {

    public static enum Browser {Firefox, Chrome, Chromium, Edge, Opera, IE}

    ;

    private static Browser browser = Browser.Firefox;

    private static String serviceName = "Zoho";
    private static String homeURL = "https://zoho.com/";
    private static String login = "turboboost4123";
    private static String password = "mX723QfeAuzgL8N";

    private static String toEmail = "somebody@example.com";
    private static String messageBody = "Hello!";

    private static String emptySubject = "(No Subject)";


    public static Browser getBrowser() {
        return browser;
    }

    public static String getServiceName() {
        return serviceName;
    }

    public static String getHomeURL() {
        return homeURL;
    }

    public static String getLogin() {
        return login;
    }

    public static String getPassword() {
        return password;
    }

    public static String getToEmail() {
        return toEmail;
    }

    public static String getMessageBody() {
        return messageBody;
    }

    public static String getSubject()
    {
        return emptySubject;
    }

}
