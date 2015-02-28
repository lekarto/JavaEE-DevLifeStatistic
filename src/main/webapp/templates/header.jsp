<%
  String[][] buttonsLinks = {{"/entries", "Entries"}, 
                             {"/comments", "Comments"}};
  String urlPattern = (request.getAttribute("urlPattern") == null) ? "": (String)request.getAttribute("urlPattern");
%>
<div class="header">
  <div class="logo">
    <a href="/devlifestatistic">
      <img src="/devlifestatistic/images/logo.png" width="32px" height="32px" /><div>DevelopersLife Statistic</div>
    </a>
  </div>
  <ul class="menu">
    <%
      for (String[] s : buttonsLinks) { %>
        <li><a <%=(urlPattern.equals(s[0]))?"class=\"activeButton\"":""%> href="/devlifestatistic<%=s[0]%>"><%=s[1]%></a></li>
    <%}%>
    
  </ul>
  <div class="none"></div>
</div>