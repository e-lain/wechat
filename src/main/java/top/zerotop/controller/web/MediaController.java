package top.zerotop.controller.web;

import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.zerotop.service.MediaService;
import top.zerotop.util.Result;

import java.util.Map;

@RestController
@Api(value = "公众号素材相关API", tags = "公众号临时和永久素材管理")
@RequestMapping(value = "/wechat/api/media", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class MediaController extends BaseController {
    @Autowired
    private MediaService mediaService;

    @PostMapping("/upload/{type}")
    @ApiOperation(value = "上传临时文件")
    public Result<String> uploadMedia(@ApiParam(value = "文件类型") @PathVariable("type") String type,
                                      @ApiParam(value = "文件") @RequestParam("file") MultipartFile file) {
        Assert.isTrue(Strings.isNullOrEmpty(type), "文件不能为空");
        Assert.isTrue(!file.isEmpty(), "文件不能为空");
        return Result.make(mediaService.uploadMedia(file, type));
    }

    @GetMapping("/get/{mediaId}")
    @ApiOperation(value = "获取公众号素材")
    public Result<Map<String, Object>> listMedia(@ApiParam(value = "类型:image/voice/video/thumb") @PathVariable("mediaId") String mediaId) {
        return Result.make(mediaService.listMedia(mediaId));
    }
}
