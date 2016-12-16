package ru.skogmark.go.blogger.blog;

import org.springframework.stereotype.Component;

/**
 * Central interface of an abstract blog
 */
@Component
public interface Blog {
    /**
     * Posts a message to the blog
     */
    void post(Post post);
}
