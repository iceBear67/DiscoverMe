import io.ib67.DiscoverMe;
import io.ib67.SimpleChatColor;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        //System.out.println(parseAnnouncementAddressPort(new ServerEntry("motdaaa",null,-1).toString()));
        DiscoverMe.INSTANCE.addEntry(SimpleChatColor.randomColorize("NullCat 女生自用服务器 | 女装 嘿客 湿法炼铜"));
        while (true) Thread.sleep(1000);
    }
}
