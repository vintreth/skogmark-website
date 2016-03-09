<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/templates" %>
<t:main title="Home page">
  <jsp:attribute name="content">
    <section class="page__detail-feed-container">
      <div class="detail-feed__feed-column">
        <div class="detail-feed__post">
          <div class="detail-feed__title">NEW ALBUM: "Raging Clouds" is out</div>
          <div class="feed__post-date">June 14, 2015</div>
          <div class="detail-feed__content">
            <p>
              ${id}
              Great looking images make your blog posts more visually compelling for your audience, and
              encourage
              readers to keep coming back.
            </p>

            <p>
              <img src="../../../resources/images/D_TodUfKFUI.jpg" alt=""/>
            </p>

            <p><img src="../../../resources/images/Y9D-ytgyWLo.jpg" alt=""/></p>
          </div>
          <div class="detail-feed__tags">
            <div class="detail-feed__tag">Tags:</div>
            <div class="detail-feed__tag"><a href="#">New Album</a></div>
            <div class="detail-feed__tag"><a href="#">Sworn To Paganism</a></div>
          </div>
        </div>
      </div><!--
    ---><div class="detail-feed__side-bar">
      <div class="side-bar__item">
        <div class="side-bar__item-title">Featured Posts</div>
        <div class="side-bar__item-carousel">
          <ul>
            <li>
              <div class="carousel__post-preview">
                <a href="#">
                  <img src="../../../resources/images/1jEWiVU49a8.jpg" alt=""/>
                </a>
              </div>
              <div class="carousel__post-title">
                <a href="#">Backstage with The Toxic Void</a>
              </div>
              <div class="carousel__post-date">June 14, 2015</div>
            </li>
          </ul>
        </div>
      </div>
    </div>
    </section>
  </jsp:attribute>
</t:main>
