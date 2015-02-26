<%@ page import="ru.devlifestatistic.model.Entry" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Total count of Entries</title>
    <link rel="shortcut icon" href="/devlifestatistic/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="/devlifestatistic/css/main.css">
</head>
<body>
  <div class="wrapper">
    <jsp:include page="/templates/header.jsp" />
    <p>Total entries: <%= ((Integer)request.getAttribute("totalEntries")==null)? 0 : (Integer)request.getAttribute("totalEntries") %>
    
    <% Entry entry = (Entry)request.getAttribute("entry"); %>
    
    <p>ID: <%= (entry == null)? "-" : entry.getId() %>
    <p>Description: <%= (entry == null)? "-" : entry.getDescription() %>
    <p>Rating: <%= (entry == null)? "-" : entry.getVotes() %>
    <p>Author: <%= (entry == null)? "-" : entry.getAuthor() %>
    <p>Date: <%= (entry == null)? "-" : entry.getDate() %>
    <p>Type: <%= (entry == null)? "-" : entry.getType() %>
    <p>Link to gif: <a href=<%= (entry == null)? "-" : entry.getGifURL() %>>There is a gif.</a>
    <p>Link to image: <a href=<%= (entry == null)? "-" : entry.getPreviewURL() %>>There is an image.</a>
    <p>Embed ID: <%= (entry == null)? "-" : entry.getEmbedId() %>
  </div>
</body>
</html>