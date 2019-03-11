package com.phda.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * socket 网络编程，服务端
 * @author phda
 */
public class Server {

	public static void startServer() {
		
		try {
			ServerSocket server = new ServerSocket();
			InetSocketAddress netAddress = new InetSocketAddress("localhost", 21110) ;
			server.bind(netAddress);
			
			while(true) {
				System.err.println("等待接收请求：");
				Socket socket = server.accept();//等待接收网络请求
				System.err.println(socket);
				String str = getRequest(socket);//获取到请求
				responseContext(socket, str);//做出响应
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	private static String getRequest(Socket socket) {
		String str = null;
		byte[] context = new byte[1024];
		InputStream input;
		try {
			input = socket.getInputStream();
			int len = input.read(context);
			str = new String(context,0,len);
			System.out.print("接收到客户端内容:" + str);
			//input.close();//短连接
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			return str ;
		}
	}
	
	/**
	 * 统一响应
	 * @param socket
	 * @param context
	 * @param len
	 */
	private static void responseContext(Socket socket,String context) {
		try {
			StringBuffer strBuff = new StringBuffer();
			strBuff.append("服务端接收到请求了:【");
			strBuff.append(context);
			strBuff.append("】");
			
			OutputStream os = socket.getOutputStream();
			os.write(strBuff.toString().getBytes());
			os.flush();
			//socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		
		Server.startServer();
		
	}
	
	
	
	
	
	
}
