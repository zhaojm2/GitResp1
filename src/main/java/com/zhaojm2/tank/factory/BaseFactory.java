package com.zhaojm2.tank.factory;

import com.zhaojm2.tank.TankFrame;

public abstract class BaseFactory {
   public abstract BaseTank createTank(TankFrame tf);
   public abstract BaseBullet createBullet(BaseTank t);
   public abstract BaseExplode createExplode();
}
