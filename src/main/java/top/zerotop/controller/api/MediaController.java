package top.zerotop.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zerotop.global.constrant.URLConstrant;
import top.zerotop.util.RestfulWapper;
import top.zerotop.wechat.TokenThread;

import java.io.IOException;

@RestController
@Api(value = "公众号素材", description = "公众号素材管理")
@RequestMapping("/media")
public class MediaController extends BaseController{

    @GetMapping("/get/{mediaId}")
    @ApiOperation(value = "获取公众号素材")
    public String listMedia(@ApiParam(value = "类型：image, voice, video, thumb")
            @PathVariable("mediaId")String mediaId) {
        String url = URLConstrant.URL_MEDIA_GET.replace("{ACCESS_TOKEN}", TokenThread.accessToken.getAccessToken())
                .replace("{MEDIA_ID}", mediaId) ;
        String res = null;
        try {
            res = RestfulWapper.getWapper(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(res);
        return res;
    }
}
