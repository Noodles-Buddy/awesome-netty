package awaresome.netty.procotol.request;

import awaresome.netty.procotol.Packet;
import lombok.Data;

import static awaresome.netty.procotol.command.Command.LOGIN_REQUEST;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-15 15:01
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
