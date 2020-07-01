package org.clickandcollect.business.exception;

public class UnauthorizedResourceException extends RuntimeException {
    public UnauthorizedResourceException(String message) {
        super(message);
    }
}
