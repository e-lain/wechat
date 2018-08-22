package top.zerotop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import top.zerotop.wechat.tunnel.domain.article;
import top.zerotop.wechat.tunnel.domain.calucation;
import top.zerotop.wechat.tunnel.domain.catalog;
import top.zerotop.wechat.tunnel.service.TunnelService;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年5月20日上午10:51:18
 */
@Controller
@RequestMapping(value = "/tunnel")
public class TunnelController {

	@Autowired
	private TunnelService tunnelService;
	
	@RequestMapping(value = "/catalog")
	public ModelAndView getChapter(HttpServletRequest req ) {
		
		Byte isImportant =  Byte.parseByte(req.getParameter("isImportant"));
		
		List<catalog> cataloglist =  tunnelService.listCatalog(isImportant);
		List<catalog> tempcatalog = new ArrayList<catalog>();
		
		ModelAndView view = new ModelAndView("catalog");
		
		if(null != isImportant&& isImportant == 1) {
			for(catalog ca:cataloglist){
				if(ca.getIsImportant() == 1){
					tempcatalog.add(ca);
				}
			}
			view.addObject("catalog", tempcatalog);
		}
		else{
			view.addObject("catalog", cataloglist);
		}
		
		return view;
	}
	
	@RequestMapping(value = "/article/{id}")
	public ModelAndView getArticle(@PathVariable("id")long id, HttpServletRequest req ) {
		
		System.out.println(id);
		
		article article = tunnelService.getArticleById(id);
		System.out.println(article.toString());
		
		ModelAndView view = new ModelAndView("article");
		view.addObject("article", article);
		return view;
	}
	
	@RequestMapping(value = "/calculation/{id}")
	public ModelAndView getCalculation(@PathVariable("id")int id,HttpServletRequest req){
		
		calucation calculation = tunnelService.getCalucationById(id);
		
		System.out.println(calculation.toString());
		
		ModelAndView view = new ModelAndView("calculation");
		
		view.addObject("calculation", calculation);
		return view;
	}
}
