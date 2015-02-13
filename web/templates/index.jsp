<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="../css/main.css">
  </head>
  <body>
  <h1 align="center" >Хоть это запилил))</h1>
  Total count of entries =
    <%=  ((Integer)request.getAttribute("totalEntries")==null)? 0 : (Integer)request.getAttribute("totalEntries") %>
  </body>
</html>
