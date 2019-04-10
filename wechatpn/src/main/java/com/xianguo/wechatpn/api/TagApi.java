package com.xianguo.wechatpn.api;

import java.util.ArrayList;
import java.util.List;

import com.xianguo.wechatpn.WechatApiDefect;
import com.xianguo.wechatpn.WechatApiFull;
import com.xianguo.wechatpn.WechatApiPublicResponse;
import com.xianguo.wechatpn.api.TagApi.CancelTageUserApi.CancelTageUserRequest;
import com.xianguo.wechatpn.api.TagApi.CreateTagApi.CreateTagRequest;
import com.xianguo.wechatpn.api.TagApi.CreateTagApi.CreateTagResponse;
import com.xianguo.wechatpn.api.TagApi.CreateTagApi.Tag;
import com.xianguo.wechatpn.api.TagApi.DeleteTagApi.DeleteTageRequest;
import com.xianguo.wechatpn.api.TagApi.GetUserByTagApi.GetUserByTagRequest;
import com.xianguo.wechatpn.api.TagApi.GetUserByTagApi.GetUserByTagResponse;
import com.xianguo.wechatpn.api.TagApi.GetUserTagsApi.GetUserTagsRequest;
import com.xianguo.wechatpn.api.TagApi.GetUserTagsApi.GetUserTagsResponse;
import com.xianguo.wechatpn.api.TagApi.QueryTagApi.QueryTagResponse;
import com.xianguo.wechatpn.api.TagApi.TagingUserApi.TagingUserRequest;
import com.xianguo.wechatpn.api.TagApi.UpdateTagApi.UpdateTageRequest;
import com.xianguo.wechatpn.enums.HttpRequestType;
import com.xianguo.wechatpn.utils.AccessToken;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TagApi {
	
	/**
	 * 创建标签api
	 * @author 鲜果
	 * @date 2019年4月10日
	 *
	 */
	public static class CreateTagApi extends WechatApiFull<CreateTagRequest, CreateTagResponse> {
		
		public CreateTagApi() {
			super(CreateTagResponse.class, "https://api.weixin.qq.com/cgi-bin/tags/create?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}

		@Getter
		public static class CreateTagRequest {
			private CreateTag tag;
			
			public CreateTagRequest() {
				try {
					this.tag = CreateTag.class.newInstance();
				} catch (Exception e) {
					log.error(e.getMessage(),e);
					log.info("创建标签对象反射失败");
				}
			}
		}
		
		@Data
		public static class CreateTag {
			private String name;//标签名称
		}
		
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class CreateTagResponse extends WechatApiPublicResponse {
			private Tag tag;
		}
		
		@Data
		public static class Tag {
			private String id;//标签id
			private String name;//标签名称
		}
	
	}
	
	/**
	 * 查询标签Api
	 * @author 鲜果
	 * @date 2019年4月10日
	 *
	 */
	public static class QueryTagApi extends WechatApiDefect<QueryTagResponse> {
		
		public QueryTagApi() {
			super(QueryTagResponse.class, "https://api.weixin.qq.com/cgi-bin/tags/get?access_token="+AccessToken.getAccessToken(), HttpRequestType.GET);
		}

		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class QueryTagResponse extends WechatApiPublicResponse {
			private List<QueryTag> tags; 
		}
		
		@Data
		public static class QueryTag{
			private String id;
			private String name;
			private Integer count;
		}
		
	}
	
	/**
	 * 更新标签api
	 * @author 鲜果
	 * @date 2019年4月10日
	 *
	 */
	public static class UpdateTagApi extends WechatApiFull<UpdateTageRequest, WechatApiPublicResponse> {
		
		public UpdateTagApi() {
			super(WechatApiPublicResponse.class, "https://api.weixin.qq.com/cgi-bin/tags/update?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}

		@Getter
		public static class UpdateTageRequest  {
			private Tag tag;
			
			public UpdateTageRequest() {
				try {
					this.tag = Tag.class.newInstance();
				} catch (Exception e) {
					log.error(e.getMessage(),e);
					log.info("创建标签对象反射失败");
				}
			}
		}

	}
	
	/**
	 * 删除标签api
	 * @author 鲜果
	 * @date 2019年4月10日
	 *
	 */
	public static class DeleteTagApi extends WechatApiFull<DeleteTageRequest, WechatApiPublicResponse>{
		
		public DeleteTagApi() {
			super(WechatApiPublicResponse.class, "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}

		@Getter
		public static class DeleteTageRequest {
			
			private TagId tag;
			
			public DeleteTageRequest() {
				try {
					this.tag = TagId.class.newInstance();
				} catch (Exception e) {
					log.error(e.getMessage(),e);
					log.info("创建标签对象反射失败");
				}
			}
		}
		
		@Data
		public static class TagId{
			private String id;//标签id
		}
		
	}
	
	/**
	 * 获取标签下粉丝列表Api
	 * @author 鲜果
	 * @date 2019年4月10日
	 *
	 */
	public static class GetUserByTagApi extends WechatApiFull<GetUserByTagRequest, GetUserByTagResponse>{
		
		public GetUserByTagApi() {
			super(GetUserByTagResponse.class, "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token="+AccessToken.getAccessToken(), HttpRequestType.GET);
		}

		@Data
		public static class GetUserByTagRequest {
			private String tagid;//标签id
			private String next_openid;//第一个拉取的OPENID，不填默认从头开始拉取
		}
		
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class GetUserByTagResponse extends WechatApiPublicResponse {
			private Integer count;//这次获取的粉丝数量
			private String next_openid;//拉取列表最后一个用户的openid
			private GetUserByTagResponseData data;//粉丝列表
		}
		
		@Getter
		public static class GetUserByTagResponseData{
			private List<String> openid;//openId
		}
	}
	
	/**
	 * 批量为用户打标签
	 * @author 鲜果
	 * @date 2019年4月10日
	 *
	 */
	public static class TagingUserApi extends WechatApiFull<TagingUserRequest, WechatApiPublicResponse> {
		
		public TagingUserApi() {
			super(WechatApiPublicResponse.class, "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}

		@Data
		public static class TagingUserRequest {
			private List<String> openid_list;//粉丝列表
			private String tagid;//标签id
			
			public TagingUserRequest() {
				openid_list = new ArrayList<String>();
			}
		}
		
	}
	
	/**
	 * 批量为用户取消标签
	 * @author 鲜果
	 * @date 2019年4月10日
	 *
	 */
	public static class CancelTageUserApi extends WechatApiFull<CancelTageUserRequest, WechatApiPublicResponse> {
		
		public CancelTageUserApi() {
			super(WechatApiPublicResponse.class, "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}

		public static class CancelTageUserRequest extends TagingUserRequest {
			
		}
		
	}
	
	/**
	 * 获取用户身上的标签列表
	 * @author 鲜果
	 * @date 2019年4月10日
	 *
	 */
	public static class GetUserTagsApi extends WechatApiFull<GetUserTagsRequest, GetUserTagsResponse>{
		
		public GetUserTagsApi() {
			super(GetUserTagsResponse.class, "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token="+AccessToken.getAccessToken(), HttpRequestType.GET);
		}

		@Data
		public static class GetUserTagsRequest {
			private String openid;
		}
		
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class GetUserTagsResponse extends WechatApiPublicResponse {
			private List<String> tagid_list;
		}
	}
}
