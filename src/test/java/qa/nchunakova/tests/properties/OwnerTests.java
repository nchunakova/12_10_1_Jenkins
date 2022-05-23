package qa.nchunakova.tests.properties;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qa.nchunakova.config.CredentialsConfig;

public class OwnerTests {
    CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

    @Test
    @Tag("owner")
    void loginTest() {
        String login = config.login();
        String password = config.password();

        System.out.println("Login: " + login);
        System.out.println("Password: " + password);

        String message = "I logged in as " + login + " with password " + password;
        System.out.println(message);
    }
}
