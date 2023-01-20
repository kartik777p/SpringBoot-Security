<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<h4 style="text-align:center"> Offers Page </h4>
<h5><a href="/">Go To Home </a> </h5>
<br>
<br>
<b> hello mr <%=SecurityContextHolder.getContext().getAuthentication().getName()%><b><br>
<br>
<!--Fixed URL-->
<!-- <a href="logout"> LogOut </a> -->

<!-- Custom URL for logout -->
<a href="/signout"> LogOut </a>