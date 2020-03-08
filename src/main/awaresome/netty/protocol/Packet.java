package awaresome.netty.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-08 17:03
 */
@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;

    /**
     * 命令类型
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();

}
