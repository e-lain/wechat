<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    boolean showAns = false;
 %>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>计算题</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
          ${calculation.title }<br>
          ${calculation.content }<br>
          
          <br>
          <input type="button" value="显示答案" onclick="changeAns(this)" style="width:200px;height:50px;"/>
          <!-- value="<showAns?"隐藏答案":"显示答案">"  -->
          <br>
 
         <%-- if (showAns ) 
         { --%>  
         <div id="calans" style="display:none"> ${calculation.ans }<br></div>
          <%--} --%>
          
          <br>
          <a href="tunnel/calculation/2" style="color:#666; font-size:28px;">练一练</a>
  </body>
  <script type="text/javascript">
    function changeAns(obj){
    
        var div = document.getElementById("calans");
        if(obj.value=="隐藏答案"){
            div.style.display = "none";
            obj.value = "显示答案";
        } else {
            div.style.display = "block";
            obj.value = "隐藏答案";
        }
    }
  </script>
  
</html>
