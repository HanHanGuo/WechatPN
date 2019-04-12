package com.xianguo.wechatpn;

import java.util.HashMap;
import java.util.Map;

/**
 * 错误码对照表
 * @author 鲜果
 * @date 2019年4月12日
 *
 */
public class WechatApiErrorMsg {
	
	private static Map<String, String> errorMsgMap;
	
	/**
	 * 根据errCode获取错误信息
	 *
	 * @author 鲜果
	 * @param @param errorCode
	 * @param @return
	 * @date 2019年4月12日
	 * @return String
	 * @throws
	 */
	public static String getMsg(String errorCode) {
		if(errorMsgMap != null) {
			return errorMsgMap.get(errorCode);
		}
		errorMsgMap = new HashMap<>();
		errorMsgMap.put("-1","系统繁忙，此时请开发者稍候再试");
		errorMsgMap.put("0","请求成功");
		errorMsgMap.put("null","请求成功");
		errorMsgMap.put("NULL","请求成功");
		errorMsgMap.put("","请求成功");
		errorMsgMap.put(null,"请求成功");
		errorMsgMap.put("40001","获取 access_token 时 AppSecret 错误，或者 access_token 无效。请开发者认真比对 AppSecret 的正确性，或查看是否正在为恰当的公众号调用接口");
		errorMsgMap.put("40002","不合法的凭证类型");
		errorMsgMap.put("40003","不合法的 OpenID ，请开发者确认 OpenID （该用户）是否已关注公众号，或是否是其他公众号的 OpenID");
		errorMsgMap.put("40004","不合法的媒体文件类型");
		errorMsgMap.put("40005","不合法的文件类型");
		errorMsgMap.put("40006","不合法的文件大小");
		errorMsgMap.put("40007","不合法的媒体文件 id");
		errorMsgMap.put("40008","不合法的消息类型");
		errorMsgMap.put("40009","不合法的图片文件大小");
		errorMsgMap.put("40010","不合法的语音文件大小");
		errorMsgMap.put("40011","不合法的视频文件大小");
		errorMsgMap.put("40012","不合法的缩略图文件大小");
		errorMsgMap.put("40013","不合法的 AppID ，请开发者检查 AppID 的正确性，避免异常字符，注意大小写");
		errorMsgMap.put("40014","不合法的 access_token ，请开发者认真比对 access_token 的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口");
		errorMsgMap.put("40015","不合法的菜单类型");
		errorMsgMap.put("40016","不合法的按钮个数");
		errorMsgMap.put("40017","不合法的按钮个数");
		errorMsgMap.put("40018","不合法的按钮名字长度");
		errorMsgMap.put("40019","不合法的按钮 KEY 长度");
		errorMsgMap.put("40020","不合法的按钮 URL 长度");
		errorMsgMap.put("40021","不合法的菜单版本号");
		errorMsgMap.put("40022","不合法的子菜单级数");
		errorMsgMap.put("40023","不合法的子菜单按钮个数");
		errorMsgMap.put("40024","不合法的子菜单按钮类型");
		errorMsgMap.put("40025","不合法的子菜单按钮名字长度");
		errorMsgMap.put("40026","不合法的子菜单按钮 KEY 长度");
		errorMsgMap.put("40027","不合法的子菜单按钮 URL 长度");
		errorMsgMap.put("40028","不合法的自定义菜单使用用户");
		errorMsgMap.put("40029","无效的 oauth_code");
		errorMsgMap.put("40030","不合法的 refresh_token");
		errorMsgMap.put("40031","不合法的 openid 列表");
		errorMsgMap.put("40032","不合法的 openid 列表长度");
		errorMsgMap.put("40033","不合法的请求字符，不能包含 \\uxxxx 格式的字符");
		errorMsgMap.put("40035","不合法的参数");
		errorMsgMap.put("40038","不合法的请求格式");
		errorMsgMap.put("40039","不合法的 URL 长度");
		errorMsgMap.put("40048","无效的url");
		errorMsgMap.put("40050","不合法的分组 id");
		errorMsgMap.put("40051","分组名字不合法");
		errorMsgMap.put("40060","删除单篇图文时，指定的 article_idx 不合法");
		errorMsgMap.put("40117","分组名字不合法");
		errorMsgMap.put("40118","media_id 大小不合法");
		errorMsgMap.put("40119","button 类型错误");
		errorMsgMap.put("40120","button 类型错误");
		errorMsgMap.put("40121","不合法的 media_id 类型");
		errorMsgMap.put("40125","无效的appsecret");
		errorMsgMap.put("40132","微信号不合法");
		errorMsgMap.put("40137","不支持的图片格式");
		errorMsgMap.put("40155","请勿添加其他公众号的主页链接");
		errorMsgMap.put("40163","oauth_code已使用");
		errorMsgMap.put("41001","缺少 access_token 参数");
		errorMsgMap.put("41002","缺少 appid 参数");
		errorMsgMap.put("41003","缺少 refresh_token 参数");
		errorMsgMap.put("41004","缺少 secret 参数");
		errorMsgMap.put("41005","缺少多媒体文件数据");
		errorMsgMap.put("41006","缺少 media_id 参数");
		errorMsgMap.put("41007","缺少子菜单数据");
		errorMsgMap.put("41008","缺少 oauth code");
		errorMsgMap.put("41009","缺少 openid");
		errorMsgMap.put("42001","access_token 超时，请检查 access_token 的有效期，请参考基础支持 - 获取 access_token 中，对 access_token 的详细机制说明");
		errorMsgMap.put("42002","refresh_token 超时");
		errorMsgMap.put("42003","oauth_code 超时");
		errorMsgMap.put("42007","用户修改微信密码， accesstoken 和 refreshtoken 失效，需要重新授权");
		errorMsgMap.put("43001","需要 GET 请求");
		errorMsgMap.put("43002","需要 POST 请求");
		errorMsgMap.put("43003","需要 HTTPS 请求");
		errorMsgMap.put("43004","需要接收者关注");
		errorMsgMap.put("43005","需要好友关系");
		errorMsgMap.put("43019","需要将接收者从黑名单中移除");
		errorMsgMap.put("44001","多媒体文件为空");
		errorMsgMap.put("44002","POST 的数据包为空");
		errorMsgMap.put("44003","图文消息内容为空");
		errorMsgMap.put("44004","文本消息内容为空");
		errorMsgMap.put("45001","多媒体文件大小超过限制");
		errorMsgMap.put("45002","消息内容超过限制");
		errorMsgMap.put("45003","标题字段超过限制");
		errorMsgMap.put("45004","描述字段超过限制");
		errorMsgMap.put("45005","链接字段超过限制");
		errorMsgMap.put("45006","图片链接字段超过限制");
		errorMsgMap.put("45007","语音播放时间超过限制");
		errorMsgMap.put("45008","图文消息超过限制");
		errorMsgMap.put("45009","接口调用超过限制");
		errorMsgMap.put("45010","创建菜单个数超过限制");
		errorMsgMap.put("45011","API 调用太频繁，请稍候再试");
		errorMsgMap.put("45015","回复时间超过限制");
		errorMsgMap.put("45016","系统分组，不允许修改");
		errorMsgMap.put("45017","分组名字过长");
		errorMsgMap.put("45018","分组数量超过上限");
		errorMsgMap.put("45047","客服接口下行条数超过上限");
		errorMsgMap.put("46001","不存在媒体数据");
		errorMsgMap.put("46002","不存在的菜单版本");
		errorMsgMap.put("46003","不存在的菜单数据");
		errorMsgMap.put("46004","不存在的用户");
		errorMsgMap.put("47001","解析 JSON/XML 内容错误");
		errorMsgMap.put("48001","api 功能未授权，请确认公众号已获得该接口，可以在公众平台官网 - 开发者中心页中查看接口权限");
		errorMsgMap.put("48002","粉丝拒收消息（粉丝在公众号选项中，关闭了 “ 接收消息 ” ）");
		errorMsgMap.put("48004","api 接口被封禁，请登录 mp.weixin.qq.com 查看详情");
		errorMsgMap.put("48005","api 禁止删除被自动回复和自定义菜单引用的素材");
		errorMsgMap.put("48006","api 禁止清零调用次数，因为清零次数达到上限");
		errorMsgMap.put("48008","没有该类型消息的发送权限");
		errorMsgMap.put("50001","用户未授权该 api");
		errorMsgMap.put("50002","用户受限，可能是违规后接口被封禁");
		errorMsgMap.put("50005","用户未关注公众号");
		errorMsgMap.put("61451","参数错误 (invalid parameter)");
		errorMsgMap.put("61452","无效客服账号 (invalid kf_account)");
		errorMsgMap.put("61453","客服帐号已存在 (kf_account exsited)");
		errorMsgMap.put("61454","客服帐号名长度超过限制 ( 仅允许 10 个英文字符，不包括 @ 及 @ 后的公众号的微信号 )(invalid kf_acount length)");
		errorMsgMap.put("61455","客服帐号名包含非法字符 ( 仅允许英文 + 数字 )(illegal character in kf_account)");
		errorMsgMap.put("61456","客服帐号个数超过限制 (10 个客服账号 )(kf_account count exceeded)");
		errorMsgMap.put("61457","无效头像文件类型 (invalid file type)");
		errorMsgMap.put("61450","系统错误 (system error)");
		errorMsgMap.put("61500","日期格式错误");
		errorMsgMap.put("63001","部分参数为空");
		errorMsgMap.put("63002","无效的签名");
		errorMsgMap.put("65301","不存在此 menuid 对应的个性化菜单");
		errorMsgMap.put("65302","没有相应的用户");
		errorMsgMap.put("65303","没有默认菜单，不能创建个性化菜单");
		errorMsgMap.put("65304","MatchRule 信息为空");
		errorMsgMap.put("65305","个性化菜单数量受限");
		errorMsgMap.put("65306","不支持个性化菜单的帐号");
		errorMsgMap.put("65307","个性化菜单信息为空");
		errorMsgMap.put("65308","包含没有响应类型的 button");
		errorMsgMap.put("65309","个性化菜单开关处于关闭状态");
		errorMsgMap.put("65310","填写了省份或城市信息，国家信息不能为空");
		errorMsgMap.put("65311","填写了城市信息，省份信息不能为空");
		errorMsgMap.put("65312","不合法的国家信息");
		errorMsgMap.put("65313","不合法的省份信息");
		errorMsgMap.put("65314","不合法的城市信息");
		errorMsgMap.put("65316","该公众号的菜单设置了过多的域名外跳（最多跳转到 3 个域名的链接）");
		errorMsgMap.put("65317","不合法的 URL");
		errorMsgMap.put("87009","无效的签名");
		errorMsgMap.put("9001001","POST 数据参数不合法");
		errorMsgMap.put("9001002","远端服务不可用");
		errorMsgMap.put("9001003","Ticket 不合法");
		errorMsgMap.put("9001004","获取摇周边用户信息失败");
		errorMsgMap.put("9001005","获取商户信息失败");
		errorMsgMap.put("9001006","获取 OpenID 失败");
		errorMsgMap.put("9001007","上传文件缺失");
		errorMsgMap.put("9001008","上传素材的文件类型不合法");
		errorMsgMap.put("9001009","上传素材的文件尺寸不合法");
		errorMsgMap.put("9001010","上传失败");
		errorMsgMap.put("9001020","帐号不合法");
		errorMsgMap.put("9001021","已有设备激活率低于 50% ，不能新增设备");
		errorMsgMap.put("9001022","设备申请数不合法，必须为大于 0 的数字");
		errorMsgMap.put("9001023","已存在审核中的设备 ID 申请");
		errorMsgMap.put("9001024","一次查询设备 ID 数量不能超过 50");
		errorMsgMap.put("9001025","设备 ID 不合法");
		errorMsgMap.put("9001026","页面 ID 不合法");
		errorMsgMap.put("9001027","页面参数不合法");
		errorMsgMap.put("9001028","一次删除页面 ID 数量不能超过 10");
		errorMsgMap.put("9001029","页面已应用在设备中，请先解除应用关系再删除");
		errorMsgMap.put("9001030","一次查询页面 ID 数量不能超过 50");
		errorMsgMap.put("9001031","时间区间不合法");
		errorMsgMap.put("9001032","保存设备与页面的绑定关系参数错误");
		errorMsgMap.put("9001033","门店 ID 不合法");
		errorMsgMap.put("9001034","设备备注信息过长");
		errorMsgMap.put("9001035","设备申请参数不合法");
		errorMsgMap.put("9001036","查询起始值 begin 不合法");
		return errorMsgMap.get(errorCode);
	}
	
}
