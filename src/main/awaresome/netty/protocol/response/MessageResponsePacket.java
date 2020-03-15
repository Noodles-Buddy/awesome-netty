package awaresome.netty.protocol.response;

import awaresome.netty.protocol.Packet;
import lombok.Data;

import static awaresome.netty.protocol.command.Command.MESSAGE_RESPONSE;


@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
