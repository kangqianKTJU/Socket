import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    ServerSocket ss;

    public Server(int port) throws IOException{
        ss = new ServerSocket(port);
        ss.setSoTimeout(100000);
    }

    public void run(){
        while(true){
            try{
                System.out.println("waiting for remote conecting..." + "\n the port is : " + ss.getLocalPort());
                Socket socket = ss.accept();
                System.out.println("remote host:" + socket.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(socket.getInputStream());
                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF("thanks for conecting!" + socket.getLocalSocketAddress() + "\n GoodBye!");
                in.close();
                out.close();
                socket.close();
            }catch(Exception ex){
                ex.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);

        try {
            Thread t = new Server(port);
            t.run();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}