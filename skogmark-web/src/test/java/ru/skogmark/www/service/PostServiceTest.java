package ru.skogmark.www.service;

import junit.framework.TestCase;
import org.mockito.Mockito;
import ru.skogmark.www.domain.Post;

import java.sql.Date;

/**
 * @author svip
 *         2016-07-11
 */
public class PostServiceTest extends TestCase {

    public void testGetCreatedAtString() throws Exception {
        PostService postService = new PostService();

        Post post1 = Mockito.mock(Post.class);
        Mockito.when(post1.getCreatedAt()).thenReturn(Date.valueOf("2016-01-01"));

        Post post2 = Mockito.mock(Post.class);
        Mockito.when(post2.getCreatedAt()).thenReturn(Date.valueOf("2016-07-25"));

        assertEquals("1 January 2016", postService.getCreatedAtString(post1));
        assertEquals("25 July 2016", postService.getCreatedAtString(post2));
    }

    public void testGetRecentPosts() throws Exception {

    }
}