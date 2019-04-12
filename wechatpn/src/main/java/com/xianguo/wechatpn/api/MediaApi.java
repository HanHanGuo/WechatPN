package com.xianguo.wechatpn.api;

import java.util.List;

import com.xianguo.wechatpn.WechatApiDefect;
import com.xianguo.wechatpn.WechatApiFull;
import com.xianguo.wechatpn.WechatApiInputStream;
import com.xianguo.wechatpn.WechatApiPublicResponse;
import com.xianguo.wechatpn.WechatApiUploadStream;
import com.xianguo.wechatpn.api.MediaApi.CreateMediaApi.CreateMediaResponse;
import com.xianguo.wechatpn.api.MediaApi.CreateMediaTemporaryApi.CreateMediaTemporaryRequest;
import com.xianguo.wechatpn.api.MediaApi.CreateMediaTemporaryApi.CreateMediaTemporaryResponse;
import com.xianguo.wechatpn.api.MediaApi.CreateRichTextApi.CreateRichTextRequest;
import com.xianguo.wechatpn.api.MediaApi.CreateRichTextApi.CreateRichTextResponse;
import com.xianguo.wechatpn.api.MediaApi.GetMediaApi.MediaRequest;
import com.xianguo.wechatpn.api.MediaApi.GetMediaListApi.GetMediaListRequest;
import com.xianguo.wechatpn.api.MediaApi.GetMediaListApi.GetMediaListResponse;
import com.xianguo.wechatpn.api.MediaApi.GetMediaNumberApi.GetMediaNumberResponse;
import com.xianguo.wechatpn.api.MediaApi.GetNewsMediaApi.GetNewsMediaResponse;
import com.xianguo.wechatpn.api.MediaApi.GetNewsMediaListApi.GetNewsMediaListResponse;
import com.xianguo.wechatpn.api.MediaApi.MediaToUrlApi.MediaToUrlResponse;
import com.xianguo.wechatpn.api.MediaApi.UpdateMediaApi.UpdateMediaRequest;
import com.xianguo.wechatpn.enums.HttpRequestType;
import com.xianguo.wechatpn.utils.AccessToken;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 素材管理
 * @author 鲜果
 * @date 2019年4月11日
 *
 */
@Slf4j
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
			 private String title;//标题
			 private String thumb_media_id;//图文消息的封面图片素材id（必须是永久mediaID）
			 private String author;//作者
			 private String digest;//图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空。如果本字段为没有填写，则默认抓取正文前64个字。
			 private String show_cover_pic;//是否显示封面，0为false，即不显示，1为true，即显示
			 private String content;//图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源 "上传图文消息内的图片获取URL"接口获取。外部图片url将被过滤。
			 private String content_source_url;//图文消息的原文地址，即点击“阅读原文”后的URL
			 private String need_open_comment;//Uint32 是否打开评论，0不打开，1打开
			 private String	only_fans_can_comment;//Uint32 是否粉丝才可评论，0所有人可评论，1粉丝才可评论
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
	
	/**
	 * 新增其他类型永久素材
	 * @author 鲜果
	 * @date 2019年4月12日
	 *
	 */
	public static class CreateMediaApi extends WechatApiFull<WechatApiUploadStream, CreateMediaResponse> {

		public CreateMediaApi() {
			super(CreateMediaResponse.class, "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}

		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class CreateMediaResponse extends WechatApiPublicResponse {
			private String media_id;//新增的永久素材的media_id
			private String url;//新增的图片素材的图片URL（仅新增图片素材时会返回该字段）
		}
		
	}
	

	
	/**
	 * 获取除视频和图文素材之外的其他素材
	 * @author 鲜果
	 * @date 2019年4月12日
	 *
	 */
	public static class GetMediaApi extends WechatApiFull<MediaRequest, WechatApiInputStream> {
		
		public GetMediaApi() {
			super(WechatApiInputStream.class, "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}

		@Data
		public static class MediaRequest {
			private String media_id;
		}
	}
	
	/**
	 * 获取永久图文素材
	 * @author 鲜果
	 * @date 2019年4月12日
	 *
	 */
	public static class GetNewsMediaApi extends WechatApiFull<MediaRequest, GetNewsMediaResponse> {
		
		public GetNewsMediaApi() {
			super(GetNewsMediaResponse.class, "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}
		
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class GetNewsMediaResponse extends WechatApiPublicResponse {
			private List<NewsItem> news_item;
		}
		
		@Data
		public static class NewsItem {
			private String title;//	图文消息的标题
			private String thumb_media_id;//	图文消息的封面图片素材id（必须是永久mediaID）
			private String show_cover_pic;//	是否显示封面，0为false，即不显示，1为true，即显示
			private String author;//	作者
			private String digest;//	图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
			private String content;//	图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
			private String url;//	图文页的URL
			private String content_source_url;//	图文消息的原文地址，即点击“阅读原文”后的URL
		}
	}
	
	
	/**
	 * 获取视频素材
	 * @author 鲜果
	 * @date 2019年4月12日
	 *
	 */
	public static class GetVideoMediaApi extends WechatApiFull<MediaRequest, WechatApiPublicResponse> {
		
		
		public GetVideoMediaApi() {
			super(WechatApiPublicResponse.class, "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}
		
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class GetVideoMediaResponse extends WechatApiPublicResponse{
			private String title;//视频标题
			private String description;//视频详情
			private String down_url;//视频地址
		}
		
	}
	
	/**
	 * 删除永久素材
	 * 请注意：
	 * 1、请谨慎操作本接口，因为它可以删除公众号在公众平台官网素材管理模块中新建的图文消息、语音、视频等素材（但需要先通过获取素材列表来获知素材的media_id）
	 * 2、临时素材无法通过本接口删除
	 * @author 鲜果
	 * @date 2019年4月12日
	 *
	 */
	public static class DeleteMediaApi extends WechatApiFull<MediaRequest, WechatApiPublicResponse> {

		public DeleteMediaApi() {
			super(WechatApiPublicResponse.class, "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}
		
	}
	
	/**
	 * 修改永久图文素材
	 * 请注意：
	 * 1、也可以在公众平台官网素材管理模块中保存的图文消息（永久图文素材）
	 * @author 鲜果
	 * @date 2019年4月12日
	 *
	 */
	public static class UpdateMediaApi extends WechatApiFull<UpdateMediaRequest, WechatApiPublicResponse> {
		
		public UpdateMediaApi() {
			super(WechatApiPublicResponse.class, "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}

		@Data
		public static class UpdateMediaRequest {
			private String media_id;//要修改的图文消息的id
			private String index;//要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
			private UpdateArticle articles;
			
			public UpdateMediaRequest() {
				try {
					this.articles = UpdateArticle.class.newInstance();
				} catch (Exception e) {
					log.error(e.getMessage(),e);
					log.info("自动创建UpdateArticle对象失败");
				}
			}
		}
		
		@Data
		public static class UpdateArticle {
			private String title;//标题
			private String thumb_media_id;//图文消息的封面图片素材id（必须是永久mediaID）
			private String author;//作者
			private String digest;//图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
			private String show_cover_pic;//是否显示封面，0为false，即不显示，1为true，即显示
			private String content;//图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
			private String content_source_url;//图文消息的原文地址，即点击“阅读原文”后的URL
		}
		
	}
	
	/**
	 * 获取素材总数
	 * 请注意：
	 * 1.永久素材的总数，也会计算公众平台官网素材管理中的素材
	 * 2.图片和图文消息素材（包括单图文和多图文）的总数上限为5000，其他素材的总数上限为1000
	 * @author 鲜果
	 * @date 2019年4月12日
	 *
	 */
	public static class GetMediaNumberApi extends WechatApiDefect<GetMediaNumberResponse> {
		
		public GetMediaNumberApi() {
			super(GetMediaNumberResponse.class, "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token="+AccessToken.getAccessToken(), HttpRequestType.GET);
		}

		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class GetMediaNumberResponse extends WechatApiPublicResponse {
			private String voice_count;//语音总数量
			private String video_count;//视频总数量
			private String image_count;//图片总数量
			private String news_count;//图文总数量
		}
		
	}
	
	/**
	 * 获取素材列表
	 * 1、永久图文消息无法通过本接口获取，永久图文消息请使用GetNewsMediaListApi获取
	 * 2、获取永久素材的列表，也包含公众号在公众平台官网素材管理模块中新建的语音、视频等素材
	 * 3、临时素材无法通过本接口获取
	 * @author 鲜果
	 * @date 2019年4月12日
	 *
	 */
	public static class GetMediaListApi extends WechatApiFull<GetMediaListRequest, GetMediaListResponse> {
		
		public GetMediaListApi() {
			super(GetMediaListResponse.class, "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}

		@Data
		public static class GetMediaListRequest {
			private String type;//素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
			private String offset;//从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
			private String count;//返回素材的数量，取值在1到20之间
		}
		
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class GetMediaListResponse extends WechatApiPublicResponse {
			private Integer total_count;//该类型的素材的总数
			private Integer item_count;//本次调用获取的素材的数量
			private List<GetMediaListItem> item;
		}
		
		@Data
		public static class GetMediaListItem {
			private String media_id;//素材id
			private String name;//文件名称
			private String update_time;//更新时间
			private String url;//素材地址
		}
	}
	
	/**
	 * 获取永久图文素材列表
	 * 请注意：
	 * 1、本接口只能获取永久图文素材列表，图片，视频，语音等请通过GetMediaListApi接口获取
	 * 2、获取永久素材的列表，也包含公众号在公众平台官网素材管理模块中新建的语音、视频等素材
	 * 3、临时素材无法通过本接口获取
	 * @author 鲜果
	 * @date 2019年4月12日
	 *
	 */
	public static class GetNewsMediaListApi extends WechatApiFull<GetMediaListRequest, GetNewsMediaListResponse> {

		public GetNewsMediaListApi() {
			super(GetNewsMediaListResponse.class, "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}

		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class GetNewsMediaListResponse extends WechatApiPublicResponse {
			private Integer total_count;//该类型的素材的总数
			private Integer item_count;//本次调用获取的素材的数量
			private List<GetNewsMediaListItem> item;//图文消息item结构
		}
		
		@Data
		public static class GetNewsMediaListItem {
			private String media_id;//素材id
			private String update_time;//更新时间
			private GetNewsMediaListContent content;//内容
		}
		
		@Data
		public static class GetNewsMediaListContent {
			private List<GetNewsMediaListContentItem> news_item;//多篇文章
		}
		
		@Data
		public static class GetNewsMediaListContentItem {
			private String title;//图文消息的标题
			private String thumb_media_id;//图文消息的封面图片素材id（必须是永久mediaID）
			private String show_cover_pic;//是否显示封面，0为false，即不显示，1为true，即显示
			private String author;//作者
			private String digest;//图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
			private String content;//图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
			private String url;//图文页的URL，或者，当获取的列表是图片素材列表时，该字段是图片的URL
			private String content_source_url;//图文消息的原文地址，即点击“阅读原文”后的URL
		}
	}
}
