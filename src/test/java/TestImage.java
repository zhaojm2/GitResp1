import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import static org.junit.Assert.assertNotNull;

public class TestImage {
    @Test
    public void test() {
        try {
            BufferedImage image = ImageIO.read(TestImage.class.getClassLoader().getResourceAsStream("BadTank1.png"));
            //assertNotNull(image);
            System.out.println(image);
            //System.out.println(TestImage.class.getClass().getClassLoader().getResourceAsStream("images/GoodTank1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
