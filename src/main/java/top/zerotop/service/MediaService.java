package top.zerotop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.zerotop.util.RestfulWrapper;
import top.zerotop.util.URLUtils;

import java.util.Map;

import static top.zerotop.global.constrant.URLConstrant.URL_MEDIA_GET;
import static top.zerotop.global.constrant.URLConstrant.URL_MEDIA_UPLOAD;

/**
 * Created by:zerotop  date:2020/5/2
 */
@Service
public class MediaService {
    private static final Logger logger = LoggerFactory.getLogger(MediaService.class);

    public String uploadMedia(MultipartFile file, String type) {
        try {
            String url = URLUtils.getUrl(URL_MEDIA_UPLOAD).replace("{type}", type);
            return RestfulWrapper.formPostWrapper(url, null, file);
        } catch (Exception e) {
            logger.warn("upload media failed. course: ", e);
        }
        return null;
    }

    public Map<String, Object> listMedia(String mediaId) {
        String url = URLUtils.getUrl(URL_MEDIA_GET).replace("{media_id}", mediaId);

        return RestfulWrapper.getWrapper(url);
    }
}
