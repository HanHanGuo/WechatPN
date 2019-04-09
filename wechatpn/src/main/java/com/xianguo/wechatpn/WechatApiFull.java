package com.xianguo.wechatpn;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.xianguo.wechatpn.enums.HttpRequestType;

import lombok.extern.slf4j.Slf4j;

/**
 * 有参数api请求器
 * @author 鲜果
 * @date 2019年4月9日
 *
 * @param <E>
 * @param <R>
 */
@Slf4j
public class WechatApiFull<E,R> extends WechatApi<R> {
	
	public WechatApiFull(Class<? extends R> resClass, String url, HttpRequestType method) {
		super(resClass, url, method);
	}

	/**
	 * 带参请求
	 *
	 * @author 鲜果
	 * @param @param e 参数
	 * @param @return 返回封装好的实体
	 * @date 2019年4月9日
	 * @return R 返回封装好的实体
	 * @throws
	 */
	public R execute(E e) {
		String jsonRes = "";
		if (method == HttpRequestType.POST) {
			String jsonStr = JSON.toJSONString(e);
			jsonRes = Post(jsonStr);
		}else if(method == HttpRequestType.GET) {
			Map<String, Object> params = JSON.parseObject(JSON.toJSONString(e));
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
