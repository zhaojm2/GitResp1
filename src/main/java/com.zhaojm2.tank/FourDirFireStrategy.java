package com.zhaojm2.tank;

import com.zhaojm2.tank.factory.BaseTank;
import com.zhaojm2.tank.factory.DefaultFactory;

public class FourDirFireStrategy implements FireStrategy {
    //private static FourDirFireStrategy fdfsInstance = new FourDirFireStrategy();
    DefaultFactory df = new DefaultFactory();
    public FourDirFireStrategy() {}
    /*public static FourDirFireStrategy getFdfsInstance() {
        return fdfsInstance;
    }*/
    @Override
    public void fire(BaseTank t) {
        if(t.getGroup() == Group.GOOD) {
            for (Dir dir:Dir.values()) {
                if(dir!= Dir.LEFTDOWN || dir!=Dir.RIGHTDOWN || dir!=Dir.LEFTUP || dir!=Dir.RIGHTUP) {
                    new Bullet(t.getX()+16,t.getY()+17,dir,t.getGroup(),t.tf);
                }
            }
            new Thread(() -> new Audio("D:/IDEA/tank/src/audio/tank_fire.wav").play()).start();
        }else {
            df.createBullet(t);
        }

    }
}
