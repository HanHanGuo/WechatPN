package com.xianguo.wechatpn.api;

import java.util.List;

import com.xianguo.wechatpn.WechatApiFull;
import com.xianguo.wechatpn.WechatApiPublicResponse;
import com.xianguo.wechatpn.api.ImageTextDataApi.ArticlesummaryApi.ArticlesummaryRequest;
import com.xianguo.wechatpn.api.ImageTextDataApi.ArticlesummaryApi.ArticlesummaryResponse;
import com.xianguo.wechatpn.api.ImageTextDataApi.ArticletotalApi.ArticletotalResponse;
import com.xianguo.wechatpn.api.ImageTextDataApi.ArticletotalApi.ArticletotalRquest;
import com.xianguo.wechatpn.api.ImageTextDataApi.UserreadApi.UserreadRequest;
import com.xianguo.wechatpn.api.ImageTextDataApi.UserreadApi.UserreadResponse;
import com.xianguo.wechatpn.api.ImageTextDataApi.UserreadhourApi.UserreadhourRequest;
import com.xianguo.wechatpn.api.ImageTextDataApi.UserreadhourApi.UserreadhourResponse;
import com.xianguo.wechatpn.api.ImageTextDataApi.UsershareApi.UsershareRequest;
import com.xianguo.wechatpn.api.ImageTextDataApi.UsershareApi.UsershareResponse;
import com.xianguo.wechatpn.api.ImageTextDataApi.UsersharehourApi.UsersharehourRequest;
import com.xianguo.wechatpn.api.ImageTextDataApi.UsersharehourApi.UsersharehourResponse;
import com.xianguo.wechatpn.enums.HttpRequestType;
import com.xianguo.wechatpn.utils.AccessToken;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图文分析数据接口
 * 
 * @author 武昱坤
 * @date 2019年4月10日
 *
 */
public class ImageTextDataApi {
	/**
	 * 获取图文群发每日数据
	 * 
	 * @author 武昱坤
	 * @date 2019年4月10日
	 *
	 */
	public static class ArticlesummaryApi extends WechatApiFull<ArticlesummaryRequest, ArticlesummaryResponse> {

		public ArticlesummaryApi() {
			super(ArticlesummaryResponse.class,"https://api.weixin.qq.com/datacube/getarticlesummary?access_token=" + AccessToken.getAccessToken(),HttpRequestType.POST);
		}
		/**
		 * 最大时间跨度1
		 * @author 武昱坤
		 * @date 2019年4月10日
		 *
		 */
		@Data
		public static class ArticlesummaryRequest {
			private String begin_date;// 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
			private String end_date;// 获取数据的结束日期，end_date允许设置的最大值为昨日
		}

		@Data
		@EqualsAndHashCode(callSuper = false)
		public static class ArticlesummaryResponse extends WechatApiPublicResponse {
			private List<Articlesummary> list;
		}

		@Data
		public static class Articlesummary {
			private String ref_date;// 数据的日期，需在begin_date和end_date之间
			private String msgid;// 请注意：这里的msgid实际上是由msgid（图文消息id，这也就是群发接口调用后返回的msg_data_id）和index（消息次序索引）组成，
									// 例如12003_3， 其中12003是msgid，即一次群发的消息的id；
									// 3为index，假设该次群发的图文消息共5个文章（因为可能为多图文），3表示5个中的第3个
			private String title;// 图文消息的标题
			private Integer int_page_read_user;// 图文页（点击群发图文卡片进入的页面）的阅读人数
			private Integer int_page_read_count;// 图文页的阅读次数
			private Integer ori_page_read_user;// 原文页（点击图文页“阅读原文”进入的页面）的阅读人数，无原文页时此处数据为0
			private Integer ori_page_read_count;// 原文页的阅读次数
			private Integer share_user;// 分享的人数
			private Integer share_count;// 分享的次数
			private Integer add_to_fav_user;// 收藏的人数
			private Integer add_to_fav_count;// 收藏的次数
		}
	}

	/**
	 * 获取图文群发总数据
	 * 
	 * @author 武昱坤
	 * @date 2019年4月10日
	 *
	 */
	public static class ArticletotalApi extends WechatApiFull<ArticletotalRquest, ArticletotalResponse> {

		public ArticletotalApi() {
			super(ArticletotalResponse.class,"https://api.weixin.qq.com/datacube/getarticletotal?access_token=" + AccessToken.getAccessToken(),HttpRequestType.POST);
		}
		/**
		 * 最大时间跨度1
		 * @author 武昱坤
		 * @date 2019年4月10日
		 *
		 */
		@Data
		public static class ArticletotalRquest {
			private String begin_date;
			private String end_date;
		}

		@Data
		@EqualsAndHashCode(callSuper = false)
		public static class ArticletotalResponse extends WechatApiPublicResponse {
			private List<Articletotal> list;
		}

		@Data
		public static class Articletotal {
			private String ref_date;
			private String msgid;
			private String title;
			private List<ArticletotalDetails> details;
		}

		@Data
		public static class ArticletotalDetails {
			private String stat_date;// 统计的日期，在getarticletotal接口中，ref_date指的是文章群发出日期， 而stat_date是数据统计日期
			private Integer target_user;// 送达人数，一般约等于总粉丝数（需排除黑名单或其他异常情况下无法收到消息的粉丝）
			private Integer int_page_read_user;
			private Integer int_page_read_count;
			private Integer ori_page_read_user;
			private Integer ori_page_read_count;
			private Integer share_user;
			private Integer share_count;
			private Integer add_to_fav_user;
			private Integer add_to_fav_count;
			private Integer int_page_from_session_read_user;
			private Integer int_page_from_session_read_count;
			private Integer int_page_from_hist_msg_read_user;
			private Integer int_page_from_hist_msg_read_count;
			private Integer int_page_from_feed_read_user;
			private Integer int_page_from_feed_read_count;
			private Integer int_page_from_friends_read_user;
			private Integer int_page_from_friends_read_count;
			private Integer int_page_from_other_read_user;
			private Integer int_page_from_other_read_count;
			private Integer feed_share_from_session_user;
			private Integer feed_share_from_session_cnt;
			private Integer feed_share_from_feed_user;
			private Integer feed_share_from_feed_cnt;
			private Integer feed_share_from_other_user;
			private Integer feed_share_from_other_cnt;
		}

	}

	/**
	 * 获取图文统计数据
	 * 
	 * @author 武昱坤
	 * @date 2019年4月10日
	 *
	 */
	public static class UserreadApi extends WechatApiFull<UserreadRequest, UserreadResponse> {

		public UserreadApi() {
			super(UserreadResponse.class,"https://api.weixin.qq.com/datacube/getuserread?access_token=" + AccessToken.getAccessToken(),HttpRequestType.POST);
		}
		/**
		 * 最大时间跨度3
		 * @author 武昱坤
		 * @date 2019年4月10日
		 *
		 */
		@Data
		public static class UserreadRequest {
			private String begin_date;
			private String end_date;
		}

		@Data
		@EqualsAndHashCode(callSuper = false)
		public static class UserreadResponse extends WechatApiPublicResponse {
			private List<Userread> list;
		}

		@Data
		public static class Userread {
			private String ref_date;
			private Integer int_page_read_user;
			private Integer int_page_read_count;
			private Integer ori_page_read_user;
			private Integer ori_page_read_count;
			private Integer share_user;
			private Integer share_count;
			private Integer add_to_fav_user;
			private Integer add_to_fav_count;
		}

	}

	/**
	 * 获取图文统计分时数据
	 * 
	 * @author 武昱坤
	 * @date 2019年4月10日
	 *
	 */
	public static class UserreadhourApi extends WechatApiFull<UserreadhourRequest, UserreadhourResponse> {

		public UserreadhourApi() {
			super(UserreadhourResponse.class,"https://api.weixin.qq.com/datacube/getuserreadhour?access_token=" + AccessToken.getAccessToken(),HttpRequestType.POST);
		}
		/**
		 * 最大时间跨度1
		 * @author 武昱坤
		 * @date 2019年4月10日
		 *
		 */
		@Data
		public static class UserreadhourRequest {
			private String begin_date;
			private String end_date;
		}
		@Data
		@EqualsAndHashCode(callSuper = false)
		public static class UserreadhourResponse extends WechatApiPublicResponse {
			private List<Userreadhour> list;
		}
		@Data
		public static class Userreadhour {
			private String ref_date;
			private Integer ref_hour;
			private Integer user_source;
			private Integer int_page_read_user;
			private Integer int_page_read_count;
			private Integer ori_page_read_user;
			private Integer ori_page_read_count;
			private Integer share_user;
			private Integer share_count;
			private Integer add_to_fav_user;
			private Integer add_to_fav_count;
		}
	}
	/**
	 * 获取图文分享转发数据
	 * @author 武昱坤
	 * @date 2019年4月10日
	 *
	 */
	public static class UsershareApi extends WechatApiFull<UsershareRequest, UsershareResponse>{
		
		public UsershareApi() {
			super(UsershareResponse.class, "https://api.weixin.qq.com/datacube/getusershare?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}
		/**
		 * 最大时间跨度7
		 * @author 武昱坤
		 * @date 2019年4月10日
		 *
		 */
		@Data
		public static class UsershareRequest{
			private String begin_date;
			private String end_date;
		}
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class UsershareResponse extends WechatApiPublicResponse{
			private List<Usershare> list;
		}
		@Data
		public static class Usershare{
			private String ref_date;
			private Integer share_scene;
			private Integer share_count;
			private Integer share_user;
		}
	}
	/**
	 * 获取图文分享转发分时数据
	 * @author 武昱坤
	 * @date 2019年4月10日
	 *
	 */
	public static class UsersharehourApi extends WechatApiFull<UsersharehourRequest, UsersharehourResponse>{
		public UsersharehourApi() {
			super(UsersharehourResponse.class, "https://api.weixin.qq.com/datacube/getusersharehour?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}
		/**
		 * 最大时间跨度1
		 * @author 武昱坤
		 * @date 2019年4月10日
		 *
		 */
		@Data
		public static class UsersharehourRequest {
			private String begin_date;
			private String end_date;
		}
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class UsersharehourResponse extends WechatApiPublicResponse{
			private List<Usersharehour> list;
		}
		@Data
		public static class Usersharehour{
			private String ref_date;
			private Integer ref_hour;
			private Integer share_scene;
			private Integer share_count;
			private Integer share_user;
		}	
	}
	
}
