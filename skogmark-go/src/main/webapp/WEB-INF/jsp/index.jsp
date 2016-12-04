<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Graf's wisdom on every fay</title>
  <!-- Bootstrap -->
  <link href="<c:url value="/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>" rel="stylesheet">
  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  <style>
    body {
      font-size: 24px;
    }
    h1 {
      font-size: 48px;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="row">
    <div class="col-md-12">
      <h1 class="text-center">Граф говорит</h1>
    </div>
  </div>
  <div class="row">&nbsp;</div>
  <div class="row">
    <div class="col-md-12">
      <p id="wisdom" class="text-center">Сатанизм это действие пойми, ну блейзер пить тоже дело</p>
    </div>
  </div>
  <div class="row">&nbsp;</div>
  <div class="row">
    <div class="col-md-12 text-center">
      <button id="btn-refresh" class="btn btn-primary btn-lg">Обновить</button>
      <button id="btn-wisdom" class="btn btn-primary btn-lg">Мудрость</button>
      <button id="btn-favorite" class="btn btn-primary btn-lg">Избранное</button>
    </div>
  </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script type="text/javascript" src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Rest.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Service.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Scene.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Settings.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js"/>"></script>
</body>
</html>