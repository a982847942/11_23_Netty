package juejinNetty.IM.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Classname ConsleCommand
 * @Description
 * @Date 2022/11/26 11:29
 * @Created by brain
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
