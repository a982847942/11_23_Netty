package juejinNetty.IM.util;

import io.netty.util.AttributeKey;

/**
 * @Classname Attributes
 * @Description
 * @Created by brain
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
