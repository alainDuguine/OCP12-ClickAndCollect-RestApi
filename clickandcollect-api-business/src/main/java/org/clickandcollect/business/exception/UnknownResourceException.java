package org.clickandcollect.business.exception;

public class UnknownResourceException extends RuntimeException {
    public UnknownResourceException(String message) {
        super(message);
    }
}
