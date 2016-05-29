package ru.skogmark.website.controller;

import org.springframework.http.HttpStatus;

/**
 * @author svip
 *         2016-05-29
 */
class BaseController {

    private static final String VIEW_PATH = "main";

    protected String errorPage(HttpStatus status) {
        return VIEW_PATH + "/page" + status;
    }
}
