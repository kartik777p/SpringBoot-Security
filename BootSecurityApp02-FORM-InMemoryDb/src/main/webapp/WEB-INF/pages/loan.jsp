

<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<h4 style="text-align:center"> Loan Page </h4>

<br>
<br>
<b> hello mr <%=SecurityContextHolder.getContext().getAuthentication().getName()%><b><br>
<h5><a href="/">Go To Home </a> </h5>
<br>
<br>
<!--Fixed URL-->
<!-- <a href="logout"> LogOut </a> -->

<!-- Custom URL for logout -->
<a href="/signout"> LogOut </a>