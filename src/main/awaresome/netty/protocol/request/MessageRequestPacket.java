package awaresome.netty.protocol.request;

import awaresome.netty.protocol.Packet;
import lombok.Data;

import static awaresome.netty.protocol.command.Command.MESSAGE_REQUEST;


@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
