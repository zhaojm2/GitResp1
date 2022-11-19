package com.zhaojm2.tank;

import com.zhaojm2.tank.factory.BaseTank;

public class DefaultFireStrategy implements FireStrategy {
    private static DefaultFireStrategy dfsInstance = new DefaultFireStrategy();
    private DefaultFireStrategy() {}
    public static DefaultFireStrategy getDfsInstance() {
        return dfsInstance;
    }
    @Override
    public void fire(BaseTank t) {
        new Bullet(t.getX()+16,t.getY()+17,t.dir,t.getGroup(),t.tf);
        if(t.getGroup() == Group.GOOD) {
            new Thread(() -> new Audio("D:/IDEA/tank/src/audio/tank_fire.wav").play()).start();
        }
    }
}
