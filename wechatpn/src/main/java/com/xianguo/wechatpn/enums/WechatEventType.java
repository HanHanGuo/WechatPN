package com.xianguo.wechatpn.enums;

import com.xianguo.wechatpn.interfaces.EnmuDecoderInterface;

/**
 * 微信事件类型
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
public enum WechatEventType implements EnmuDecoderInterface {
	SUBSCRIBE("subscribe"),//关注，以及扫描带参数二维码关注事件
	UNSUBSCRIBE("unsubscribe"),//取消关注
	SCAN("SCAN"),//用户已关注是扫描带参数二维码关注公众号事件
	LOCATION("LOCATION"),//用户同意上报地理位置后，每次进入公众号会话时，都会在进入时上报地理位置，或在进入会话后每5秒上报一次地理位置，公众号可以在公众平台网站中修改以上设置。上报地理位置时，微信会将上报地理位置事件推送到开发者填写的URL。
	MENU_CLICK("CLICK"),//用户点击自定义菜单后，微信会把点击事件推送给开发者，请注意，点击菜单弹出子菜单，不会产生上报。
	CLICK("CLICK"),//点击菜单拉取消息时的事件推送
	VIEW("VIEW"),//点击菜单跳转链接时的事件推送
	SCANCODE_PUSH("scancode_push"),//扫码推事件的事件推送
	SCANCODE_WAITMSG("scancode_waitmsg"),//扫码推事件且弹出“消息接收中”提示框的事件推送
	PIC_SYSPHOTO("pic_sysphoto"),//弹出系统拍照发图的事件推送
	PIC_PHOTO_OR_ALBUM("pic_photo_or_album"),//弹出拍照或者相册发图的事件推送
	PIC_WEIXIN("pic_weixin"),//弹出微信相册发图器的事件推送
	LOCATION_SELECT("location_select"),//弹出地理位置选择器的事件推送
	VIEW_MINIPROGRAM("view_miniprogram");//点击菜单跳转小程序的事件推送
	
	
	private final String value;
	
	private WechatEventType(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return this.value;
	}
	
	public static WechatEventType getEnmuByKey(String key) {
		WechatEventType[] enums = WechatEventType.values();
		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getValue().equals(key)) {
				return enums[i];
			}
		}
		return null;
	}


	@Override
	@SuppressWarnings("unchecked")
	public WechatEventType getEnmuByKeyInterface(String key) {
		return getEnmuByKey(key);
	}
}
