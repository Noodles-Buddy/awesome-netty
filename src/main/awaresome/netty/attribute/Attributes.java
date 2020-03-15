package awaresome.netty.attribute;

import io.netty.util.AttributeKey;

public interface Attributes {
    /**
     * 登陆
     */
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
