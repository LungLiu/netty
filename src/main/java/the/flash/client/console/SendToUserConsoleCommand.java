package the.flash.client.console;

import io.netty.channel.Channel;
import the.flash.protocol.request.MessageRequestPacket;

import java.util.Scanner;

/**
 * @Author:MH
 * @Date:Created in 16:23 2018/10/20
 */
public class SendToUserConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个某个用户：");

        String toUserId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }
}