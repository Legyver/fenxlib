package com.legyver.fenxlib.api.alert;

/**
 * The text content to be displayed in an alert
 */
public class AlertTextContent {
    private final String message;
    private final Object[] args;

    /**
     * Construct the textual content for an alert
     * @param message the message or property key of an i18n property
     * @param args any args that should be evaluated with an i18n property
     */
    public AlertTextContent(String message, Object...args) {
        this.message = message;
        this.args = args;
    }

    /**
     * Get the text or property key for the message
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get any args that should be evaluated against an i18n property
     * @return any supplied arguments
     */
    public Object[] getArgs() {
        return args;
    }
}
