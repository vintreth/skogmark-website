package ru.skogmark.website.clientApi.v1.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.skogmark.website.clientApi.v1.exception.JsonRenderException;
import ru.skogmark.website.clientApi.v1.exception.RecordNotFoundException;

import java.io.IOException;

/**
 * @author kbogdanov 01.06.16
 */
class ClientApiController {

    public ModelAndView notFound(RecordNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND);

        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.addObject("json", errorResponse.toJson());
        } catch (JsonRenderException exception) {
            modelAndView.addObject("json", "{\"message\":\"Failure to render a json response\"}");
        }

        modelAndView.setViewName("clientApi/error");

        return modelAndView;
    }

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

        public int getStatus() {
            return status.value();
        }

        public void setStatus(HttpStatus status) {
            this.status = status;
        }

        public String toJson() throws JsonRenderException {
            try {
                ObjectMapper objectMapper = new ObjectMapper();

                return objectMapper.writeValueAsString(this);
            } catch (IOException e) {
                throw new JsonRenderException(e);
            }
        }
    }
}
