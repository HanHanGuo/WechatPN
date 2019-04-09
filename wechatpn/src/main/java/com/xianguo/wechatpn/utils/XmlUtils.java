package com.xianguo.wechatpn.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;

public class XmlUtils {

	/**
	 * 获取xml转实体，转换对象
	 *
	 * @author 鲜果
	 * @param @return
	 * @date 2019年4月9日
	 * @return XStream
	 * @throws
	 */
	public static XStream GetXmlBean() {
		return new XStream() {
			@Override
			protected MapperWrapper wrapMapper(MapperWrapper next) {
				return new MapperWrapper(next) {
					@Override
					public boolean shouldSerializeMember(@SuppressWarnings("rawtypes") Class definedIn,
							String fieldName) {
						if (definedIn == Object.class) {
							try {
								return this.realClass(fieldName) != null;
							} catch (Exception e) {
								return false;
							}
						} else {
							return super.shouldSerializeMember(definedIn, fieldName);
						}
					}
				};
			}
		};

	}

}
