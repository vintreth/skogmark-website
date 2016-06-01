package ru.skogmark.website.clientApi.v1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.skogmark.website.clientApi.v1.exception.RecordNotFoundException;

/**
 * @author kbogdanov 01.06.16
 */
@Controller
public class ErrorController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Record could not be found")
    @ExceptionHandler(RecordNotFoundException.class)
    public
    @ResponseBody
    ErrorResponse notFound(RecordNotFoundException e) {
        return new ErrorResponse() {
            @Override
            public String getMessage() {
                return e.getMessage();
            }

            @Override
            public HttpStatus getStatus() {
                return HttpStatus.NOT_FOUND;
            }
        };
    }

    private interface ErrorResponse {
        String getMessage();

        HttpStatus getStatus();
    }
}
