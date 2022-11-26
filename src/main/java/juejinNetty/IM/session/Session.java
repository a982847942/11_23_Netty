package juejinNetty.IM.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname Session
 * @Description
 * @Date 2022/11/26 9:42
 * @Created by brain
 */
@Data
@NoArgsConstructor
public class Session {
    //用户标识
    private String userId;
    private String userName;

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
