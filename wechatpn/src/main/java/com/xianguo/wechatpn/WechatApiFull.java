package com.xianguo.wechatpn;

import java.io.InputStream;
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
	@SuppressWarnings("unchecked")
	public R execute(E e) {
		Object res = null;
		if (method == HttpRequestType.POST) {
			if(e instanceof WechatApiUploadStream) {
				res = Post(e);
			}else {
				String jsonStr = JSON.toJSONString(e);
				res = Post(jsonStr);
			}
		}else if(method == HttpRequestType.GET) {
			Map<String, Object> params = JSON.parseObject(JSON.toJSONString(e));
			res = Get(params);
		}
		if (res == null) {
			return null;
		}
		if(res instanceof InputStream) {
			return (R) res;
		}else {
			log.info(res.toString());
			R object = JSON.parseObject(res.toString(), resClass);
			return object;
		}
	}
	
	
}
