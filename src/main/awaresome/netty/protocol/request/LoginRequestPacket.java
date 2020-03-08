package awaresome.netty.protocol.request;

import awaresome.netty.protocol.Packet;
import lombok.Data;

import static awaresome.netty.protocol.command.Command.LOGIN_REQUEST;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-08 17:05
 */
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
