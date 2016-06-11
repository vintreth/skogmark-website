<%@ tag language="java" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="content" fragment="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="w" tagdir="/WEB-INF/tags/widgets/template" %>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>${title}</title>
  <link rel="stylesheet" href="<c:url value="/resources/css/template.css"/>"/>
  <link rel="stylesheet" href="<c:url value="/resources/css/forms.css"/>"/>
  <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.css"/>"/>
  <link rel="stylesheet" href="<c:url value="/resources/css/raleway.css"/>"/>
  <link rel="stylesheet" href="<c:url value="/resources/css/open-sans.css"/>"/>
  <link rel="stylesheet" href="<c:url value="/resources/css/museo-slab.css"/>"/>
  <link rel="stylesheet" href="<c:url value="/resources/css/icomoon.css"/>"/>
  <link rel="stylesheet" href="<c:url value="/resources/css/detail-feed.css"/>"/>
  <link rel="stylesheet" href="<c:url value="/resources/css/tour.css"/>"/>
</head>
<body>
<div id="background-repeat"></div>
<div id="page">
  <section class="page__head-bar">
    <div id="navigation-bg"></div>
    <div class="head_bar__navigation-container">
      <jsp:include page="/WEB-INF/jsp/navigation/main.jsp"/>
      <div class="head_bar__social-block social-block">
        <ul>
          <li><a href="https://www.facebook.com/skogmark" class="social-block__fb-icon">&nbsp;</a></li>
          <li><a href="https://soundcloud.com/skogmark" class="social-block__sc-icon">&nbsp;</a></li>
          <li><a href="https://vk.com/skogmark" class="social-block__vk-icon">&nbsp;</a></li>
          <li><a
            href="https://www.youtube.com/channel/UCD_aJMfIsQvfjoGZ1rOKNUQ"
            class="social-block__yt-icon">&nbsp;</a></li>
        </ul>
      </div>
    </div>
  </section>
  <jsp:invoke fragment="content"/>
  <section class="page__footer">
    <div class="page__separator-small"></div>
    <div class="footer__social-block-container">
      <h3>Follow us on:</h3>
      <w:social-block htmlClass="footer__social-block"/>
    </div>
    <div class="footer__copyright">
      <p>© 2016 by Skogmark</p>
    </div>
  </section>
</div>
</body>
</html>