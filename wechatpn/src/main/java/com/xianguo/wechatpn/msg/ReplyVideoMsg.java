package com.xianguo.wechatpn.msg;

import com.thoughtworks.xstream.XStream;
import com.xianguo.wechatpn.WechatReplyMsg;
import com.xianguo.wechatpn.enums.WechatMsgType;
import com.xianguo.wechatpn.utils.XmlUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 回复视频消息
 * @author 鲜果
 * @date 2019年4月9日
 *
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper=false)
public class ReplyVideoMsg extends WechatReplyMsg {
	
	private Video Video;
	
	public ReplyVideoMsg() {
		super.setMsgType(WechatMsgType.VIDEO);
		try {
			this.Video = Video.getClass().newInstance();
		} catch (Exception e) {
			log.error("微信被动回复消息Video对象自动构造失败");
			log.error(e.getMessage(),e);
		}
	}
	
	@Override
	public String returnXml() {
		XStream xml = XmlUtils.GetXmlBean();
		xml.alias("Video", Video.class);
		xml.alias("xml", ReplyVideoMsg.class);
		return xml.toXML(this);
	}
	
	@Data
	public class Video{
		private String MediaId;//通过素材管理中的接口上传多媒体文件，得到的id
		private String Title;//视频消息的标题
		private String Description;//视频消息的描述
	}
	
}
