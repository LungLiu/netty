package the.flash.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Author:MH
 * @Date:Created in 14:09 2018/10/17
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + ": 客户端写出数据");
        //1.获取数据
        ByteBuf buffer = getByteBuf(ctx);
        //2.写数据
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date() + ":客户端读到数据 ->" + byteBuf.toString(Charset.forName("utf-8")));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        //1.获取二进制抽象buffer
        ByteBuf buffer = ctx.alloc().buffer();
        //2.准备数据，指定字符串的字符集为UTF-8
        byte[] bytes ="你好，陌小歌！".getBytes(Charset.forName("utf-8"));
        //3.填充数据到 ByteBuf
        buffer.writeBytes(bytes);

        return buffer;
    }
}