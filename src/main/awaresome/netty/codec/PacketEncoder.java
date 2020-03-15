package awaresome.netty.codec;

import awaresome.netty.procotol.Packet;
import awaresome.netty.procotol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 描述: 省略了，ctx.writeAndFlush()
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-15 15:46
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) {
        PacketCodeC.INSTANCE.encode(out, packet);
    }
}
