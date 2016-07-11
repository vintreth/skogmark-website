<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="w" tagdir="/WEB-INF/tags/widgets/post" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:main title="Home page">
  <jsp:attribute name="content">
    <section class="page__detail-feed-container">
      <div class="detail-feed__feed-column">
        <div class="detail-feed__post">
          <div class="detail-feed__title">${post.title}</div>
          <div class="feed__post-date">${post.createdBy.firstName}</div>
          <div class="feed__post-date">${createdAt}</div>
          <div class="feed__post-preview"><img src="${imageSrc}" alt="${post.title}"/></div>
          <div class="detail-feed__content">${post.content}</div>
          <div class="detail-feed__tags">
            <div class="detail-feed__tag">Tags:</div>
            <c:forEach var="tag" items="${post.tags}">
              <div class="detail-feed__tag"><a href="#">${tag.value}</a></div>
            </c:forEach>
          </div>
        </div>
      </div><!--
   --><div class="detail-feed__side-bar">
        <w:side-bar/>
      </div>
    </section>
  </jsp:attribute>
</t:main>
