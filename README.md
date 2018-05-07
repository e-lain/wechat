# wechat
wechat development

一些微信接口的开发尝试

暂时只是用户，消息，菜单等接口

项目是直接在maven项目中修改的，pom.xml懒得改了


项目有关直接通过发送请求即可达到目的的接口，直接在本地测试
例如获取用户列表

#################

服务器上部署：
暂时只是将项目中的classes直接放到tomcat项目WEB-INF下
在tomcat下的web.xml中写入微信公众号的appid和appsecret
就可以直接运行
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
  version="3.1"
  metadata-complete="true">

  <display-name>Welcome to Tomcat</display-name>
  <description> Welcome wechat development </description>
	
	<servlet> 
		<servlet-name>initAccessTokenServlet</servlet-name> 
		<servlet-class> top.zerotop.wechat.AccessTokenServlet </servlet-class> 
		<init-param> 
			<param-name>appid</param-name> 
			<param-value>yourappid</param-value> 
		</init-param> 
		<init-param> 
			<param-name>appsecret</param-name> 
			<param-value>yourappsecret</param-value> 
		</init-param> <load-on-startup>0</load-on-startup> 
	</servlet> 
	<servlet>
		<servlet-name>entrance</servlet-name>
		<servlet-class>top.zerotop.wechat.Entrance</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>entrance</servlet-name>
		<url-pattern>/wechat</url-pattern>
	</servlet-mapping>
	
	<filter>
    		<filter-name>encodingFilter</filter-name>
    		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    	<init-param>
      		<param-name>encoding</param-name>
      		<param-value>UTF-8</param-value>
    	</init-param>
    	<init-param>
      		<param-name>forceEncoding</param-name>
     		<param-value>true</param-value>
	</init-param>
  	</filter>
  		<filter-mapping>
    		<filter-name>encodingFilter</filter-name>
    		<url-pattern>/*</url-pattern>
  	</filter-mapping>
  
  	<servlet>
    		<servlet-name>springDispatcher</servlet-name>
    		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    		<init-param>
      			<param-name>contextConfigLocation</param-name>
      			<param-value>classpath:application-context.xml</param-value>
    		</init-param>
    		<load-on-startup>2</load-on-startup>
  	</servlet>
  	<servlet-mapping>
    		<servlet-name>springDispatcher</servlet-name>
    		<url-pattern>/</url-pattern>
  	</servlet-mapping>

</web-app>

#########

由于添加了jssdk,所以使用了spring



按照微信jssdk的逻辑，感觉可以在本地vue或者其他页面里初始化config
并调用微信接口中的方法。然后暂时还在尝试，之后更新

