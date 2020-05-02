package top.zerotop.service;

import org.springframework.stereotype.Service;
import top.zerotop.domain.user.DateRange;
import top.zerotop.domain.user.UserSummary;
import top.zerotop.util.JsonUtils;
import top.zerotop.util.RestfulWrapper;
import top.zerotop.util.URLUtils;

import java.util.List;

import static top.zerotop.global.constrant.URLConstrant.URL_USER_SUMMARY;

/**
 * Created by:zerotop  date:2019/7/8
 */
@Service
public class UserAnalysisService {
    public List<UserSummary> getUserSummary(String begin, String end) {
        DateRange timeRange = new DateRange(begin, end);

        String url = URLUtils.getUrl(URL_USER_SUMMARY);
        String res = RestfulWrapper.postWrapper(url, JsonUtils.toJson(timeRange));
        System.out.println(res);
        return JsonUtils.fromJson(res, "list", UserSummary.class);
    }
}
