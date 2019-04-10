package com.xianguo.wechatpn.interfaces;

/**
 * 枚举基类 fastjson解码用
 * @author 鲜果
 * @date 2019年4月10日
 *
 */
public interface EnmuDecoderInterface {
	
	/**
	 * 获取枚举值
	 *
	 * @author 鲜果
	 * @param @return
	 * @date 2019年4月10日
	 * @return String
	 * @throws
	 */
	public String getValue();
	
	/**
	 * 更具key获取枚举实体
	 *
	 * @author 鲜果
	 * @param @param key
	 * @param @return
	 * @date 2019年4月10日
	 * @return T
	 * @throws
	 */
	public <T extends EnmuDecoderInterface> T getEnmuByKeyInterface(String key);
	
}
