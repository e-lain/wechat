package top.zerotop.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zerotop.service.UserAnalysisService;
import top.zerotop.util.Result;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Created by:zerotop  date:2019/7/8
 */
@RestController
@Api(value = "用户数据分析接口", description = "用户数据分析接口")
@RequestMapping(value = "/menu", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class UserAnalysisController {
    @Autowired
    private UserAnalysisService userAnalysisService;

    @GetMapping("/getUserSummary")
    @ApiOperation(value = "获取当前公众号菜单")
    public Result getUserSummary(@ApiParam(value = "开始时间")
                                                    @RequestParam String beginTime,
                                                    @ApiParam(value = "结束时间")
                                                    @RequestParam String endTime) {
        try {
            LocalDate begin = LocalDate.parse(beginTime);
            LocalDate end = LocalDate.parse(endTime);
        } catch (DateTimeParseException e) {
            return Result.error("开始时间结束时间无法解析");
        }

        return Result.make(userAnalysisService.getUserSummary(beginTime, endTime));
    }

}
