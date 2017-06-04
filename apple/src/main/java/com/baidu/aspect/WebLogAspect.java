package com.baidu.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class WebLogAspect {


    
    public void webLog(){
    	
    	
    }

    @Before("highlight()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

       String url = request.getRequestURL().toString();
       String method = request.getMethod();
       String remoteAddr = request.getRemoteAddr();
       String a=""+joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
       String array = Arrays.toString(joinPoint.getArgs());
       	System.out.println(url);
    }

    

}
