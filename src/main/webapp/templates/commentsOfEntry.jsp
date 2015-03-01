<%@ page import="ru.devlifestatistic.model.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <% List<Comment> comments = (List<Comment>)request.getAttribute("comments");%>
    <% if ((comments == null) || (comments.size() == 0)){ %>
      No comments in this entry.
    <%}%>
    <div id="comments">
      <% for (Comment comment : comments){%>
      <div class="comment <%= (comment.getId() == null)? "-" : comment.getId() %>">
        <div class="content">
            <span class="author">@info (author = <%= (comment.getAuthorName() == null) ? "-" : comment.getAuthorName()%>,</span>
            <span class="date"> date = <% SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy", Locale.UK);%>
                                        <%= (comment.getDate() == null)? "-" : formatDate.format(comment.getDate()) %>,</span>
            <span class="time"> time = <% SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm", Locale.UK);%>
                                        <%= (comment.getDate() == null)? "-" : formatTime.format(comment.getDate()) %>,</span>
            <span class="rating"> rating = <%= (comment.getVoteCount() == null)? "no votes" : comment.getVoteCount() %>)</span>

          <div class="text"><%= (comment.getText() == null)? "-" : comment.getText() %></div>
			 </div>
        </div>
      <%}%>
        </div>
