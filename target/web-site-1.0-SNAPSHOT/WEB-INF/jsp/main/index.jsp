<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="w" tagdir="/WEB-INF/tags/widgets/main" %>
<t:main title="Home page">
  <jsp:attribute name="content">
    <w:logo/>
    <w:feed/>
    <w:actual-album/>
    <w:subscription/>
  </jsp:attribute>
</t:main>
