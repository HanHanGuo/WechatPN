package com.xianguo.wechatpn.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import com.xianguo.wechatpn.api.TokenApi;
import com.xianguo.wechatpn.api.TokenApi.TokenResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessToken {

	public static String AccessToken;
	private static Boolean isReturn = true;
	
	@SuppressWarnings("all")
	public static String getAccessToken() {
		// 1、检查token是否有值 有值则直接返回
		if (AccessToken != null) {
			return AccessToken;
		}
		// 2、token通过scoket进行操作
		try {
			// 如果目标地址有socket在运行
			if (isRunning(WechatConstants.WX_TOKEN_HOST,WechatConstants.WX_TOKEN_PROT)) {
				return getSocketToken();// 直接取token并返回
			} else {
				isReturn = true;
				Runnable server = new AccessToken().new ScoketServer();
				new Thread(server).start();
				int i = 0;
				while(isReturn) {
					Thread.sleep(150);
					i++;
					if(i>=6*5) {
						break;
					}
				}
				return AccessToken;
			}
		} catch (Exception e) {
			log.info("socket通信异常", e);
			log.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 通过Socket获取token
	 *
	 * @author 武昱坤 @param @return @date 2019年4月9日 @return Token @throws
	 */
	private static String getSocketToken() {
		Socket socket;
		try {
			socket = new Socket(WechatConstants.WX_TOKEN_HOST, WechatConstants.WX_TOKEN_PROT);
			InputStream input = socket.getInputStream();
			byte[] bt = new byte[500];
			input.read(bt);
			return new String(bt);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private class TokenServer implements Runnable{

		@Override
		public void run() {
			try {
				TokenApi api = new TokenApi();
				TokenResponse response = api.execute();
				if(response.check()) {
					AccessToken = response.getAccess_token();
					isReturn = false;
					int timeout = Integer.valueOf(response.getExpires_in()) - 200;
					while(true) {
						timeout--;
						if(timeout <= 0) {
							break;
						}
						Thread.sleep(1000);
					}
					run();
				}else {
					log.info("微信ACCESS_TOKEN获取失败");
				}
			} catch (InterruptedException e) {
				log.info("ACCESS_TOKEN获取线程休眠错误");
				log.error(e.getMessage(),e);
			}
		}
		
	}
	
	/**
	 * 服务（即等待被请求）
	 * 
	 * @author 武昱坤
	 * @date 2019年4月9日
	 *
	 */
	private class ScoketServer implements Runnable {
		@SuppressWarnings("resource")
		@Override
		public void run() {
			try {
				// 启动服务端请求token 放入socket
				// 暴露服务
				ServerSocket serverSocket;
				serverSocket = new ServerSocket(WechatConstants.WX_TOKEN_PROT);
				new Thread(new TokenServer()).start();
				while(true) {
					Socket socket = serverSocket.accept();
					StringBuilder sb = new StringBuilder();
					InputStream is = socket.getInputStream();
					byte[] temp = new byte[1024];
					int len = is.read(temp);
					sb.append(new String(temp,0,len));
					if(!WechatConstants.WX_TOKEN_KEY.equals(sb.toString())) {
						is.close();
						socket.close();
						continue;
					}
					//Thread.sleep(60*1000);
					// 从socket获取token
					OutputStream outputStream = socket.getOutputStream();
					outputStream.write(AccessToken.getBytes());
					outputStream.close();
					is.close();
					socket.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
		}

	}

	/**
	 * 判断某服务能否连通
	 *
	 * @param host host
	 * @param port port
	 * @return boolean
	 */
	private static boolean isRunning(String host, int port) {
		Socket sClient = null;
		try {
			SocketAddress saAdd = new InetSocketAddress(host.trim(), port);
			sClient = new Socket();
			sClient.connect(saAdd, 1000);
		} catch (UnknownHostException e) {
			return false;
		} catch (SocketTimeoutException e) {
			return false;
		} catch (IOException e) {
			return false;
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (sClient != null) {
					sClient.close();
				}
			} catch (Exception e) {
			}
		}
		return true;
	}

	/**
	 * 判断是否断开连接，断开返回false,连接返回true
	 * 
	 * @param socket
	 * @return
	 */
	@SuppressWarnings("all")
	private static boolean isServerClose(String host, int port) {
		Socket socket;
		try {
			socket = new Socket(host, port);
			socket.sendUrgentData(0xFF);// 发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
		} catch (Exception se) {
			return false;
		}
		return true;
	}
}
