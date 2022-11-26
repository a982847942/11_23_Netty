package juejinNetty.IM.util;

import io.netty.util.AttributeKey;
import juejinNetty.IM.session.Session;

/**
 * @Classname Attributes
 * @Description
 * @Created by brain
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
