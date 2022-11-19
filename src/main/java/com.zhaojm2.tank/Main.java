package com.zhaojm2.tank;

import com.zhaojm2.tank.factory.BaseFactory;
import com.zhaojm2.tank.factory.DefaultFactory;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        TankFrame tf = new TankFrame();
        BaseFactory df = new DefaultFactory();
        int tankCout = Integer.parseInt((String)PropertyMgr.getIntance().get("initCount"));
        new Thread(() -> {
            while(true) {
                new Audio("D:/IDEA/tank/src/audio/war1.wav").play();
                try {
                    Thread.sleep(35000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        for(int i=0;i<tankCout;i++) {
            tf.tanks.add(df.createTank(tf));
        }
        new Thread(() -> {
                    try {
                        while (true) {
                            ResourceMgr.TANKU = ImageIO.read(new File("D:/IDEA/tank/src/images/BadTank1.png"));
                            Thread.sleep(5000);
                            ResourceMgr.TANKU = ImageIO.read(new File("D:/IDEA/tank/src/images/BadTank2.png"));
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }).start();
        while(true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tf.repaint();
        }
    }
}
