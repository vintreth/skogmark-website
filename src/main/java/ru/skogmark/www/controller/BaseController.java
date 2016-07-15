package ru.skogmark.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import ru.skogmark.www.store.FileRegister;

/**
 * @author svip
 *         2016-05-29
 */
class BaseController {

    private static final String VIEW_PATH = "main";

    @Autowired
    protected FileRegister fileRegister;

    protected String errorPage(HttpStatus status) {
        return VIEW_PATH + "/page" + status;
    }

}
