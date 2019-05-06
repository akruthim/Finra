package com.interview.finra.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ResponseStatus(reason = "File metadata or file upload failed. Try after sometime")
    @ExceptionHandler(FileUploadFailedException.class)
    public void fileUploadFailureExceptionHandler(FileUploadFailedException ex) {
        logger.error("Exception occurred: ", ex);
    }

    @ResponseStatus(reason = "Ouch. Operation failed. Please try again.")
    @ExceptionHandler(Exception.class)
    public void genericExceptionHandler(Exception ex) {
        logger.error("Exception occurred: ", ex);
    }

}
