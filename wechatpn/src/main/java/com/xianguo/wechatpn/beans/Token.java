package com.xianguo.wechatpn.beans;

import lombok.Data;

@Data
public class Token {
	/**
	 * 获取到的凭证
	 */
	private String access_token;
	/**
	 * 凭证有效时间，单位：秒
	 */
	private String expires_in;
	/**
	 * 错误码
	 */
	private String errcode;
	/**
	 * 错误信息
	 */
	private String errmsg;
}
