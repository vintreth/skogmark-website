<%@ tag import="ru.skogmark.www.store.FileRegisterImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" %>
<% FileRegisterImpl.getInstance().addPackage("main/feed"); %>
<section class="page__feed">
  <div class="page__separator-small"></div>
  <h1>Latest News</h1>
  <div class="feed__container">
    <div class="feed__container-column">
      <div class="feed__post">
        <div class="feed__post-title">
          <a href="/post/1/">The Toxic Void performance at WixMusic Festival hit waves</a>
        </div>
        <div class="feed__post-date">June 14, 2015</div>
        <div class="feed__post-preview-image"></div>
        <div class="feed__post-preview-text">
          To create your first blog post, click here and select 'Add & Edit
          Posts' > All Posts > This is the title of your first blog post.

          I'm a paragraph. Click here to add your own text and edit me. It’s easy. Just click “Edit Text”
          or
          double click me to add your own content and make changes to the font. Feel free to drag and drop
          me
          anywhere you like on your page. I’m a great place for you to tell a story and let your users
          know a
          little more about you.
        </div>
        <a href="/post/1" class="feed__post-read-more-btn button">Read More</a>
      </div>
      <div class="feed__post">
        <div class="feed__post-title">
          <a href="/post/2">Backstage with The Toxic Void</a>
        </div>
        <div class="feed__post-date">June 14, 2015</div>
        <div class="feed__post-preview-image">
          <a href="/post/2">
            <img src="../../../../resources/images/1jEWiVU49a8.jpg" alt=""/>
          </a>
        </div>
        <div class="feed__post-preview-text">
          Great looking images make your blog posts more visually compelling for your audience, and
          encourage
          readers to keep coming back.
        </div>
        <a href="/post/2" class="feed__post-read-more-btn button">Read More</a>
      </div>
      <div class="feed__post">
        <div class="feed__post-title">
          <a href="#">Win a Golden Ring ticket to our next show</a>
        </div>
        <div class="feed__post-date">June 14, 2015</div>
        <div class="feed__post-preview-image">
          <a href="">
            <img src="../../../../resources/images/D_TodUfKFUI.jpg" alt=""/>
          </a>
        </div>
        <div class="feed__post-preview-text">
          Great looking images make your blog posts more visually compelling for your audience, and
          encourage
          readers to keep coming back.
        </div>
        <a href="#" class="feed__post-read-more-btn button">Read More</a>
      </div>
    </div>
    <div class="feed__container-column">
      <div class="feed__post">
        <div class="feed__post-title">
          <a href="#">NEW ALBUM: "Raging Clouds" is out</a>
        </div>
        <div class="feed__post-date">June 14, 2015</div>
        <div class="feed__post-preview-image">
          <a href="">
            <img src="../../../../resources/images/Y9D-ytgyWLo.jpg" alt=""/>
          </a>
        </div>
        <div class="feed__post-preview-text">
          Great looking images make your blog posts more visually compelling for your audience, and
          encourage
          readers to keep coming back.
        </div>
        <a href="#" class="feed__post-read-more-btn button">Read More</a>
      </div>
      <div class="feed__post">
        <div class="feed__post-title">
          <a href="#">“Rain of Doom” hits #1</a>
        </div>
        <div class="feed__post-date">June 14, 2015</div>
        <div class="feed__post-preview-image">
          <a href="">
            <img src="../../../../resources/images/05e3dc_553608c4545b41a3b9ecf44f6fdd9354.jpg"
                 alt=""/>
          </a>
        </div>
        <div class="feed__post-preview-text">
          To create your first image blog post, click here and select 'Add & Edit Posts' > All Posts >
          This is the title of your first image post.
          Great looking images make your blog posts more visually compelling for your audience, and
          encourage readers to keep coming back
        </div>
        <a href="#" class="feed__post-read-more-btn button">Read More</a>
      </div>
    </div>
    <div class="clearfix"></div>
  </div>
</section>