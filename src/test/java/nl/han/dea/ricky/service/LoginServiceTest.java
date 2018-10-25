package nl.han.dea.ricky.service;

import nl.han.dea.ricky.exception.LoginException;
import nl.han.dea.ricky.persistence.IAccountDAO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;

    @Mock
    private IAccountDAO accountDAOMock;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testLoginWithCorrectCredentials() throws LoginException {
        Mockito.when(accountDAOMock.login(Mockito.any())).thenReturn(true);
        loginService.login(null);
    }

    @Test
    public void testThatLoginThrowsLoginExceptionWhenWrongCredentialsAreEntered() throws Exception {
        thrown.expect(LoginException.class);
        thrown.expectMessage("Incorrect authenticate credentials.");
        Mockito.when(accountDAOMock.login(Mockito.any())).thenReturn(false);
        loginService.login(null);
    }


}