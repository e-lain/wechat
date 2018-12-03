package top.zerotop.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.zerotop.global.constrant.URLConstrant;
import top.zerotop.util.RestfulWapper;
import top.zerotop.wechat.TokenThread;

import java.io.IOException;

@RestController
@Api(value = "公众号素材", description = "公众号临时和永久素材管理")
@RequestMapping(value = "/media", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class MediaController extends BaseController {

    @PostMapping("media/upload/{type}")
    @ApiOperation(value = "上传临时文件")
    public String uploadMedia(@ApiParam(value = "文件")
                              @PathVariable("type") String type,
                              @ApiParam(value = "文件")
                              @RequestParam("file") MultipartFile file) {
        System.out.println("--create media--");
        if (file.isEmpty()) {
            return "";
        }

        String url = URLConstrant.URL_MEDIA_UPLOAD.replace("{ACCESS_TOKEN}", TokenThread.accessToken.getAccessToken())
                .replace("{TYPE}", type);
        System.out.println(url);
        String res = null;
        try {
            res = RestfulWapper.formPostWapper(url, null, file);
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @GetMapping("/media/get/{mediaId}")
    @ApiOperation(value = "获取公众号素材")
    public String listMedia(@ApiParam(value = "类型：image, voice, video, thumb")
                            @PathVariable("mediaId") String mediaId) {
        String url = URLConstrant.URL_MEDIA_GET.replace("{ACCESS_TOKEN}", TokenThread.accessToken.getAccessToken())
                .replace("{MEDIA_ID}", mediaId);
        String res = null;
        try {
            res = (String)RestfulWapper.getWapper(url).get("result");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(res);
        return res;
    }
}
