package awaresome.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-07 19:52
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("Client端开始写数据！");
        //1.获取数据
        ByteBuf byteBuf = getByteBuf(ctx);
        //2.发送数据到server端
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("Client端收到Server端数据！");
        //1.解析
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println(new Date() + "Client收到的回复内容为：" + byteBuf.toString(Charset.forName("utf-8")));

    }

    private static ByteBuf getByteBuf(ChannelHandlerContext ctx){
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "这是Client端发送端第一条消息，请查收！".getBytes(Charset.forName("utf-8"));
        buffer.writeBytes(bytes);
        return buffer;
    }

}
