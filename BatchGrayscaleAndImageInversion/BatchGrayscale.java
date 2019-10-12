
/**
 * This program batch processes several images, and creates and 
 * saves new images (with new filenames) that are grayscale versions 
 * of each image.
 * @author Hsingyi Lin
 * @version 08/31/2019
 */
import edu.duke.*;
import java.io.File;

public class BatchGrayscale {
    public ImageResource getGrayscaleImage(ImageResource inImg) {
        ImageResource outImg = new ImageResource(inImg.getWidth(), 
                                                 inImg.getHeight());
        for (Pixel outPx : outImg.pixels()) {
            Pixel inPx = inImg.getPixel(outPx.getX(), outPx.getY());
            int avgRGB = (inPx.getRed() + inPx.getGreen() + inPx.getBlue()) / 3;
            outPx.setRed(avgRGB);
            outPx.setGreen(avgRGB);
            outPx.setBlue(avgRGB);
        }
        return outImg;
    }
    
    public void convertGrayscaleImages() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            ImageResource grayImg = getGrayscaleImage(image);
            grayImg.setFileName("gray-" + image.getFileName());
            grayImg.save();
        }
    }
}
