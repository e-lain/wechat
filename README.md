# wechat

wechat公众号相关开发

### 项目简介 

spring mvc项目  
项目使用spring + xml配置的方式开发

主要是调用一些微信公众号接口，微信公众号管理页面的本地项目   
微信公众号的一些开发

### 服务器上部署：
  
将项目打成war包放到服务器tomcat上  
部署前需要再项目的webapp的web.xml中写入个人微信公众号的appid和appsecret      

项目是直接在maven项目中修改的 web.xml如下
```
    <servlet>
      <servlet-name>initAccessTokenServlet</servlet-name>
      <servlet-class>top.zerotop.wechat.AccessTokenServlet</servlet-class>
      <init-param>
        <param-name>appid</param-name>
        <param-value>yourappid</param-value>
      </init-param>
      <init-param>
        <param-name>appsecret</param-name>
        <param-value>yourappsecret</param-value>
      </init-param>
      <load-on-startup>0</load-on-startup>
    </servlet>
    <servlet>
      <servlet-name>entrance</servlet-name>
      <servlet-class>top.zerotop.wechat.Entrance</servlet-class>
    </servlet>
    <servlet-mapping>
      <servlet-name>entrance</servlet-name>
      <url-pattern>/wechat</url-pattern>
    </servlet-mapping>
```

### 技术栈：    

spring + mvc  
数据库： mysql    
连接池： druid  
  
接口管理工具： swagger-ui

### 备注

接口会逐步添加，包括js-sdk,  敬请期待  
暂时使用微信公众号测试账号进行开发  
接口数量和实际有差别
