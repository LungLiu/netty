package the.flash.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import the.flash.protocol.Packet;
import the.flash.protocol.request.LoginRequestPacket;
import the.flash.protocol.PackerCodec;
import the.flash.protocol.response.LoginResponsePacket;

import java.util.Date;
import java.util.UUID;

/**
 * @Author:MH
 * @Date:Created in 16:33 2018/10/17
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx)  {
        System.out.println(new Date() + ":客戶端开始登陆");

        //创建登陆就对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("flash");
        loginRequestPacket.setPassword("pwd");

        //编码
        ByteBuf buffer = PackerCodec.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        //写数据
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        ByteBuf byteBuf = (ByteBuf)msg;
        Packet packet = PackerCodec.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if (loginResponsePacket.isSuccess()){
                System.out.println(new Date() + "：客户端登录成功");
            }else {
                System.out.println(new Date() + ":客户端登录失败,原因：" + loginResponsePacket.getReason());
            }
        }
    }
}