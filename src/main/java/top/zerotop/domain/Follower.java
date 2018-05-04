package top.zerotop.domain;

import java.util.List;

public class Follower {
	
	/**
	 * 关注该公众号的总用户数
	 */
	private int total;
	
	/**
	 * 拉取的openid的个数
	 */
	private int count;
	
	/**
	 * opeinid 
	 */
	private OpenIdList data;
	
	/**
	 *拉取列表最后一个 用户的openid
	 */
	private String next_openid;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public OpenIdList getData() {
		return data;
	}
	public void setData(OpenIdList data) {
		this.data = data;
	}
	public String getNext_openid() {
		return next_openid;
	}
	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
	
	class OpenIdList {

		public List<String> openid;

		public List<String> getOpenid() {
			return openid;
		}

		public void setOpenid(List<String> openid) {
			this.openid = openid;
		}
		
	}

	@Override
	public String toString() {
		return "Follower [total=" + total + ", count=" + count + ", data=" + data + ", next_openid=" + next_openid
				+ "]";
	}
	
}
