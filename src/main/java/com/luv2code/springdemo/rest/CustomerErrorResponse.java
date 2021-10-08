package com.luv2code.springdemo.rest;

public class CustomerErrorResponse {

    private int statusCode;
    private long timeStamp;
    private String errorMessage;

    public CustomerErrorResponse() {
    }

    public CustomerErrorResponse(int statusCode, long timeStamp, String errorMessage) {
        this.statusCode = statusCode;
        this.timeStamp = timeStamp;
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
