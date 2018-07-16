package socket.TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class TCPEchoServer {
	public static void main(String[] args) throws IOException{
		if (args.length != 1){
			throw new IllegalArgumentException("参数：<端口号>");
		}
		int port = Integer.parseInt(args[0]);
		
		ServerSocket serverSocket = new ServerSocket(port);
		
		int recvMsgSize;	//	接收到的消息的大小
		byte[] recivedBuf = new byte[1024];
		
		while(true){
			Socket socket = serverSocket.accept();
			
			InputStream ins = socket.getInputStream();
			OutputStream outs = socket.getOutputStream();
			
			SocketAddress clientSocket = socket.getRemoteSocketAddress();
			System.out.println("正在处理客户机：" + clientSocket);
			
			while((recvMsgSize = ins.read(recivedBuf)) != -1){
				outs.write(recivedBuf, 0, recvMsgSize);
			}
			socket.close();
			
			
			
		}
	}
}
