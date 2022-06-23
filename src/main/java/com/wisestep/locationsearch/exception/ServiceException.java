package com.wisestep.locationsearch.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private final String errorCode;

    private final String errorMessage;

    /**
     * @param errorCode    shortHand way to inform about the exception.
     * @param errorMessage cause for the exception.
     */
    public ServiceException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;

    }

    /**
     * @param errorCode    shortHand way to inform about the exception.
     * @param errorMessage cause for the exception.
     * @param t            full stacktrace of exception.
     */
    public ServiceException(String errorCode, String errorMessage, Throwable t) {
        super(errorMessage, t);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;

    }


}
