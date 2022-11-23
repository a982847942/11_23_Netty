package juejinNetty.IM.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @Classname LoginUtil
 * @Description
 * @Date 2022/11/23 23:06
 * @Created by brain
 */
public class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }
}
