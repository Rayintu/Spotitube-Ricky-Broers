package nl.han.dea.ricky.service;

import nl.han.dea.ricky.LoginCredentials;
import nl.han.dea.ricky.exception.LoginException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LoginServiceTest {

    private ILoginService ILoginService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        ILoginService = new LoginService();
    }

    @Test
    public void testLoginWithCorrectCredentials() throws LoginException {
        LoginCredentials creds = new LoginCredentials("Ricky", "yeet");
        ILoginService.login(creds);
    }

    @Test
    public void testThatLoginThrowsLoginExceptionWhenWrongCredentialsAreEntered() throws Exception {
        thrown.expect(LoginException.class);
        thrown.expectMessage("Incorrect authenticate credentials.");
        LoginCredentials creds = new LoginCredentials("Uwe", "password1234");
        ILoginService.login(creds);
    }


}