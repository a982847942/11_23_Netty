package base01.BIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @Classname BIOServer
 * @Description
 * @Date 2022/11/23 13:20
 * @Created by brain
 */
public class BIOServer extends Thread{
    private ServerSocket serverSocket = null;

    public static void main(String[] args) {
        BIOServer bioServer = new BIOServer();
        bioServer.start();

    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(7397));
            System.out.println("------------BIO Server start------------");
            while (true) {
                Socket socket = serverSocket.accept();
                BioServerHandler handler = new BioServerHandler(socket, Charset.forName("utf-8"));
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
