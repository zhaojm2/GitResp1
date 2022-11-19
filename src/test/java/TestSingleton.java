import com.zhaojm2.tank.PropertyMgr;

public class TestSingleton {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(PropertyMgr.getIntance().hashCode());
            }).start();
        }
    }
}
