<%--
  Created by IntelliJ IDEA.
  User: svip
  Date: 08.03.16
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="w" tagdir="/WEB-INF/tags/widgets/main" %>
<t:main title="Home page">
  <jsp:attribute name="content">
    <w:logo/>
    <w:feed/>
    <w:actual-album/>
  </jsp:attribute>
</t:main>
