package com.xianguo.wechatpn;

import java.io.InputStream;
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
	@SuppressWarnings("unchecked")
	public R execute() {
		try {
			Object res = null;
			if (method == HttpRequestType.POST) {
				res = Post("");
			}else if(method == HttpRequestType.GET) {
				Map<String, Object> params = new HashMap<>();
				res = Get(params);
			}
			if (res == null) {
				return null;
			}
			if(res instanceof InputStream) {
				if(resClass.equals(WechatApiInputStream.class)) {
					WechatApiInputStream is = (WechatApiInputStream) resClass.newInstance();
					is.setInputStream((InputStream) res);
					return (R) is;
				}else {
					throw new RuntimeException("api返回类型不正确，请确定返回类型为WechatApiInputStream");
				}
			}else {
				log.info(res.toString());
				R object = JSON.parseObject(res.toString(), resClass);
				return object;
			}
		} catch (InstantiationException e) {
			log.info("execut方法返回class.newInstance出错");
			log.error(e.getMessage(),e);
			return null;
		} catch (IllegalAccessException e) {
			log.info("execut方法返回class.newInstance出错");
			log.error(e.getMessage(),e);
			return null;
		}
	}
	
}
