package com.xianguo.wechatpn;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.xianguo.wechatpn.enums.HttpRequestType;

import lombok.extern.slf4j.Slf4j;

/**
 * 无参数api请求器
 * @author 鲜果
 * @date 2019年4月9日
 *
 * @param <R>
 */
@Slf4j
public class WechatApiDefect<R> extends WechatApi<R> {
	
	public WechatApiDefect(Class<? extends R> resClass, String url, HttpRequestType method) {
		super(resClass, url, method);
	}

	/**
	 * 无参数请求
	 *
	 * @author 鲜果
	 * @param @return
	 * @date 2019年4月9日
	 * @return R
	 * @throws
	 */
	public R execute() {
		String jsonRes = "";
		if (method == HttpRequestType.POST) {
			jsonRes = Post("");
		}else if(method == HttpRequestType.GET) {
			Map<String, Object> params = new HashMap<>();
			jsonRes = Get(params);
		}
		if (jsonRes == null) {
			return null;
		}
		log.info(jsonRes);
		R object = JSON.parseObject(jsonRes, resClass);
		return object;
	}
	
}
