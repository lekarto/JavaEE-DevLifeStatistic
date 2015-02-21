<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Total count of Entries</title>
    <link rel="stylesheet" type="text/css" href="../css/main.css">
</head>
<body>

<p>Total entries:
<%=  ((Integer)request.getAttribute("totalEntries")==null)? 0 : (Integer)request.getAttribute("totalEntries") %>

<p>ID:
<%=  ((Integer)request.getAttribute("id")==null)? 0 : (Integer)request.getAttribute("id") %>

<p>Description:
<%=  ((String)request.getAttribute("description")==null)? 0 : (String)request.getAttribute("description") %>

<p>Rating:
<%=  ((Integer)request.getAttribute("votes")==null)? 0 : (Integer)request.getAttribute("votes") %>

<p>Author:
<%=  ((String)request.getAttribute("author")==null)? 0 : (String)request.getAttribute("author") %>

<p>Date:
<%=  ((Date)request.getAttribute("date")==null)? 0 : (Date)request.getAttribute("date") %>

<p>Link to gif:
    <a href=<%=  ((String)request.getAttribute("gifUrl")==null)? 0 : (String)request.getAttribute("gifUrl") %>>There is a gif.</a>

<p>Link to image:
    <a href=<%=  ((String)request.getAttribute("imageUrl")==null)? 0 : (String)request.getAttribute("imageUrl") %>>There is an image.</a>

<p>Type:
<%=  ((String)request.getAttribute("type")==null)? 0 : (String)request.getAttribute("type") %>

<p>Embed ID:
<%=  ((String)request.getAttribute("embedId")==null)? 0 : (String)request.getAttribute("embedId") %>

</body>
</html>


