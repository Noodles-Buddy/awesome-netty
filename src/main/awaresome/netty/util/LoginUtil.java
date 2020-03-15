package awaresome.netty.util;

import awaresome.netty.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-15 10:48
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}
