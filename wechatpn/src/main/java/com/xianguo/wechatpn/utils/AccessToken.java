package com.xianguo.wechatpn.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.StringUtils;

import com.xianguo.wechatpn.api.TokenApi;
import com.xianguo.wechatpn.api.TokenApi.TokenResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessToken {

	private static String AccessToken;//Token值
	private static Boolean isReturn = true;//返回标识
	private static Object tokenSocketLock = "";//TokenServer和ScoketServer公用锁
	private static Boolean tokenServerIsRuning = false;//Token服务是否在运行中
	private Lock lock = new ReentrantLock();//Token线程同步锁
	
	@SuppressWarnings("all")
	public static String getAccessToken() {
		// 1、检查token是否有值 有值则直接返回
		if (AccessToken != null) {
			return AccessToken;
		}
		// 2、token通过scoket进行操作
		try {
			// 如果目标地址有socket在运行
			isReturn = true;
			if (isRunning(WechatConstants.WX_TOKEN_HOST,WechatConstants.WX_TOKEN_PROT)) {
				Runnable server = new AccessToken().new TokenClientServer();
				new Thread(server).start();//启动客户端Token获取服务
			} else {
				Runnable server = new AccessToken().new ScoketServer();
				new Thread(server).start();//启动服务端Token获取服务
			}
			
			int i = 0;
			while(isReturn) {
				Thread.sleep(150);
				i++;
				if(i>=6*5) {
					throw new RuntimeException("Token获取超时");
				}
			}
			return AccessToken;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 检查token获取是否成功
	 *
	 * @author 鲜果
	 * @param @param returnMsg
	 * @param @return
	 * @date 2019年4月16日
	 * @return Boolean
	 * @throws
	 */
	public static Boolean checkTokenSuccess(String returnMsg) {
		return  WechatConstants.WX_TOKEN_GET_CLIENT_NOMSG.equals(returnMsg)
				|| WechatConstants.WX_TOKEN_GET_ARRAY_LENGTH_ERROR.equals(returnMsg) 
				|| WechatConstants.WX_TOKEN_GET_KEY_ERROR.equals(returnMsg) 
				|| WechatConstants.WX_TOKEN_GET_ERROR.equals(returnMsg);
	}
	
	/**
	 * Token服务端
	 * @author 鲜果
	 * @date 2019年4月16日
	 *
	 */
	private class TokenClientServer implements Runnable{
		
		private Socket socket;
		private InputStream input;
		private OutputStream os;
		
		@Override
		public void run() {
			try {
				while(true) {
					socket = new Socket(WechatConstants.WX_TOKEN_HOST, WechatConstants.WX_TOKEN_PROT);
					String clientData = WechatConstants.WX_TOKEN_KEY;
					if(StringUtils.isEmpty(AccessToken)) {
						clientData += ":" + WechatConstants.WX_TOKEN_GET_IN;
					}
					os = socket.getOutputStream();
					os.write(clientData.getBytes());
					os.flush();
					input = socket.getInputStream();
					byte[] bt = new byte[1024];
					input.read(bt);
					String returnData = new String(bt);
					if(!checkTokenSuccess(returnData)) {
						AccessToken = returnData;
						isReturn = false;
					}else {
						throw new RuntimeException(returnData);
					}
					socket.close();
					input.close();
					os.close();
				}
			} catch (SocketException e) {
				log.info("服务端死亡，启动Token服务中");
				new Thread(new AccessToken().new ScoketServer()).start();
			} catch (IOException e) {
				log.error(e.getMessage(),e);
				log.info("客户端获取Token失败");
			} finally {
				try {
					if(socket != null){
						socket.close();
					}
					if(input != null){
						input.close();
					}
					if(os != null){
						os.close();
					}
				} catch (IOException e) {
					log.error(e.getMessage(),e);
				}
			}
		}
		
	}
	
	/**
	 * Token获取服务
	 * @author 鲜果
	 * @date 2019年4月16日
	 *
	 */
	private class TokenServer implements Runnable{

		@Override
		public void run() {
			try {
				while(true) {
					lock.lock();
					tokenServerIsRuning = true;
					TokenApi api = new TokenApi();
					TokenResponse response = api.execute();
					if(response.check()) {
						AccessToken = response.getAccess_token();
						lock.unlock();
						isReturn = false;
						int timeout = Integer.valueOf(response.getExpires_in()) - 200;
						synchronized(tokenSocketLock) {
							while(true) {
								timeout--;
								if(timeout <= 0) {
									break;
								}
								Thread.sleep(1000);
							}
						}
					}else {
						log.info("微信ACCESS_TOKEN获取失败");
					}
				}
			} catch (InterruptedException e) {
				log.info("ACCESS_TOKEN获取线程休眠错误");
				log.error(e.getMessage(),e);
			} finally {
				tokenServerIsRuning = false;
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
		
		private ServerSocket serverSocket;
		
		@Override
		public void run() {
			try {
				if(!tokenServerIsRuning) {//启动Token请求线程
					tokenServerIsRuning = true;
					new Thread(new TokenServer()).start();
				}
				// 启动服务端请求token 放入socket
				// 暴露服务
				serverSocket = new ServerSocket(WechatConstants.WX_TOKEN_PROT);
				while(true) {
					if(!tokenServerIsRuning) {//监听Token服务线程,如果被意外关闭则重启
						new Thread(new TokenServer()).start();
					}
					String returnMsg = WechatConstants.WX_TOKEN_GET_ERROR;
					Socket socket = serverSocket.accept();
					log.info("收到链接请求");
					StringBuilder sb = new StringBuilder();
					InputStream is = socket.getInputStream();
					byte[] temp = new byte[1024];
					int len = is.read(temp);
					if(len == -1) {
						returnMsg = WechatConstants.WX_TOKEN_GET_CLIENT_NOMSG;
						OutputStream outputStream = socket.getOutputStream();
						outputStream.write(returnMsg.getBytes());
						outputStream.close();
						is.close();//关闭接收流
						socket.close();//关闭socket链接
						continue;
					}
					sb.append(new String(temp,0,len));
					String clientMsg = sb.toString();
					if(StringUtils.isEmpty(clientMsg)) {//如果客户端没有发送消息
						returnMsg = WechatConstants.WX_TOKEN_GET_CLIENT_NOMSG;
					}
					String[] clientMsgList = clientMsg.split(":");
					
					if(clientMsgList.length <= 0) {//数组长度不正确
						returnMsg = WechatConstants.WX_TOKEN_GET_ARRAY_LENGTH_ERROR;
					}

					if(!WechatConstants.WX_TOKEN_KEY.equals(clientMsgList[0])) {//秘钥key不正确
						returnMsg = WechatConstants.WX_TOKEN_GET_KEY_ERROR;
					}
					if(clientMsgList.length == 1) {//延迟Token获取
						synchronized (tokenSocketLock) {
							returnMsg = WechatConstants.WX_TOKEN_GET_SUCCESS;
						}
					}else if(clientMsgList.length == 2) {//Token获取
						if(WechatConstants.WX_TOKEN_GET_IN.equals(clientMsgList[1])) {
							returnMsg = WechatConstants.WX_TOKEN_GET_SUCCESS;
						}else {
							returnMsg = WechatConstants.WX_TOKEN_GET_DATA_ERROR;//参数错误
						}
					}else {
						returnMsg = WechatConstants.WX_TOKEN_GET_DATA_ERROR;//参数错误
					}
					if(WechatConstants.WX_TOKEN_GET_SUCCESS.equals(returnMsg)) {//获取成功
						lock.lock();
						//从socket获取token
						OutputStream outputStream = socket.getOutputStream();
						outputStream.write(AccessToken.getBytes());
						outputStream.close();
						is.close();
						socket.close();
						lock.unlock();
					}else {//获取失败
						OutputStream outputStream = socket.getOutputStream();
						outputStream.write(returnMsg.getBytes());
						outputStream.close();
						is.close();//关闭接收流
						socket.close();//关闭socket链接
					}
				}
			} catch (IOException e) {
				log.error(e.getMessage(),e);
				log.info("重启Token服务中");
				try {
					if(serverSocket != null) {
						serverSocket.close();
					}
					new Thread(new ScoketServer()).start();
				} catch (IOException e1) {
					log.error(e1.getMessage(),e1);
					log.info("Token服务重启失败");
				}
				
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
}
