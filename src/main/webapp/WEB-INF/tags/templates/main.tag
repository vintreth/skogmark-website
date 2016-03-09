<%@ tag language="java" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="content" fragment="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>${title}</title>
  <!--<link rel="stylesheet" href="../../../resources/css/template.css"/>-->
  <!--<link rel="stylesheet" href="../../../resources/css/forms.css"/>-->
  <!--<link rel="stylesheet" href="../../../resources/css/font-awesome.css"/>-->
  <!--<link rel="stylesheet" href="../../../resources/css/raleway.css"/>-->
  <!--<link rel="stylesheet" href="../../../resources/css/open-sans.css"/>-->
  <!--<link rel="stylesheet" href="../../../resources/css/museo-slab.css"/>-->
  <!--<link rel="stylesheet" href="../../../resources/css/icomoon.css"/>-->
  <link rel="stylesheet" href="<c:url value="/resources/css/template.css"/>"/>
  <link rel="stylesheet" href="<c:url value="/resources/css/forms.css"/>"/>
  <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.css"/>"/>
  <link rel="stylesheet" href="<c:url value="/resources/css/raleway.css"/>"/>
  <link rel="stylesheet" href="<c:url value="/resources/css/open-sans.css"/>"/>
  <link rel="stylesheet" href="<c:url value="/resources/css/museo-slab.css"/>"/>
  <link rel="stylesheet" href="<c:url value="/resources/css/icomoon.css"/>"/>
  <link rel="stylesheet" href="<c:url value="/resources/css/detail-feed.css"/>"/>
</head>
<body>
<div id="background-repeat"></div>
<div id="background-header">
  <div class="background-header__img"></div>
</div>
<div id="navigation-bg"></div>
<div id="page">
  <section class="page__head-bar">
    <div class="head_bar__navigation">
      <ul>
        <li class="selected"><a href="#">Home</a></li>
        <li><a href="#">Tour</a></li>
        <li><a href="#">Media</a></li>
        <li><a href="#">Albums</a></li>
        <li><a href="#">Biography</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
    </div>
    <div class="head_bar__social-block">
      <ul>
        <li><a href="https://www.facebook.com/skogmark" class="social-block__fb-icon">&nbsp;</a></li>
        <li><a href="https://soundcloud.com/skogmark" class="social-block__sc-icon">&nbsp;</a></li>
        <li><a href="https://vk.com/skogmark" class="social-block__vk-icon">&nbsp;</a></li>
        <li><a
          href="https://www.youtube.com/channel/UCD_aJMfIsQvfjoGZ1rOKNUQ"
          class="social-block__yt-icon">&nbsp;</a></li>
      </ul>
    </div>
  </section>
  <jsp:invoke fragment="content"/>
  <section class="page__footer">
    <div class="page__separator"></div>
    <h3>Join our mailing list for the latest news,<br/>dates & free tickets!</h3>
    <div class="footer__subscription-form">
      <form action="" method="post">
        <div class="form__input-group">
          <i class="form__icon-email"></i>
          <input class="form__input-text" type="email" placeholder="Email Address"/>
        </div>
        <div class="form__input-group">
          <input class="form__input-submit button" type="submit" value="Subscribe Now"/>
        </div>
      </form>
    </div>
    <div class="page__separator-small"></div>
    <div class="footer__social-block">
      <h3>Follow us on:</h3>
      <!--todo: require social widget-->
    </div>
    <div class="footer__copyright">
      <p>© 2016 by Skogmark</p>
    </div>
  </section>
</div>
</body>
</html>