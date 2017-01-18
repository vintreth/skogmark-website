package ru.skogmark.go.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 02.12.2016 12:45
 */
@Controller
public class WebController {
    private static final String INDEX_JSP = "index";

    private static final Logger logger = Logger.getLogger(WebController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        logger.debug("Calling index page");
        return INDEX_JSP;
    }
}
