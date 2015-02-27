<%@ page import="ru.devlifestatistic.model.Comment" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Comments of entry</title>
    <link rel="shortcut icon" href="/devlifestatistic/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
  <div class="wrapper">
  <jsp:include page="/templates/header.jsp" />
    <% List<Comment> comments = (List<Comment>)request.getAttribute("comments");%>
    <% if ((comments == null) || (comments.size() == 0)){ %>
      No comments in this entry.
    <%}%>

    <% for (Comment comment : comments){%>
      <p>ID: <%= (comment.getId() == null)? "-" : comment.getId() %>
        <br>Text: <%= (comment.getText() == null)? "-" : comment.getText() %>
        <br>Date: <%= (comment.getDate() == null)? "-" : comment.getDate() %>
        <br>Vote Count: <%= (comment.getVoteCount() == null)? "no votes" : comment.getVoteCount() %>
        <br>Author ID: <%= (comment.getAuthorId() == null)? "-" : comment.getAuthorId() %>
        <br>Author Name: <%= (comment.getAuthorName() == null)? "-" : comment.getAuthorName() %>
        <br>Parent ID: <%= (comment.getParentId() == null)? "don`t available" : comment.getParentId() %>
        <br>Entry ID: <%= (comment.getEntryId() == null)? "-" : comment.getEntryId() %>
        <br>Deleted: <%= (comment.getDeleted() == false)? "not" : comment.getDeleted() %>
        <br>Voted: <%= (comment.getVoted() == false)? "not" : comment.getVoted() %>
        <br>Editable: <%= (comment.getEditable() == false)? "not" : comment.getEditable() %>
      </p>
    <%}%>
  </div>
</body>
</html>
