package ru.skogmark.www.controller;

import org.springframework.http.HttpStatus;
import ru.skogmark.www.store.FileRegister;

import javax.annotation.Resource;

/**
 * @author svip
 *         2016-05-29
 */
class BaseController {

    private static final String VIEW_PATH = "main";

    @Resource
    protected FileRegister fileRegister;

    protected String errorPage(HttpStatus status) {
        return VIEW_PATH + "/page" + status;
    }
}
