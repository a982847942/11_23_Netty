package juejinNetty.IM.util;

import java.util.UUID;

/**
 * @Classname IDUtil
 * @Description
 * @Date 2022/11/26 13:11
 * @Created by brain
 */
public class IDUtil {
    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
