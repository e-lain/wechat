package top.zerotop.global.constrant;

public class LoginURLConstrant {
	public static final String URL_GET_TOKEN = URLConstrant.BASE_URL + "/token?grant_type=client_credential&appid={appid}&secret={app_secret}";
	
	public static final String URL_GET_TICKET = URLConstrant.BASE_URL + "/ticket/getticket?access_token={access_token}&type=jsapi";
}
