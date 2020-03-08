package awaresome.netty.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 描述: 协议体
 * command 决定 报文内容 解析成何种实体
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-08 15:06
 */
@Data
public abstract class Packet {

    /**
     * 协议版本 不加密
     */
    @JSONField(deserialize = false, serialize = false)
    private byte version = 1;

    /**
     * 命令获取方法 不加密
     * @return
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
