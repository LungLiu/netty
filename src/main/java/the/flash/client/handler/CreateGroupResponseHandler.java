package the.flash.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.flash.protocol.response.CreateGroupResponsePacket;

/**
 * @Author:MH
 * @Date:Created in 16:50 2018/10/20
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        System.out.print("群创建成功， id为["+ createGroupResponsePacket.getGroupId()+ "],");
        System.out.println("群里面有：" + createGroupResponsePacket.getUserNameList());
    }
}
