package eventlog;

import event.logging.AuthenticateAction;
import event.logging.AuthenticateEventAction;
import event.logging.EventLoggingService;
import event.logging.User;

public class Main1 {

//    https://github.com/gchq/event-logging

    
    public static void main(String[] args) {

        String userId = "Mina";
        // Create the logging service
        final EventLoggingService eventLoggingService = new CustomEventLoggingService();

        // Log some work that produces a result. This will make use of createEvent for common event values.
        final UserAccount userAccount = eventLoggingService.loggedResult(
                "LOGON",
                "User " + userId + " logged on",
                AuthenticateEventAction.builder()
                        .withAction(AuthenticateAction.LOGON)
                        .withUser(User.builder()
                                .withId(userId)
                                .build())
                        .build(),
        () -> {
            // Perform authentication and logon

            // An exception here will cause an unsuccessful logon event to be logged
            // then the original exception will be re-thrown.

            UserAccount account = performLogon();

            return account;
        });

    }

    private static UserAccount performLogon() {
        return null;
    }
}
