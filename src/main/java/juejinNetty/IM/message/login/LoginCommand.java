package juejinNetty.IM.message.login;

/**
 * @Classname LoginCommand
 * @Description
 * @Date 2022/11/23 22:47
 * @Created by brain
 */
public interface LoginCommand {
    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;
}
