package com.zhaojm2.tank.factory;

import com.zhaojm2.tank.*;

import java.util.Random;

public class DefaultFactory extends BaseFactory {

    @Override
    public BaseTank createTank(TankFrame tf) {
        return new Tank((new Random().nextInt(1280-Tank.getWIDTH()-10)-Tank.getWIDTH()),
                (new Random().nextInt(460)+Tank.getHEIGHT()), Dir.DOWN, Group.BAD,tf);
    }

    @Override
    public BaseBullet createBullet(BaseTank t) {
        return new Bullet(t.getX()+16,t.getY()+17,t.dir,t.getGroup(),t.tf);
    }

    @Override
    public BaseExplode createExplode() {
        return null;
    }
}
