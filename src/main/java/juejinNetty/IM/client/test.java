package juejinNetty.IM.client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @Classname test
 * @Description
 * @Date 2022/11/24 20:56
 * @Created by brain
 */
public class test {
    void bug() {
        new Thread(() -> {
            while (!Thread.interrupted()) {

                Scanner sc = new Scanner(System.in);
                StringBuilder str = new StringBuilder();
                System.out.println("请输入要显示的信息:");
                while (sc.hasNext()) {
                    str.append(sc.nextLine());
                }
                try {
                    System.in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sc.close();
                System.out.println(str.toString());
            }
        }).start();
    }

    public static void main(String[] args) {

        new Thread(() -> {
            while (true){
                Scanner sc = new Scanner(System.in);
                StringBuilder builder = new StringBuilder();
                System.out.println("enter text information:");
                while (!sc.hasNext("#")) {
                    builder.append(sc.nextLine());
                }
                System.out.println(builder.toString());
            }
        }).start();
    }
}