package awaresome.netty.procotol.response;

import awaresome.netty.procotol.Packet;
import lombok.Data;

import static awaresome.netty.procotol.command.Command.LOGIN_RESPONSE;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-15 15:04
 */
@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
