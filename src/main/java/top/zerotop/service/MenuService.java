package top.zerotop.service;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import top.zerotop.domain.menu.Menu;
import top.zerotop.util.JsonUtils;
import top.zerotop.util.RestfulWrapper;
import top.zerotop.util.URLUtils;

import java.util.Map;

import static top.zerotop.global.constrant.URLConstrant.*;

@Service
public class MenuService {
    public Menu getMenu() {
        String url = URLUtils.getUrl(URL_MENU_GET);
        String res = (String) RestfulWrapper.getWrapper(url).get("result");
        return JsonUtils.toSubObject(res, "menu", Menu.class);
    }

    public String deleteMenu() {
        String url = URLUtils.getUrl(URL_MENU_DELETE);
        return  (String) RestfulWrapper.getWrapper(url).get("result");
    }


    public Map<String, String> createMenu(Menu menu) {
        String url = URLUtils.getUrl(URL_MENU_CREATE);
        String res = RestfulWrapper.postWrapper(url, JSON.toJSONString(menu));
        return JsonUtils.toObject(res, Map.class);
    }
}
