package awaresome.netty.protocol;

import lombok.Data;

/**
 * 描述:登陆 协议体
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-08 15:11
 */
@Data
public class LoginRequestPacket extends Packet {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }

}
