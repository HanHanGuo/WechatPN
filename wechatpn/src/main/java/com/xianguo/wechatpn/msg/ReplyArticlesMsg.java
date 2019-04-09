package com.xianguo.wechatpn.msg;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.xianguo.wechatpn.WechatReplyMsg;
import com.xianguo.wechatpn.enums.WechatMsgType;
import com.xianguo.wechatpn.utils.XmlUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 回复图文消息
 * @author 鲜果
 * @date 2019年4月9日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReplyArticlesMsg extends WechatReplyMsg {
	
	private String ArticleCount;//消息个数
	private List<Articles> Articles;//消息
	
	public ReplyArticlesMsg() {
		super.setMsgType(WechatMsgType.NEWS);
		Articles = new ArrayList<>();
	}
	
	@Override
	public String returnXml() {
		XStream xml = XmlUtils.GetXmlBean();
		xml.alias("Articles", List.class);
		xml.alias("item", Articles.class);
		xml.alias("xml", ReplyArticlesMsg.class);
		return xml.toXML(this);
	}
	
	@Data
	public class Articles{
		private String Title;//图文消息标题
		private String Description;//图文消息描述
		private String PicUrl;//图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
		private String Url;//点击图文消息跳转链接
	}
}
