package ru.skogmark.www.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.skogmark.www.domain.Post;
import ru.skogmark.www.service.PostService;

import javax.annotation.Resource;

/**
 * @author svip
 *         2016-03-09
 */
@Controller
@RequestMapping("/post")
public class PostController extends BaseController {

    @Resource(name = "postService")
    private PostService postService;

    private static Logger logger = Logger.getLogger("PostController");

    @RequestMapping(path = "/{id}/", method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN"})
    public String detail(@PathVariable int id, Model model) {
        try {
            logger.debug("Rendering detail post page");
            logger.debug("Getting post " + id);

            Post post = postService.getPostById(id);
            if (null == post) {
                throw new HttpException(HttpStatus.NOT_FOUND, "Post id" + id + " could not be found");
            }

            model.addAttribute("post", post);
            model.addAttribute("createdAt", postService.getCreatedAtString(post));
            model.addAttribute("imageSrc", postService.getImageSrc(post));
        } catch (HttpException e) {
            return errorPage(e.getStatus());
        }

        return "post/detail";
    }

}