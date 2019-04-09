package com.xianguo.wechatpn.msg;

import com.thoughtworks.xstream.XStream;
import com.xianguo.wechatpn.WechatReplyMsg;
import com.xianguo.wechatpn.enums.WechatMsgType;
import com.xianguo.wechatpn.utils.XmlUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 回复图片消息
 * @author 鲜果
 * @date 2019年4月9日
 *
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper=false)
public class ReplyImageMsg extends WechatReplyMsg {
	
	private Image Image;//图片
	
	public ReplyImageMsg() {
		super.setMsgType(WechatMsgType.IMAGE);
		try {
			this.Image = Image.getClass().newInstance();
		} catch (Exception e) {
			log.error("微信被动回复消息Image对象自动构造失败");
			log.error(e.getMessage(),e);
		} 
	}
	
	@Override
	public String returnXml() {
		XStream xml = XmlUtils.GetXmlBean();
		xml.alias("Image", Image.class);
		xml.alias("xml", ReplyImageMsg.class);
		return xml.toXML(this);
	}
	
	@Data
	public class Image{
		private String MediaId;//通过素材管理中的接口上传多媒体文件，得到的id。
	}
}
