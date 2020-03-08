package awaresome.netty.protocol.response;

import awaresome.netty.protocol.Packet;
import lombok.Data;

import static awaresome.netty.protocol.command.Command.LOGIN_RESPONSE;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-08 17:06
 */
@Data
public class LoginResponsePacket extends Packet {
    /**
     * 登陆结果
     */
    private boolean success;
    /**
     * 失败原因
     */
    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
