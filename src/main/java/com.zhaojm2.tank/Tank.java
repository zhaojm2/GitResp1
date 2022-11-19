package com.zhaojm2.tank;

import com.zhaojm2.tank.factory.BaseTank;

import java.awt.*;
import java.util.Random;

public class Tank extends BaseTank {

    public int getSPEED() {
        return SPEED;
    }

    public void setSPEED(int SPEED) {
        this.SPEED = SPEED;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    private static final int WIDTH = ResourceMgr.TANKU.getWidth();
    private static final int HEIGHT = ResourceMgr.TANKU.getHeight();
    private int SPEED = 5;

    public Dir getDir() {
        return dir;
    }
    private boolean moving = true;
    private boolean live = true;
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group group = Group.BAD;
    Random random = new Random();
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

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = randomDir();
        this.group = group;
        this.tf = tf;
        rect = new Rectangle();
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics g) {
        if(!live) tf.tanks.remove(this);
        switch(dir) {
            case LEFT:
                if(this.group == Group.BAD)
                    g.drawImage(ResourceMgr.TANKL,x,y,null);
                else
                    g.drawImage(ResourceMgr.MYTANKL,x,y,null);
                break;
            case UP:
                if(this.group == Group.BAD)
                    g.drawImage(ResourceMgr.TANKU,x,y,null);
                else
                    g.drawImage(ResourceMgr.MYTANKU,x,y,null);
                break;
            case RIGHT:
                if(this.group == Group.BAD)
                    g.drawImage(ResourceMgr.TANKR,x,y,null);
                else
                    g.drawImage(ResourceMgr.MYTANKR,x,y,null);
                break;
            case DOWN:
                if(this.group == Group.BAD)
                    g.drawImage(ResourceMgr.TANKD,x,y,null);
                else
                    g.drawImage(ResourceMgr.MYTANKD,x,y,null);
                break;
            default:
                break;
        }
        move();
    }
    public void move() {
        if(!moving ) return ;
        if(this.group == Group.GOOD) {
            new Thread(() -> new Audio("D:/IDEA/tank/src/audio/tank_move.wav").play()).start();
        }
        switch(dir) {
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
        rect.x = this.x;
        rect.y = this.y;
        if(this.group == Group.BAD && random.nextInt(1000)>950) {
            this.fire(this.tf.fs);
            this.dir = randomDir();
        }
        boundsCheck();
    }

    private void boundsCheck() {
        if(this.x<20) this.x = 20;
        else if(this.y<Tank.getHEIGHT()-20) this.y = Tank.getHEIGHT()-20;
        else if(this.x>TankFrame.GAME_WIDTH - Tank.getWIDTH()-20) this.x = TankFrame.GAME_WIDTH - Tank.getWIDTH()-20;
        else if(this.y>TankFrame.GAME_HEIGHT - Tank.getHEIGHT()-20) this.y = TankFrame.GAME_HEIGHT - Tank.getHEIGHT()-20;
    }

    public void fire(FireStrategy fs) {
        fs.fire(this);
    }

    public void die() {
        this.live = false;
    }

    public static Dir randomDir() {
        int randomNum = new Random().nextInt(4);
        Dir randomDir = Dir.DOWN;
        switch(randomNum) {
            case 0:
                randomDir = Dir.DOWN;
                break;
            case 1:
                randomDir = Dir.UP;
                break;
            case 2:
                randomDir = Dir.LEFT;
                break;
            case 3:
                randomDir = Dir.RIGHT;
                break;
            default:
                break;
        }
        return randomDir;
    }
}
