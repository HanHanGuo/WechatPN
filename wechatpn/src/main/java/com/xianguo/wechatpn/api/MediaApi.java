package com.xianguo.wechatpn.api;

import java.util.List;

import com.xianguo.wechatpn.WechatApiDefect;
import com.xianguo.wechatpn.WechatApiFull;
import com.xianguo.wechatpn.WechatApiInputStream;
import com.xianguo.wechatpn.WechatApiPublicResponse;
import com.xianguo.wechatpn.WechatApiUploadStream;
import com.xianguo.wechatpn.api.MediaApi.CreateMediaTemporaryApi.CreateMediaTemporaryRequest;
import com.xianguo.wechatpn.api.MediaApi.CreateMediaTemporaryApi.CreateMediaTemporaryResponse;
import com.xianguo.wechatpn.api.MediaApi.CreateRichTextApi.CreateRichTextRequest;
import com.xianguo.wechatpn.api.MediaApi.CreateRichTextApi.CreateRichTextResponse;
import com.xianguo.wechatpn.api.MediaApi.MediaToUrlApi.MediaToUrlResponse;
import com.xianguo.wechatpn.enums.HttpRequestType;
import com.xianguo.wechatpn.utils.AccessToken;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 素材管理
 * @author 鲜果
 * @date 2019年4月11日
 *
 */
public class MediaApi {
	
	/**
	 * 新增临时素材
	 * @author 鲜果
	 * @date 2019年4月11日
	 *
	 */
	public static class CreateMediaTemporaryApi extends WechatApiFull<CreateMediaTemporaryRequest, CreateMediaTemporaryResponse> {
		
		public CreateMediaTemporaryApi() {
			super(CreateMediaTemporaryResponse.class, "https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}

		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class CreateMediaTemporaryRequest extends WechatApiUploadStream {
			private String type;//媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
		}
		
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class CreateMediaTemporaryResponse extends WechatApiPublicResponse {
			private String type;//媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb，主要用于视频与音乐格式的缩略图）
			private String media_id;//媒体文件上传后，获取标识
			private String created_at;//媒体文件上传时间戳
		}
		
	}
	
	/**
	 * 获取临时素材
	 * @author 鲜果
	 * @date 2019年4月11日
	 *
	 */
	public static class GetMediaTemporaryApi extends WechatApiDefect<WechatApiInputStream> {

		public GetMediaTemporaryApi(String mediaId) {
			super(WechatApiInputStream.class, "https://api.weixin.qq.com/cgi-bin/media/get?access_token="+AccessToken.getAccessToken()+"&media_id="+mediaId, HttpRequestType.GET);
		}
		
	}
	
	/**
	 * 新增永久图文素材api
	 * @author 鲜果
	 * @date 2019年4月11日
	 *
	 */
	public static class CreateRichTextApi extends WechatApiFull<CreateRichTextRequest, CreateRichTextResponse> {
		
		public CreateRichTextApi() {
			super(CreateRichTextResponse.class, "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}

		@Data
		public static class CreateRichTextRequest {
			private List<Article> articles;
		}
		
		@Data
		public static class Article {
			 private String title;
			 private String thumb_media_id;
			 private String author;
			 private String digest;
			 private String show_cover_pic;
			 private String content;
			 private String content_source_url;
			 private String need_open_comment;
			 private String	only_fans_can_comment;
		}
		
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class CreateRichTextResponse extends WechatApiPublicResponse {
			private String media_id;
		}
		
	}
	
	/**
	 * 上传图文消息内的图片获取URLApi
	 * @author 鲜果
	 * @date 2019年4月11日
	 *
	 */
	public static class MediaToUrlApi extends WechatApiDefect<MediaToUrlResponse> {
		
		public MediaToUrlApi() {
			super(MediaToUrlResponse.class, "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}
		
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class MediaToUrlResponse extends WechatApiPublicResponse {
			private String url;
		}
		
	}
	
}
