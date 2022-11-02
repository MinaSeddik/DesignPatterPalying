package eventlog;

import event.logging.*;
import event.logging.impl.DefaultEventLoggingService;

import java.util.Date;

public  class CustomEventLoggingService extends DefaultEventLoggingService {

    @Override
    public Event createEvent(final String typeId,
                             final String description,
                             final Purpose purpose,
                             final EventAction eventAction) {
        return Event.builder()
                .withEventTime(EventTime.builder()
                        .withTimeCreated(new Date())
                        .build())
                .withEventSource(EventSource.builder()
                        .withSystem(SystemDetail.builder()
                                .withName("My System Name")
                                .withEnvironment("Test")
                                .withVersion(getBuildVersion())
                                .build())
                        .withGenerator("CustomEventLoggingService")
                        .withClient(getClientDevice())
                        .withDevice(getThisDevice())
                        .withUser(User.builder()
                                .withId(getLoggedInUserId())
                                .build())
                        .build())
                .withEventDetail(EventDetail.builder()
                        .withTypeId(typeId)
                        .withDescription(description)
                        .withPurpose(purpose != null ? purpose : getSessionPurpose())
                        .withEventAction(eventAction)
                        .build())
                .build();
    }

    private Purpose getSessionPurpose() {
        return null;
    }

    private String getLoggedInUserId() {
        return null;
    }

    private Device getThisDevice() {
        return null;
    }

    private Device getClientDevice() {
        return null;
    }

    private String getBuildVersion() {
        return null;
    }

}