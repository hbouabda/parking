package com.example.parking.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

import static com.example.parking.exception.TechnicalException.TechnicalExceptionType.DEFAULT_INTERNAL_SERVER_ERROR;


@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);


    /**
     * Handles all NotFoundExceptions
     *
     * @param response
     * @param e        a NotFoundException
     * @return an exception response that contains the details of the
     * NotFoundException
     */
    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionResponse handleUserNotFoundException(HttpServletResponse response, NotFoundException e) {
        LOG.error(e.getMessage(), e);
        return new ExceptionResponse(e.getTitleKey(), e.getMessageKey(), e);
    }

    /**
     * Handles all FunctionalExceptions
     *
     * @param response
     * @param e        a FunctionalException
     * @return an exception response that contains the details of the
     * FunctionalException
     */
    @ExceptionHandler({FunctionalException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionResponse handleFunctionalException(HttpServletResponse response, FunctionalException e) {
        LOG.error(e.getMessage(), e);
        return new ExceptionResponse(e.getTitleKey(), e.getMessageKey(), e);
    }


    /**
     * @param response
     * @param e        exception
     * @return an exception response that contains the details of all not managed
     * exceptions
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionResponse handleDefaultException(HttpServletResponse response, Exception e) {
        LOG.error(e.getMessage(), e);
        return new ExceptionResponse(
                DEFAULT_INTERNAL_SERVER_ERROR.getTitleKey(),
                DEFAULT_INTERNAL_SERVER_ERROR.getMessageKey(),
                e);
    }
}
