package com.xianguo.wechatpn;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
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

	protected Object Get(Map<String, Object> params) {
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
			Map<String, List<String>> map = conn.getHeaderFields();
			String contentType = map.get("Content-Type").get(0);
			InputStream inStream = conn.getInputStream();
			if(contentType.lastIndexOf("application/json") != -1 || contentType.lastIndexOf("text") != -1) {
				byte[] data = toByteArray(inStream);
				String result = new String(data, "UTF-8");
				inStream.close();
				return result;
			}else {
				return inStream;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	protected Object Post(Object params) {
		try {
			String encoding = "UTF-8";
			
			HttpURLConnection conn = null;
			String finalSplit = "---------------------------123821742118716";
			if(params instanceof String) {//普通参数
				URL url = new URL(path);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				byte[] data = ((String) params).getBytes(encoding);
				conn.setRequestProperty("Content-Type", "application/x-javascript; charset=" + encoding);
				conn.setRequestProperty("Content-Length", String.valueOf(data.length));
				conn.setConnectTimeout(5 * 1000);
				OutputStream outStream = conn.getOutputStream();
				outStream.write(data);
				outStream.flush();
				outStream.close();
			}else if(params instanceof WechatApiUploadStream) {//流参数表单文件上传
				Map<String, Object> textValues = (Map<String, Object>)JSON.parse(JSON.toJSONString(params));
				for(String key : textValues.keySet()) {
					if(path.lastIndexOf("?") != -1) {
						path += "&" + key + "=" + textValues.get(key);
					}else {
						path += "?" + key + "=" + textValues.get(key);
					}
				}
				URL url = new URL(path);
				conn = (HttpURLConnection) url.openConnection();
				WechatApiUploadStream uploadStream = (WechatApiUploadStream) params;
				conn.setRequestProperty("Connection", "Keep-Alive");
				conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
				conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + finalSplit);
				conn.setDoOutput(true);
		        conn.setDoInput(true);
		        conn.setUseCaches(false);//POST方式不能使用缓存
		        
				StringBuilder strbuil = new StringBuilder();
				strbuil.append("\r\n").append("--").append(finalSplit).append("\r\n");
				strbuil.append("Content-Disposition: form-data; name=\"" + uploadStream.getInputName() + "\"; filename=\"" + uploadStream.getFileName() + "\"; filelength=\""+uploadStream.getInputStream().available()+"\"\r\n");
				strbuil.append("Content-Type:application/octet-stream\r\n\r\n");
				OutputStream outStream = conn.getOutputStream();
				outStream.write(strbuil.toString().getBytes());
				int len = -1;
				byte[] temp = new byte[1024];
				DataInputStream din=new DataInputStream(uploadStream.getInputStream());
				while ((len = din.read(temp)) != -1) {
					outStream.write(temp, 0, len);
				}
				din.close();
				byte[] endData = ( "\r\n--" + finalSplit + "--\r\n").getBytes();
				outStream.write(endData);
				outStream.flush();
				outStream.close();
			}

			Map<String, List<String>> map = conn.getHeaderFields();
			String contentType = map.get("Content-Type").get(0);
			InputStream inStream = conn.getInputStream();
			if (conn.getResponseCode() == 200 && (contentType.lastIndexOf("application/json") != -1 || contentType.lastIndexOf("text") != -1)) {
				String result = new String(toByteArray(inStream), "UTF-8");
				inStream.close();
				return result;
			}else {
				return inStream;
			}
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
