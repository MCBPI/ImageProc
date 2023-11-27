package image;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;

public class imageProcess {

    public static void main(String[] args) {
        String sourceFolderPath = "D:\\Учеба\\Ане\\before";
        String destinationFolderPath = "D:\\Учеба\\Ане\\after";

        int newWidth = 256;
        int newHeight = 256;

        File sourceFolder = new File(sourceFolderPath);
        File[] imageFiles = sourceFolder.listFiles();

        if (imageFiles != null) {
            for (File imageFile : imageFiles) {
                try {
                    BufferedImage originalImage = ImageIO.read(imageFile);

                    Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D graphics = newImage.createGraphics();
                    graphics.drawImage(resizedImage, 0, 0, null);
                    graphics.dispose();

                    BufferedImage blackAndWhiteImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_BINARY);
                    Graphics2D bwGraphics = blackAndWhiteImage.createGraphics();
                    bwGraphics.drawImage(newImage, 0, 0, null);
                    bwGraphics.dispose();

                    String fileName = imageFile.getName().replaceFirst("[.][^.]+$", "");

                    File destinationFile = new File(destinationFolderPath + "/" + fileName + "_resized_bw.png");
                    ImageIO.write(blackAndWhiteImage, "png", destinationFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Изображения успешно обработаны и сохранены.");
        } else {
            System.out.println("В указанной папке нет изображений.");
        }
    }
}