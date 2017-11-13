import com.chavrusa.im.bootstrap.launch.ServerLauncher;

/**
 *
 * @author Jerry
 * @date 2017/11/13 0013
 */
public class Bootstrap {
    public static void main(String[] args) throws Exception {
        ServerLauncher serverLauncher=new ServerLauncher();
        serverLauncher.startup();

    }
}
