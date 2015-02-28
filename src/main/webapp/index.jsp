<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>DevelopersLife Statistic</title>
    <link rel="shortcut icon" href="/devlifestatistic/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="/devlifestatistic/css/main.css">
    <script src="/devlifestatistic/libs/jQuery/jquery-1.11.2.min.js"></script>
    <script>
      $( document ).ready(function() {
        console.log( "ready!" );
        console.log( $(".wrapper").height() );
        console.log( $(document).height() );
        if ($(".wrapper").height() < $(document).height()){
          $(".wrapper").height($(document).height() - 30);
        }
      });
    </script>
  </head>

  <body>
    <div class="wrapper">
    <jsp:include page="templates/header.jsp" />
    Hello. This is the main page of our project. As you see we just start it, so we haven't so much time for design.<br />
    Now you can see only page with total count of entries <a href="/devlifestatistic/entries">here</a>.<br />
    Have a nice day :-)
    </div>
  </body>
</html>
