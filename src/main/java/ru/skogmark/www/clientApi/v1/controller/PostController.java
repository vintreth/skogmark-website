package ru.skogmark.www.clientApi.v1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.skogmark.www.clientApi.v1.exception.RecordNotFoundException;
import ru.skogmark.www.domain.Post;
import ru.skogmark.www.service.PostService;

import javax.annotation.Resource;

/**
 * @author kbogdanov 01.06.16
 */
@Controller("clientApi.v1.PostController")
@RequestMapping("/clientapi/v1/post")
public class PostController extends ClientApiController {

    @Resource
    private PostService postService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Post getOne(@PathVariable int id) {
        Post post = postService.getPostById(id);

        if (null == post) {
            throw new RecordNotFoundException(String.format("Post %d could not be found", id));
        }

        return post;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public ModelAndView notFound(RecordNotFoundException e) {
        return super.notFound(e);
    }
}
