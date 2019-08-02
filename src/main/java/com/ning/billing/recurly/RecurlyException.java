package com.ning.billing.recurly;

/**
 * Base class for Recurly exceptions
 */
public class RecurlyException extends RuntimeException {

    public RecurlyException() {
        super();
    }

    public RecurlyException(String message) {
        super(message);
    }

    public RecurlyException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecurlyException(Throwable cause) {
        super(cause);
    }

}
