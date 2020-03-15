package awaresome.netty.protocol.response;

import awaresome.netty.protocol.Packet;
import lombok.Data;

import static awaresome.netty.protocol.command.Command.LOGIN_RESPONSE;


@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
