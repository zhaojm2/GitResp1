package com.zhaojm2.tank;

import com.zhaojm2.tank.factory.BaseBullet;
import com.zhaojm2.tank.factory.BaseTank;

import java.awt.*;

public class Bullet extends BaseBullet {
    private int x,y;
    private static final int SPEED = 20;
    Dir dir = Dir.DOWN;
    public Group group = Group.BAD;
    private static final int WIDTH = 20;

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

    private static final int HEIGHT = 30;
    private boolean live = true;
    TankFrame tf =null;
    Rectangle bulletRect ;

    public Bullet(int x, int y, Dir dir,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
        bulletRect = new Rectangle();
        bulletRect.x = this.x;
        bulletRect.y = this.y;
        bulletRect.width = WIDTH;
        bulletRect.height = HEIGHT;
        tf.bullets.add(this);
    }

    public void paint(Graphics g) {
        if(!live) {
            tf.bullets.remove(this);
        }
        switch(dir) {
            case LEFT:
                g.drawImage(ResourceMgr.BULLETL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.BULLETU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.BULLETR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.BULLETD,x,y,null);
                break;
        }
        move();
    }

    public void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFTUP:
                y -= SPEED;
                x -= SPEED;
                break;
            case RIGHTUP:
                y -= SPEED;
                x += SPEED;
                break;
            case LEFTDOWN:
                y += SPEED;
                x -= SPEED;
                break;
            case RIGHTDOWN:
                y += SPEED;
                x += SPEED;
                break;
            default:
                break;
        }
        bulletRect.x = this.x;
        bulletRect.y = this.y;
        if(x<0 || y<0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT)
            live = false;
    }

    public void collideWith(BaseTank tank) {
        if(this.group == tank.getGroup()) return;
        //Rectangle myTankRect = new Rectangle(tf.myTank.getX(),tf.myTank.getY(),Tank.getWIDTH(),Tank.getHEIGHT());
        if(bulletRect.intersects(tank.rect)) {
            this.die();
            tank.die();
            tf.explodes.add(new Explode(tank.getX(),tank.getY(),tf));
        }/*else if(bulletRect.intersects(myTankRect)) {
            this.die();
            tf.myTank.die();
            tf.explodes.add(new Explode(tank.getX(),tank.getY(),tf));
        }*/

    }

    private void die() {
        this.live=false;
    }
}