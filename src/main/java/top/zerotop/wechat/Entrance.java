package top.zerotop.wechat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.zerotop.Provider.WechatProvider;
import top.zerotop.util.DecriptUtils;

public class Entrance extends HttpServlet {
    private static final long serialVersionUID = 1L;


    private static Logger logger = LoggerFactory.getLogger(Entrance.class);

    /**
     * Token
     */
    private final String token = "zerotop";

    WechatProvider wechatService = new WechatProvider();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("开始签名校验");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        //排序 加密 校验签名
        String sortString = sort(token, timestamp, nonce);
        String mytoken = DecriptUtils.SHA1(sortString);
        if (null != mytoken && mytoken != "" && mytoken.equals(signature)) {
            logger.info("签名校验通过。");
            response.getWriter().println(echostr); //如果检验成功输出echostr，微信服务器接收到此输出，才会确认检验完成。
        } else {
            logger.info("签名校验失败。");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String responseMessage = wechatService.processRequest(request);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("");
            PrintWriter wout = response.getWriter();
            wout.print(responseMessage);
            wout.flush();
            wout.close();
        } catch (Exception e) {
            logger.info("error: " + e.getMessage());
        }
    }

    /**
     * 排序方法
     *
     * @param token
     * @param timestamp
     * @param nonce
     * @return
     */
    public static String sort(String token, String timestamp, String nonce) {

        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);

        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strArray) {
            stringBuilder.append(str);
        }

        return stringBuilder.toString();
    }
}
