package ru.skogmark.go.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 02.12.2016 12:45
 */
@Controller
@Scope("session")
public class WebController {
    private static final String INDEX_JSP = "index";

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return INDEX_JSP;
    }
}