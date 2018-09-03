package top.zerotop.domain;

public class AccessToken {
	
	/**
	 * accessToken
	 */
	private static String accessToken;
	
	private static String jsapiTicket;
	
	/**
	 * token有效期
	 */
    private int expiresin;

    public static String getAccessToken() {
        return accessToken;
    }
 
    public static void setAccessToken(String accessToken) {
		AccessToken.accessToken = accessToken;
    }

	public static String getJsapiTicket() {
		return jsapiTicket;
	}

	public static void setJsapiTicket(String jsapiTicket) {
		AccessToken.jsapiTicket = jsapiTicket;
	}

	public int getExpiresin() {
        return expiresin;
    }
 
    public void setExpiresin(int expiresin) {
        this.expiresin = expiresin;
    }

}