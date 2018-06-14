package top.zerotop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年5月11日下午6:50:47
 */
@Controller
public class MusicController {

	@RequestMapping(value="/music/list", produces="application/json;charset=utf-8")
	@ResponseBody
	public String musicList(HttpServletRequest req){
		
		System.out.println(req.getRequestURL().toString());
		
		String sMinisign = req.getSession().getAttribute("minisign").toString();
		String minisign = req.getParameter("minisigan");
		
		System.out.println("session_id:"+req.getSession().getId());
		System.out.println("session minisign:"+sMinisign);
		System.out.println("minisign: "+minisign);
		
		
		if(sMinisign.equals(minisign))
			return "musiclist";
		else
			return "musicfail";
	}
}
