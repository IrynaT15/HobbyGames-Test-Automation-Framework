package api;

import by.hobbygames.api.*;
import org.junit.jupiter.api.*;

public class LoginTest {
    @Test
    public void testLogin() {
        UserAuthService userAuthService = new UserAuthService();
        userAuthService.doRequest("dsgsvhc", "skdjnksvn", "email");
        userAuthService.printResponse();

        Assertions.assertEquals(200, userAuthService.getStatusCode());
    }
}
