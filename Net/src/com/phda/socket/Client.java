package com.phda.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private static Socket s = null;
	static {
	}
	
	private static void sendMsg(String sendMsg) {
		try {
			s = new Socket("localhost", 21110);
			
			OutputStream os = s.getOutputStream();
			os.write(sendMsg.getBytes());
			os.flush();//清空缓存
			//os.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void getResponse() {
		try {
			System.out.print("连接状态："+s.isClosed()+" "+s.isConnected());
			InputStream in = s.getInputStream();
			byte[] readContext = new byte[1024];
			int len = in.read(readContext);
			System.out.println("读取到内容长度:"+len);
			//in.close();
			if(len != -1)
				System.err.println("client 读取到内容:"+new String(readContext,0,len));
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		Client.sendMsg("hujb");
		
		Thread.sleep(300000);
		
		
	}
	
	
}
