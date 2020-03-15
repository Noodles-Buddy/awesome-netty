package awaresome.netty.procotol.command;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-15 15:00
 */
public interface Command {
    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;
}
