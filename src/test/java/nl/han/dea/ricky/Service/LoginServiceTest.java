package nl.han.dea.ricky.Service;

import nl.han.dea.ricky.LoginCredentials;
import nl.han.dea.ricky.LoginException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LoginServiceTest {

    private LoginService loginService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        loginService = new LoginService();
    }

    @Test
    public void testLoginWithCorrectCredentials() throws LoginException {
        LoginCredentials creds = new LoginCredentials("Ricky", "yeet");
        loginService.login(creds);
    }

    @Test
    public void testThatLoginThrowsLoginExceptionWhenWrongCredentialsAreEntered() throws Exception {
        thrown.expect(LoginException.class);
        thrown.expectMessage("Incorrect authenticate credentials.");
        LoginCredentials creds = new LoginCredentials("Uwe", "password1234");
        loginService.login(creds);
    }


}