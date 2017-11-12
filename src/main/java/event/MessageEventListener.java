package event;

/**
 * @author Jerry
 * @date 2017/11/12 0012
 *
 */
public interface MessageEventListener {
    boolean ontransbufferC2cCallback();
    boolean onTransBufferCallback();
    void onUserLoginActionCallback();
}
