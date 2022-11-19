package com.zhaojm2.tank.factory;

import com.zhaojm2.tank.Dir;
import com.zhaojm2.tank.Group;
import com.zhaojm2.tank.TankFrame;

import java.awt.*;

public abstract class BaseTank {
    public int x=200,y=500;
    public Rectangle rect ;
    public  Dir dir = Dir.DOWN;
    public TankFrame tf = null;
    public abstract int getX();
    public abstract int getY();
    public abstract Group getGroup();
    public abstract void paint(Graphics g);
    public abstract void die();
}
