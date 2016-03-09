package ru.skogmark.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author svip
 *         2016-03-09
 */
@Controller
public class PostController {

    @RequestMapping(path = "/post/{id}", method = RequestMethod.GET)
    public String detailPage(@PathVariable Integer id, Model model) {
        model.addAttribute("id", id);

        return "post/detail";
    }

}
