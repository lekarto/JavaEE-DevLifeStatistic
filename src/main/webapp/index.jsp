<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>DevelopersLife Statistic</title>
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script src="libs/jQuery/jquery-1.11.2.min.js"></script>
    <script src="js/functions.js"></script>
  </head>

  <body>
    <div class="wrapper">
      <jsp:include page="templates/header.jsp" />
      <div class="pageContent">
        Hello. This is the main page of our project. As you see we just start it, so we haven't so much time for design.<br />
        Now you can see only page with total count of entries <a class="ajax" href="/devlifestatistic/entries">here</a>.<br />
        Have a nice day :-)
      </div>
    </div>
  </body>
</html>
