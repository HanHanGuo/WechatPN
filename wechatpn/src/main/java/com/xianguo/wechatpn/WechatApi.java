package com.xianguo.wechatpn;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.xianguo.wechatpn.enums.HttpRequestType;

import lombok.extern.slf4j.Slf4j;

/**
 * api请求器
 * 
 * @author 鲜果
 * @date 2019年4月9日
 *
 * @param <E> 请求实体
 * @param <R> 返回实体
 */
@Slf4j
public class WechatApi<R> {

	protected String path;// 请求地址
	protected HttpRequestType method;// 请求方法
	protected Class<? extends R> resClass;// 请求成功返回class类型对象

	public WechatApi(Class<? extends R> resClass, String url, HttpRequestType method) {
		this.path = url;
		this.method = method;
		this.resClass = resClass;
	}

	protected String Get(Map<String, Object> params) {
		try {
			for(String key : params.keySet()) {
				if(path.lastIndexOf("?") != -1) {
					path += "&" + key + "=" + params.get(key);
				}else {
					path += "?" + key + "=" + params.get(key);
				}
			}
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestMethod("GET");
			InputStream inStream = conn.getInputStream();
			byte[] data = toByteArray(inStream);
			String result = new String(data, "UTF-8");
			return result;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	protected String Post(String params) {
		try {
			String encoding = "UTF-8";
			byte[] data = params.getBytes(encoding);
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			conn.setRequestProperty("Content-Type", "application/x-javascript; charset=" + encoding);
			conn.setRequestProperty("Content-Length", String.valueOf(data.length));
			conn.setConnectTimeout(5 * 1000);
			OutputStream outStream = conn.getOutputStream();
			outStream.write(data);
			outStream.flush();
			outStream.close();
			if (conn.getResponseCode() == 200) {
				InputStream inStream = conn.getInputStream();
				String result = new String(toByteArray(inStream), "UTF-8");
				return result;
			}
			return null;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}

	}

	private byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		return output.toByteArray();
	}

}
