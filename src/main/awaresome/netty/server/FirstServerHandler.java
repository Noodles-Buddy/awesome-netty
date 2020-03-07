package awaresome.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * 描述:逻辑处理器
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-07 19:35
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 1.打印收到的消息
     * 2.回复client
     *
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println("Server端收到的数据：" + byteBuf.toString(Charset.forName("utf-8")));

        //回复数据
        System.out.println("Server端开始写数据");
        ByteBuf buffer = getByteBuf(ctx);
        ctx.channel().writeAndFlush(buffer);
    }

    /**
     * 回复client端内容获取
     *
     * @param ctx
     * @return
     */
    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "client端您好，这是server端的第一条回复消息！".getBytes(Charset.forName("utf-8"));
        buffer.writeBytes(bytes);
        return buffer;
    }

}
