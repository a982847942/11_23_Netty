package base01.BIO;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @Classname BIOClient
 * @Description
 * @Date 2022/11/23 13:13
 * @Created by brain
 */
public class BIOClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.1.116", 7397);
            System.out.println("------------BIO Client start------------");
            BioClientHandler bioClientHandler = new BioClientHandler(socket, Charset.forName("utf-8"));
            bioClientHandler.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
