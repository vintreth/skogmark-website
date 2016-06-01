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
class ClientApiController {

//    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Record could not be found")
//    @ExceptionHandler(RecordNotFoundException.class)
//    @ResponseBody
//    public String notFound(RecordNotFoundException e) {
//        return new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND).toString();
//    }

    protected static class ErrorResponse {

        private String message;

        private HttpStatus status;

        public ErrorResponse() {
        }

        public ErrorResponse(String message, HttpStatus status) {
            this.message = message;
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public HttpStatus getStatus() {
            return status;
        }

        public void setStatus(HttpStatus status) {
            this.status = status;
        }
    }
}
