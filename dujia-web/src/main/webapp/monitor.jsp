<%@ page session="false" %>
<%@ page import="com.gome.plan.tools.utils.Monitor"%>
<%@ page import="com.gome.plan.tools.utils.JsonUtils"%>
<%@ page contentType="text/plain;charset=UTF-8" language="java" %>
<%=new JsonUtils("JSON").serialize(Monitor.getValues())%>
