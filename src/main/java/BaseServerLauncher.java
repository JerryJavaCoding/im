import handler.ServerCoreHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jerry
 * @date 2017/11/12 0012
 *  框架启动抽象类
 */
public abstract class BaseServerLauncher {
    private static Logger logger= LoggerFactory.getLogger(BaseServerLauncher.class);
    private ServerCoreHandler serverCoreHandler=null;
    public static int PORT=71015;
    public static String appkey=null;
    public static boolean debug=true;
    public void startup(){}
    public void shutdown(){}
    public abstract void initListener();
}
