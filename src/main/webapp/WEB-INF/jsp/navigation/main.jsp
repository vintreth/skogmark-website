<%@ page import="ru.skogmark.www.Navigation" %>
<%--
  Created by IntelliJ IDEA.
  User: svip
  Date: 11.06.16
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<% Navigation.Item[] items = new Navigation().getNavigation(); %>
<div class="head_bar__navigation">
  <ul>
    <% for (Navigation.Item item : items) {%>
    <li<%--class="selected"--%>>
      <a href="<%= item.getUrl() %>"><%= item.getName() %></a>
    </li>
    <%}%>
  </ul>
</div>