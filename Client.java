import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.xml.sax.InputSource;

public class Client{
    public static void main(String[] args){
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            System.out.println("Now, connecting to: " + serverName + ",port:" + port);
            Socket clientSocket = new Socket(serverName, port);
            System.out.println("Remote host address:" + clientSocket.getRemoteSocketAddress());
            OutputStream ops = clientSocket.getOutputStream();
            DataOutputStream out = new DataOutputStream(ops);
            
            out.writeUTF("Hello from" + clientSocket.getLocalSocketAddress() );
            InputStream ips = clientSocket.getInputStream();
            DataInputStream in = new DataInputStream(ips);
            System.out.println("Server's response" + in.readUTF());
            clientSocket.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}