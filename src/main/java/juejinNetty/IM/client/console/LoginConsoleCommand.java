package juejinNetty.IM.client.console;

import io.netty.channel.Channel;
import juejinNetty.IM.packet.login.LoginRequestPacket;

import java.util.Scanner;

/**
 * @Classname LoginConsoleCommand
 * @Description
 * @Date 2022/11/26 11:33
 * @Created by brain
 */
public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        System.out.print("输入用户名登录: ");
        String username = scanner.nextLine();
        loginRequestPacket.setUserName(username);
        // 密码使用默认的
        loginRequestPacket.setPassword("pwd");
        // 发送登录数据包
        channel.writeAndFlush(loginRequestPacket);
//        waitForLoginResponse();
    }
}
