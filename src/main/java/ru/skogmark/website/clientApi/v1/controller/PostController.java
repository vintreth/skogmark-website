package ru.skogmark.website.clientApi.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.skogmark.website.clientApi.v1.exception.RecordNotFoundException;
import ru.skogmark.website.domain.Post;
import ru.skogmark.website.service.PostService;

import javax.annotation.Resource;

/**
 * @author kbogdanov 01.06.16
 */
@Controller
@RequestMapping("/clientapi/v1/post")
public class PostController {

    @Resource
    private PostService postService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Post getOne(@PathVariable int id) {
        Post post = postService.getPostById(id);

        if (null == post) {
            throw new RecordNotFoundException(String.format("Post %d could not be found", id));
        }

        return post;
    }
}
