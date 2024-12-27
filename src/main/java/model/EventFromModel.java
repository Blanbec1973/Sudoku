package model;

public class EventFromModel {
    private final EventFromModelType eventFromModelType;
    private final int numCase;
    private final String message;

    public EventFromModel(EventFromModelType eventFromModelType, int numCase, String message) {
        this.eventFromModelType = eventFromModelType;
        this.numCase = numCase;
        this.message = message;
    }

    public EventFromModelType getEventFromModelType() {
        return eventFromModelType;
    }

    public int getNumCase() {
        return numCase;
    }

    public String getMessage() {
        return message;
    }
}
