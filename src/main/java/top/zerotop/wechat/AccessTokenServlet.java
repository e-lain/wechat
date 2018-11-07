package top.zerotop.wechat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AccessTokenServlet")
public class AccessTokenServlet extends HttpServlet {
	private static final long serialVersionUID = 4246161980523214021L;

	public void init() throws ServletException {
        TokenThread.appId = getInitParameter("appid");
        TokenThread.appSecret = getInitParameter("appsecret");
        System.out.println("appid:"+TokenThread.appId);
        System.out.println("appSecret:"+TokenThread.appSecret);
        new Thread(new TokenThread()).start(); //启动进程
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}