package socket.TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;


public class TCPEchoClient {
	public static void main(String[] args) throws IOException{
		if(args.length <2 || args.length > 3){
			throw new IllegalArgumentException("Paramenter(s): <服务器><内容><端口>");
		}
		//	接收参数的处理
		String server = args[0];
		int port = Integer.parseInt(args[2]);
		byte[] data = args[1].getBytes();
		
		//	创建套接字
		Socket socket = new Socket(server, port);
		System.out.println("正在连接主机：" + server + "，连接端口为：" +  port);
		
		InputStream ins = socket.getInputStream();
		OutputStream outs = socket.getOutputStream();
		
		outs.write(data);
		
		int totalBytesRecv = 0;
		int bytesRecv;
		while(totalBytesRecv < data.length){
			if ((bytesRecv = ins.read(data, totalBytesRecv,data.length - totalBytesRecv)) == -1){
				throw new SocketException("连接关闭了");
			}
			totalBytesRecv += bytesRecv;
		}
		System.out.println("Recived:" + new String(data));
		socket.close();
	}
}
