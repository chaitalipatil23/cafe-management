<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg=(String) session.getAttribute("msg");
	String errmsg=(String)session.getAttribute("errmsg");
	session.removeAttribute("msg");
	session.removeAttribute("errmsg");
%>
<%
if(msg!=null){%>
<div class="alert alert-success"><%=msg %></div>
<%} else if(errmsg!=null){ %>

<div class="alert alert-danger"><%=errmsg %></div>
<%} %>
