package top.zerotop.wechat;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import top.zerotop.Provider.WechatProvider;
import top.zerotop.util.DecriptUtils;

public class Entrance extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(Entrance.class);

    // Token
    private final String personalToken = "zerotop";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("======> 开始签名校验");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        //排序 加密 校验签名
        String sortString = sort(personalToken, timestamp, nonce);
        String token = DecriptUtils.SHA1(sortString);
        if (StringUtils.hasText(token) && token.equals(signature)) {
            logger.info("<======签名校验通过...");
            response.getWriter().println(echostr); //如果检验成功输出echostr，微信服务器接收到此输出，才会确认检验完成。
        } else {
            logger.info("<======签名校验失败...");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.info(" ===> new post request uri: {}", request.getRequestURL().toString());
            String responseMessage = WechatProvider.processRequest(request);
            if (StringUtils.hasText(responseMessage)) {
                response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
                PrintWriter writer = response.getWriter();
                writer.print(responseMessage);
                writer.flush();
                writer.close();
            } else {
                logger.warn(" ===> nothing response.");
            }
        } catch (Exception e) {
            logger.warn(" catch exception when process weChat request: ", e);
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
    private static String sort(String token, String timestamp, String nonce) {
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);

        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strArray) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }
}
