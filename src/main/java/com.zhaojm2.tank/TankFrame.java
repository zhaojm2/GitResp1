package com.zhaojm2.tank;

import com.zhaojm2.tank.factory.BaseTank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame{
    Tank myTank = new Tank(200,700,Dir.DOWN,Group.GOOD,this);
    List<BaseTank> tanks = new ArrayList<>();
    List<Bullet> bullets = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();
    PropertyMgr mgr = PropertyMgr.getIntance();
    String fireStrategyName = (String)mgr.get("fireStrategy");
    FireStrategy fs ;
    public static final int GAME_WIDTH=1280,GAME_HEIGHT=960;
    public TankFrame() {
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setTitle("tank war");
        setResizable(false);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new MyKeyListener());
        myTank.setMoving(false);
        myTank.setSPEED(15);
        try {
            fs = (FireStrategy) Class.forName(fireStrategyName).newInstance();
            System.out.println(fs);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;
        int key = 0;
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            key = e.getKeyCode();
            switch(key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir(myTank);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            key = e.getKeyCode();
            switch(key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire(fs);
                    break;
                default:
                    break;
            }
            setMainTankDir(myTank);

        }
        private void setMainTankDir(Tank tank) {
            if(!bL && !bU && !bR && !bD) tank.setMoving(false);
            else {
                tank.setMoving(true);
                if(bL){
                    tank.dir = Dir.LEFT;
                }
                if(bU) tank.dir = Dir.UP;
                if(bR) tank.dir = Dir.RIGHT;
                if(bD) tank.dir = Dir.DOWN;
                //if(bL&&bU) tank.dir = Dir.LEFTUP;
                //if(bR&&bU) tank.dir = Dir.RIGHTUP;
                //if(bL&&bD) tank.dir = Dir.LEFTDOWN;
                //if(bR&&bD) tank.dir = Dir.RIGHTDOWN;
            }

        }
    }
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("敌人的数量"+tanks.size(),10,60);
        g.drawString("子弹的数量"+bullets.size(),10,80);
        g.setColor(c);
        myTank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        for(int i=0;i < tanks.size();i++) {
            tanks.get(i).paint(g);
        }
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
    }
}

