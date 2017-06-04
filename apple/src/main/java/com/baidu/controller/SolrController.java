package com.baidu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baidu.entity.LogEntity;
import com.baidu.entity.Time;
import com.baidu.entity.User;
import com.baidu.service.AppleService;
import com.baidu.util.JXPageUtil;

@Controller
public class SolrController {
	private static final String URL = "http://192.168.126.128:8080/solr";
	
	@Autowired
	private AppleService appleService;
	
	@RequestMapping("list")
	public String list(HttpServletRequest req){
		User user=new User();
		
		user.setName("杨冠宇");
	
		user.setUid(1);
		List<User> list = new ArrayList<User>();
		list.add(user);
		req.setAttribute("list", list);
		
		return "list";
	}
	
	
	
	
	/**
	 * 
	 * 作者姓名：杨冠宇
	 * 2017年5月30日
	 * @return
	 * @throws Exception
	 * 功能：
	 */
/*	@RequestMapping("index")
	public String index() throws Exception{
		
		
		return "index";
	}*/
	

	@RequestMapping("hello")
	public String hello() throws Exception{
		
		SolrServer solrServer = new HttpSolrServer(URL);
		SolrInputDocument doc1 = new SolrInputDocument();
		doc1.setField("id", "1003");
		doc1.setField("name", "三河iphone7S手机");
		doc1.setField("price", "6000");
		doc1.setField("url", "/images/001.jpg");
		
		SolrInputDocument doc2 = new SolrInputDocument();
		doc2.setField("id", "1004");
		doc2.setField("name", "三里s6手机");
		doc2.setField("price", "5300");
		doc2.setField("url", "/images/002.jpg");
		//设置服务器保存信息并提交
		solrServer.add(doc1);
		//设置服务器保存信息并提交
		solrServer.add(doc2);
		solrServer.commit();
		return "hello";
	}
	@RequestMapping("highlight")
	
	public String highlight(HttpServletRequest req ,HttpServletResponse res,Integer currpage,String mohu) throws SolrServerException, IOException{
		SolrServer solrServer = new HttpSolrServer(URL);
 	    if(currpage==null){
 	    	currpage=1;
 	    }
 	    int size =3;
		SolrQuery params = new SolrQuery();
		if(mohu==null||mohu==""){
			params.setQuery("*:*");
		}else{
			
			params.setQuery("name:"+mohu+"");
		}
		System.out.println(currpage);
		
		params.set("start", (currpage-1)*size);
		params.set("rows", size);
		//设置高亮
/*		params.setHighlight(true);
		params.setHighlightFragsize(2);          //返回的字符个数  
		//高亮字段设置
		
		params.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀
		params.setHighlightSimplePost("</font>");//后缀
		params.addHighlightField("name");*/
 	    
        QueryResponse response = solrServer.query(params);
        //输出查询结果集
        SolrDocumentList list = response.getResults();
        List<User> ulist = new ArrayList<User>();
 	    //System.out.println(list);
 	    for (SolrDocument so : list) {
 	    	User user = new User();
 	    	String uid = (String) so.get("id");
 	    	String name = (String) so.get("name");
 	    	if(mohu!=null&&mohu!=""){
 	    		
 	    		name=name.replace(""+mohu+"", "<font color='red' escape='false'>"+mohu+"</font>");
 	    	}
 	    	float price = (float) so.get("price");
 	    	String url = (String) so.get("url");
			user.setUid(Integer.parseInt(uid));
			user.setName(name);
			user.setPrice(price);
			user.setUrl(url);
			
			ulist.add(user);
		}
 	    //System.out.println(ulist);
 	    req.setAttribute("list", ulist);
 	    
 	    HttpSession session = req.getSession();
 	    LogEntity log = (LogEntity) session.getAttribute("log");
 	    this.appleService.saveLog(log);
 	    

 	    //分页
 	    Integer total=ulist.size();
 	    int wish=total/size;
 	    JXPageUtil jx = new JXPageUtil("highlight",total,currpage,2); 
 	    jx.setBothnum(3);  
 	    String numpage = jx.showNumPage();  
 	    currpage = jx.getCurrpage();  
 	    session.setAttribute("currpage", currpage);  
 	    req.setAttribute("numpage",numpage);  
 	   req.setAttribute("mohu", mohu);
 	    
		return "index";
	}
	/**
	 * 
	 * 作者姓名：杨冠宇
	 * 2017年6月1日
	 * @param req
	 * @return
	 * 功能： Made 4 baidu.echarts
	 */
	@RequestMapping("echarts")
	public String echarts(HttpServletRequest req){
		List timea=new ArrayList();
		List count=new ArrayList();
		List<Time> list=this.appleService.findMessage();
		for (Time time : list) {
			timea.add(time.getTime());
			count.add(time.getAllcount());
		}
		JSONArray json1 = new JSONArray(timea);
		JSONArray json2 = new JSONArray(count);
		
		String jsons1 = json1.toString();
		String jsons2 = json2.toString();
		req.setAttribute("list1", jsons1);
		req.setAttribute("list2", jsons2);
		
		return "echarts";
	}
 
}
