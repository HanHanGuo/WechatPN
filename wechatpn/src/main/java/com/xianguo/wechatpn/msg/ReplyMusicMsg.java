package com.xianguo.wechatpn.msg;

import com.thoughtworks.xstream.XStream;
import com.xianguo.wechatpn.WechatReplyMsg;
import com.xianguo.wechatpn.enums.WechatMsgType;
import com.xianguo.wechatpn.utils.XmlUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 回复音乐消息
 * @author 鲜果
 * @date 2019年4月9日
 *
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper=false)
public class ReplyMusicMsg extends WechatReplyMsg {
	
	private Music Music;
	
	public ReplyMusicMsg() {
		super.setMsgType(WechatMsgType.MUSIC);
		try {
			this.Music = Music.getClass().newInstance();
		} catch (Exception e) {
			log.error("微信被动回复消息Music对象自动构造失败");
			log.error(e.getMessage(),e);
		}
	}
	
	@Override
	public String returnXml() {
		XStream xml = XmlUtils.GetXmlBean();
		xml.alias("Music", Music.class);
		xml.alias("xml", ReplyMusicMsg.class);
		return xml.toXML(this);
	}
	
	@Data
	public class Music{
		private  String Title;//音乐标题
		private  String Description;//音乐描述
		private  String MusicURL;//音乐链接
		private  String HQMusicUrl;//高质量音乐链接，WIFI环境优先使用该链接播放音乐
		private  String ThumbMediaId;//缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
	}
	
}
