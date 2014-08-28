/**
 * Get the chanel of the image and show several different
 * chanel of the image by using the RGBImageFilter to 
 * filt the image
 */

import imagereader.IImageProcessor;
import imagereader.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.Color;


public class ImageProcessor implements IImageProcessor
{
    /**
     * show the chanel of red of the Image
     * @para sourceImage the source image to filt
     * @return the new image after filt
     */
    public Image showChanelR(Image sourceImage)
    {
        ColorFilter filter = new ColorFilter("red");
        	
	FilteredImageSource filterSrc = 
	    new FilteredImageSource(sourceImage.getSource(), filter);

        Image newImage = Toolkit.getDefaultToolkit().createImage(filterSrc);

        return newImage;
    }
    

    /**
     * show the chanel of green of the Image
     * @para sourceImage the source image to filt
     * @return the new image after filt
     */
    public Image showChanelG(Image sourceImage)
    {
        ColorFilter filter = new ColorFilter("green");
       
	FilteredImageSource filterSrc = 
	    new FilteredImageSource(sourceImage.getSource(), filter);

        Image newImage = Toolkit.getDefaultToolkit().createImage(filterSrc);

        return newImage;
    }
    

    /**
     * show the chanel of blue of the Image
     * @para sourceImage the source image to filt
     * @return the new image after filt
     */
    public Image showChanelB(Image sourceImage)
    {
        ColorFilter filter = new ColorFilter("blue");
       
	FilteredImageSource filterSrc = 
	    new FilteredImageSource(sourceImage.getSource(), filter);

        Image newImage = Toolkit.getDefaultToolkit().createImage(filterSrc);

        return newImage;
    }
    
    
    /**
     * show the chanel of gray of the Image
     * @para sourceImage the source image to filt
     * @return the new image after filt
     */
    public Image showGray(Image sourceImage)
    {
        ColorFilter filter = new ColorFilter("gray");
      
	FilteredImageSource filterSrc = 
	    new FilteredImageSource(sourceImage.getSource(), filter);

        Image newImage = Toolkit.getDefaultToolkit().createImage(filterSrc);

        return newImage;
    }
    
    /**
     * internal class by inherits the RGBImageFilter
     * filt the color by the given color
     */
    class ColorFilter extends RGBImageFilter
    {
        public ColorFilter(String c)
        {
	    // The filter's operation does not depend on the
	    // pixel's location, so IndexColorModels can be
	    // filtered directly.
            canFilterIndexColorModel = true;
	    color = c;
        }

        public int filterRGB(int x, int y, int rgb)
        {
	    int result = 0;

	    switch (color) {

	    case "red":
		result = (rgb & 0xffff0000);
		break;
		
	    case "green": 
		result =  (rgb & 0xff00ff00);
		break;
		
	    case "blue": 
		result =  (rgb & 0xff0000ff);
		break;
	    
	    case "gray":
		// change the color to gray
		int gray = (int)(((rgb & 0x00ff0000) >> 16) * 0.299
				 + ((rgb & 0x0000ff00) >> 8) * 0.587 
				 + (rgb & 0x000000ff) * 0.114);
	    
		result = (rgb & 0xff000000) + (gray << 16) 
		    + (gray << 8) + gray;
	    
		break;
	    
	    default: 
		break;

	    }
	    return result;
        } 
    }

    private String color;
}
