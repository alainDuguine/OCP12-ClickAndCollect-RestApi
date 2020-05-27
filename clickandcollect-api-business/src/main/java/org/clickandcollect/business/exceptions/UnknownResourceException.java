package org.clickandcollect.business.exceptions;

public class UnknownResourceException extends RuntimeException {
    public UnknownResourceException(String message) {
        super(message);
    }
}
