package awaresome.netty.procotol.response;

import awaresome.netty.procotol.Packet;
import lombok.Data;

import static awaresome.netty.procotol.command.Command.MESSAGE_RESPONSE;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-15 15:05
 */
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
