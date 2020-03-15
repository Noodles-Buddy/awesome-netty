package awaresome.netty.protocol.request;

import awaresome.netty.protocol.Packet;
import lombok.Data;

import static awaresome.netty.protocol.command.Command.LOGIN_REQUEST;

@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
