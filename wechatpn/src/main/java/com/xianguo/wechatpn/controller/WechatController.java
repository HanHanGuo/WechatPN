package com.xianguo.wechatpn.controller;

import java.io.InputStream;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xianguo.wechatpn.handle.Handle;
import com.xianguo.wechatpn.utils.Decript;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WechatController {
	
	private final String token = "JqQDY3gcQ2ydEl5tCxNZJB6aeOdrkHr6";
	
	@Autowired
	private Handle handle;
	
	@RequestMapping("/WechatUrl")
	public String WechatUrl(HttpServletRequest request) throws Exception {
		InputStream is = request.getInputStream();
		StringBuilder sb = new StringBuilder();
		byte[] temp = new byte[1024];
		int length = -1;
		while((length = is.read(temp)) != -1) {
			sb.append(new String(temp), 0, length);
		}
		String xml = sb.toString();//读取微信xml信息
		log.info(xml);
		if(xml != null && !"".equals(xml)) {//如果是xml消息
			return handle.HandleHead(sb.toString());//交由处理器处理
		}
		
		
		
		
		
		
		
		
		System.out.println("开始签名校验");
	    String signature = request.getParameter("signature");
	    String timestamp = request.getParameter("timestamp");
	    String nonce = request.getParameter("nonce");
	    String echostr = request.getParameter("echostr");
	    
	    String[] strArray = { token, timestamp, nonce };
	    Arrays.sort(strArray);
	 
	    StringBuilder sbuilder = new StringBuilder();
	    for (String str : strArray) {
	        sbuilder.append(str);
	    }
	    
	    //加密
	    String mytoken = Decript.SHA1(sbuilder.toString());
	    
	    //校验签名
	    if (mytoken != null && mytoken != "" && mytoken.equals(signature)) {
	        System.out.println("签名校验通过。");
	        return echostr;//如果检验成功输出echostr，微信服务器接收到此输出，才会确认检验完成。
	    } else {
	        System.out.println("签名校验失败。");
	    }
		
		
		return "";
	}
	
}
