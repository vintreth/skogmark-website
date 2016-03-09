package ru.skogmark.website.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author svip
 *         2016-03-08
 */
@Controller
public class MainController {

    private static final String INDEX_VIEW = "main/index";

    private static Logger logger = Logger.getLogger("MainController");

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        logger.debug("Processing index page");

        return INDEX_VIEW;
    }
}
