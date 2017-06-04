package com.baidu.aspect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.baidu.entity.LogEntity;
import com.baidu.service.AppleService;

@WebServlet
public class myHandleInterceptor implements HandlerInterceptor{
	@Autowired
	private AppleService appleService;
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView model)
			throws Exception {

             
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		// TODO 这里获取了IP
		//获取ip，由于没啥人访问，就从网上找点，来个随机数
		//String ip = req.getRemoteAddr();
		String city ="";
		String ips [] = {"202.38.64.1","202.38.96.6","202.38.135.7","210.35.104.0","202.101.105.160"};
		Random random = new Random();
		int i = random.nextInt(5);
		
		System.out.println(ips[i]);
		
		// TODO 这里获取了城市
        URL url = new URL("http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&ip="+ips[i]);  
        URLConnection conn = url.openConnection();  
        conn.addRequestProperty("host", "api.map.baidu.com");
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));  
        String line = null;  
        StringBuffer result = new StringBuffer();  
        while ((line = reader.readLine()) != null) {  
            result.append(line);  
        }  
        reader.close();  
        String ipAddr = result.toString(); 
        //System.out.println(ipAddr);
        try {  
            JSONObject obj1= new JSONObject(ipAddr);  
            JSONObject obj2= new JSONObject(obj1.get("content").toString());  
            JSONObject obj3= new JSONObject(obj2.get("address_detail").toString()); 
            city = obj3.get("city").toString(); 
            System.out.println(city);
            
        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
          
        // TODO 这里获取了到底是什么浏览器
        String agent=req.getHeader("User-Agent").toLowerCase();//从请求头里拿出了浏览器的信息
        //根据浏览器关键字来判断浏览器
        String browser="";
        
        	  if(agent.indexOf("msie")>0){
        		  browser= "浏览器是：ie";
        	  }
        	  else if(agent.indexOf("opera")>0){
        		  browser= "浏览器是：opera";
        	   }
        	  else if(agent.indexOf("firefox")>0){
        		  browser= "浏览器是：firefox";
        	  }else if(agent.indexOf("webkit")>0){
        		  browser= "浏览器是：goole";
        	  }else if(agent.indexOf("gecko")>0 && agent.indexOf("rv:11")>0){
        		  browser= "浏览器是：ie11";
        	  }else{
        		  browser= "我们这里不知道您用的是什么浏览器";
        	  }
        	  System.out.println(browser);
        	  
        	  LogEntity log = new LogEntity(ips[i],city,browser,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        	  //this.appleService.saveLog(log);

	           /*   if (appleService == null) {//解决service为null无法注入问题 
	                 BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(req.getServletContext()); 
	                 appleService = (AppleService) factory.getBean("appleService"); 
	              } 
	              appleService.saveLog(log); */
	              //AppleServiceImp imp = new AppleServiceImp();
        	  		HttpSession session = req.getSession();
        	  		session.setAttribute("log", log);
	  
	return true;
	}

	

}
