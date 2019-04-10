package com.xianguo.wechatpn.utils;

import java.io.IOException;
import java.lang.reflect.Type;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.xianguo.wechatpn.interfaces.EnmuDecoderInterface;

public class FastJsonEnmuDecoder implements ObjectSerializer, ObjectDeserializer {

	@Override
	@SuppressWarnings("unchecked")
	public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
		Object value = parser.parse();
        return value == null ? null : value instanceof EnmuDecoderInterface ? ((EnmuDecoderInterface)value).getEnmuByKeyInterface(value.toString()) : (T) value;
	}

	@Override
	public int getFastMatchToken() {
		return 0;
	}

	@Override
	public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features)
			throws IOException {
		if(object instanceof EnmuDecoderInterface) {
			serializer.write(((EnmuDecoderInterface) object).getValue());
		}
	}

}
