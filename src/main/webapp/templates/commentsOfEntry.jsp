<%@ page import="ru.devlifestatistic.model.Comment" %>
<%@ page import="java.util.ArrayList" %>
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
    <% ArrayList<Comment> comments = (ArrayList<Comment>)request.getAttribute("comments");%>
    <% if (comments.size() == 0){ %>
      No comments in this entry.
    <%}%>

    <% for (int i = 0; i < comments.size(); i++){%>
      <p>ID: <%= (comments.get(i).getId() == null)? "-" : comments.get(i).getId() %>
        <br>Text: <%= (comments.get(i).getText() == null)? "-" : comments.get(i).getText() %>
        <br>Date: <%= (comments.get(i).getDate() == null)? "-" : comments.get(i).getDate() %>
        <br>Vote Count: <%= (comments.get(i).getVoteCount() == null)? "no votes" : comments.get(i).getVoteCount() %>
        <br>Author ID: <%= (comments.get(i).getAuthorId() == null)? "-" : comments.get(i).getAuthorId() %>
        <br>Author Name: <%= (comments.get(i).getAuthorName() == null)? "-" : comments.get(i).getAuthorName() %>
        <br>Parent ID: <%= (comments.get(i).getParentId() == null)? "don`t available" : comments.get(i).getParentId() %>
        <br>Entry ID: <%= (comments.get(i).getEntryId() == null)? "-" : comments.get(i).getEntryId() %>
        <br>Deleted: <%= (comments.get(i).getDeleted() == false)? "not" : comments.get(i).getDeleted() %>
        <br>Voted: <%= (comments.get(i).getVoted() == false)? "not" : comments.get(i).getVoted() %>
        <br>Editable: <%= (comments.get(i).getEditable() == false)? "not" : comments.get(i).getEditable() %>
      </p>
    <%}%>
  </div>
</body>
</html>
