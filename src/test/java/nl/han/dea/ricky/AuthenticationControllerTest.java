package nl.han.dea.ricky;

import nl.han.dea.ricky.controller.AuthenticationController;
import nl.han.dea.ricky.entity.LoginCredentials;
import nl.han.dea.ricky.entity.UserToken;
import nl.han.dea.ricky.exception.LoginException;
import nl.han.dea.ricky.service.ILoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationControllerTest {

    LoginCredentials creds;

    @InjectMocks
    private AuthenticationController sut;

    @Mock
    private ILoginService ILoginServiceMock;

    @Test
    public void testThatAuthenticationControllerReturnsOKResponseWithTokenWhenCredentialsAreCorrect() throws Exception {

        creds = new LoginCredentials("ricky", "yeet");
        UserToken token = new UserToken("1234-1234-1234", "Ricky Broers");


        Mockito.when(ILoginServiceMock.login(creds)).thenReturn(token);
        assertEquals(Response.Status.CREATED.getStatusCode(), sut.authenticate(creds).getStatus());

    }

    @Test
    public void testThatAuthenticationControllerReturnsUNAUTHORIZEDResponseWithTokenWhenCredentialsAreIncorrect() throws Exception {

        creds = new LoginCredentials("ricky", "wrongpassword");
        LoginException loginException = new LoginException("Incorrect authenticate credentials.");

        Mockito.when(ILoginServiceMock.login(creds)).thenThrow(loginException);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), sut.authenticate(creds).getStatus());
    }
}