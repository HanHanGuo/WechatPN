package com.xianguo.wechatpn.interfaces;

/**
 * 微信接口是否调用成功接口
 * @author 鲜果
 * @date 2019年4月9日
 *
 */
public interface ApiIsSuccess {
	
	/**
	 * 接口是否调用成功
	 *
	 * @author 鲜果
	 * @param @return
	 * @date 2019年4月9日
	 * @return Boolean 返回true为成功，false为失败
	 * @throws
	 */
	public Boolean check();
	
}
