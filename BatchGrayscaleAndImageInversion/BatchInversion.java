
/**
 * This program creates new images that are photographic negatives (or inverted images) of selected 
 * images and saves these new images.
 * @author Hsingyi Lin
 * @version 08/31/2019
 */
import edu.duke.*;
import java.io.File;

public class BatchInversion {
    public ImageResource getInversionImage(ImageResource inImg) {
        ImageResource outImg = new ImageResource(inImg.getWidth(), 
                                                 inImg.getHeight());
        for (Pixel outPx : outImg.pixels()) {
            Pixel inPx = inImg.getPixel(outPx.getX(), outPx.getY());
            outPx.setRed(255 - inPx.getRed());
            outPx.setGreen(255 - inPx.getGreen());
            outPx.setBlue(255 - inPx.getBlue());
        }
        return outImg;
    }
    
    public void convertInversionImages() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            ImageResource invertedImg = getInversionImage(image);
            invertedImg.setFileName("inverted-" + image.getFileName());
            invertedImg.save();
        }
    }

}
