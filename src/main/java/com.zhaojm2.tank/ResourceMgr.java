package com.zhaojm2.tank;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResourceMgr {
    public static BufferedImage TANKL,TANKU,TANKR,TANKD;
    public static BufferedImage BULLETL,BULLETU,BULLETR,BULLETD;
    public static BufferedImage MYTANKL,MYTANKU,MYTANKR,MYTANKD;
    public static BufferedImage[] explodes = new BufferedImage[16];
    static {
        try {
            TANKU = ImageIO.read(new File("D:/IDEA/tank/src/images/BadTank2.png"));
            TANKL = ImageUtil.rotateImage(TANKU,-90);
            TANKR = ImageUtil.rotateImage(TANKU,90);
            TANKD = ImageUtil.rotateImage(TANKU,-180);

            MYTANKU = ImageIO.read(new File("D:/IDEA/tank/src/images/GoodTank1.png"));
            MYTANKL = ImageUtil.rotateImage(MYTANKU,-90);
            MYTANKR = ImageUtil.rotateImage(MYTANKU,90);
            MYTANKD = ImageUtil.rotateImage(MYTANKU,-180);

            BULLETU = ImageIO.read(new File("D:/IDEA/tank/src/images/bulletU.png"));
            BULLETL = ImageUtil.rotateImage(BULLETU,-90);
            BULLETR = ImageUtil.rotateImage(BULLETU,90);
            BULLETD = ImageUtil.rotateImage(BULLETU,180);
            for (int i = 0; i < explodes.length; i++) {
                explodes[i] = ImageIO.read(new File("D:/IDEA/tank/src/images/e"+(i+1)+".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
