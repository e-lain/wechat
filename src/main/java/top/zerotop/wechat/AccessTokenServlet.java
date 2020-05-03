package top.zerotop.wechat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@WebServlet(name = "AccessTokenServlet")
public class AccessTokenServlet extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(AccessTokenServlet.class);

	public void init() throws ServletException {
	    TokenThread.initAppInfo(getInitParameter("appid"), getInitParameter("appsecret"));

        logger.info(" ====> appId:{}, appSecret:{}",TokenThread.getAppId(),TokenThread.getAppSecret());
        new Thread(new TokenThread()).start(); //启动进程
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}