package cn.huang.wechat.automessagereply;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 图片消息
 * @author huangyejun   
 *
 */
public class ImageMerge
{
    private static final String path = ImageMerge.class.getResource("").getPath();
    
    public static void main(String[] args) throws IOException
    {
        File file1 = new File(path, "1.jpg");
        File file2 = new File(path, "2.jpg");
        mergeImage(file1, file2);
    }
   
    public static void mergeImage(File file1, File file2) throws IOException
    {
        BufferedImage image1 = ImageIO.read(file1);
        BufferedImage image2 = ImageIO.read(file2);
        
        BufferedImage combined = new BufferedImage(image1.getWidth() * 2, image1.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        Graphics g = combined.createGraphics();
        g.drawImage(image1, 0, 0, null);
        g.drawImage(image2, 20, 20, null);
        
        ImageIO.write(combined, "JPG", new File(path, "3.jpg"));
    }
}
