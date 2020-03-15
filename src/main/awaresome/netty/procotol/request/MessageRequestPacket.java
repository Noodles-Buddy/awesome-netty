package awaresome.netty.procotol.request;

import awaresome.netty.procotol.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static awaresome.netty.procotol.command.Command.MESSAGE_REQUEST;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-15 15:02
 */
@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    private String message;

    public MessageRequestPacket(String msg){
        this.message = msg;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
