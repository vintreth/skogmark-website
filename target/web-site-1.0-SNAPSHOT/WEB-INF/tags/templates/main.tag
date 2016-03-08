<%--
  Created by IntelliJ IDEA.
  User: svip
  Date: 08.03.16
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ tag language="java" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="content" fragment="true" %>
<html>
<head>
  <title>${title}</title>
</head>
<body>
  <jsp:invoke fragment="content"/>
</body>
</html>
