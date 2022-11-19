package com.zhaojm2.tank;

import java.awt.*;

public class Explode {
    private int x,y;
    private int step = 0;
    private static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    private static final int HEIGHT = ResourceMgr.explodes[0].getHeight();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private boolean live = true;
    TankFrame tf =null;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(() -> new Audio("D:/IDEA/tank/src/audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++],this.x,this.y,null);
        if(step >= ResourceMgr.explodes.length) {
            step = 0;
            tf.explodes.remove(this);
        }
    }
}