<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="w" tagdir="/WEB-INF/tags/widgets/post" %>
<t:main title="Home page">
  <jsp:attribute name="content">
    <section class="page__detail-feed-container">
      <div class="detail-feed__feed-column">
        <div class="detail-feed__post">
          <div class="detail-feed__title">${post.title}</div>
          <div class="feed__post-date">${createdAt}</div>
          <div class="feed__post-preview"><img src="${imageSrc}" alt="${post.title}"/></div>
          <div class="detail-feed__content">${post.content}</div>
          <div class="detail-feed__tags">
            <div class="detail-feed__tag">Tags:</div>
            <div class="detail-feed__tag"><a href="#">New Album</a></div>
            <div class="detail-feed__tag"><a href="#">Sworn To Paganism</a></div>
          </div>
        </div>
      </div><!--
   --><div class="detail-feed__side-bar">
        <w:side-bar/>
      </div>
    </section>
  </jsp:attribute>
</t:main>
