# wechat

wechat development


### 项目简介 

项目有关直接通过发送get请求即可获取数据的接口  
可直接在本地用js构造请求，前端界面正在写  
例如获取用户列表，直接获取token  
然后直接发送请求接口

### 服务器上部署：
  
项目可以直接打成war包放到服务器上  
需要再项目的下的web.xml中写入自微信公众号的appid和appsecret  
就可以直接运行  

项目是直接在maven项目中修改的 web.xml如下
 
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


### 技术栈：  

maven构建项目  

spring管理项目  
数据库： mysql  
连接池： druid  
  
添加接口测试工具： swagger-ui

### 备注

项目现在仅尝试部分接口，等之后会更对的进行尝试  
暂时使用微信公众号测试账号进行开发  
接口数量和实际有差别
