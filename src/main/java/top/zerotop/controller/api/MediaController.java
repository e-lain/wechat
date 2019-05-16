package top.zerotop.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.zerotop.global.constrant.URLConstrant;
import top.zerotop.util.RestfulWapper;
import top.zerotop.util.Result;
import top.zerotop.util.URLUtils;
import top.zerotop.wechat.TokenThread;

import java.util.Map;

import static top.zerotop.global.constrant.URLConstrant.URL_MEDIA_GET;
import static top.zerotop.global.constrant.URLConstrant.URL_MEDIA_UPLOAD;

@RestController
@Api(value = "公众号素材", description = "公众号临时和永久素材管理")
@RequestMapping(value = "/media", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class MediaController extends BaseController {
    @PostMapping("media/upload/{type}")
    @ApiOperation(value = "上传临时文件")
    public Result<String> uploadMedia(@ApiParam(value = "文件")
                                      @PathVariable("type") String type,
                                      @ApiParam(value = "文件")
                                      @RequestParam("file") MultipartFile file) {
        Assert.isTrue(!file.isEmpty(), "文件不能为空");

        String url = URLUtils.getUrl(URL_MEDIA_UPLOAD).replace("{TYPE}", type);
        String res = null;
        try {
            res = RestfulWapper.formPostWapper(url, null, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.make(res);
    }

    @GetMapping("/media/get/{mediaId}")
    @ApiOperation(value = "获取公众号素材")
    public Result<Map<String, Object>> listMedia(@ApiParam(value = "类型：image, voice, video, thumb")
                                                 @PathVariable("mediaId") String mediaId) {
        String url = URLUtils.getUrl(URL_MEDIA_GET).replace("{MEDIA_ID}", mediaId);
        return Result.make(RestfulWapper.getWapper(url));
    }
}
