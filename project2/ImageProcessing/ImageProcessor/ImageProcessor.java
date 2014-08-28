/**
 * Get the chanel of the image and show several different
 * chanel of the image by using the RGBImageFilter to 
 * filt the image
 */

import imagereader.IImageProcessor;
import imagereader.*;
import java.awt.*;
import java.awt.image.*;


public class ImageProcessor implements IImageProcessor
{
    /**
     * show the chanel of red of the Image
     * @para sourceImage the source image to filt
     * @return the new image after filt
     */
    public Image showChanelR(Image sourceImage)
    {
        GetRedFilter filter = new GetRedFilter();
        	
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
        GetGreenFilter filter = new GetGreenFilter();
       
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
        GetBlueFilter filter = new GetBlueFilter();
       
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
        GrayFilter filter = new GrayFilter();
      
	FilteredImageSource filterSrc = 
	    new FilteredImageSource(sourceImage.getSource(), filter);

        Image newImage = Toolkit.getDefaultToolkit().createImage(filterSrc);

        return newImage;
    }
    
    /**
     * internal class by inherits the RGBImageFilter
     * filt the red color
     */
    class GetRedFilter extends RGBImageFilter
    {
        public GetRedFilter()
        {
	    // The filter's operation does not depend on the
	    // pixel's location, so IndexColorModels can be
	    // filtered directly.
            canFilterIndexColorModel = true;
        }

        public int filterRGB(int x, int y, int rgb)
        {
            return (rgb & 0xffff0000);
        }
    }
    
    
    /**
     * internal class by inherits the RGBImageFilter
     * filt the red green
     */
    class GetGreenFilter extends RGBImageFilter
    {
        public GetGreenFilter()
        {
	    // The filter's operation does not depend on the
	    // pixel's location, so IndexColorModels can be
	    // filtered directly.
            canFilterIndexColorModel = true;
        }

        public int filterRGB(int x, int y, int rgb)
        {
            return (rgb & 0xff00ff00);
        }
    }
    
    
    /**
     * internal class by inherits the RGBImageFilter
     * filt the blue color
     */
    class GetBlueFilter extends RGBImageFilter
    {
        public GetBlueFilter()
        {
	    // The filter's operation does not depend on the
	    // pixel's location, so IndexColorModels can be
	    // filtered directly.
            canFilterIndexColorModel = true;
        }

        public int filterRGB(int x, int y, int rgb)
        {
            return (rgb & 0xff0000ff);
        }
    }
    
    
    /**
     * internal class by inherits the RGBImageFilter
     * filt the gray color
     */
    class GrayFilter extends RGBImageFilter
    {
        public GrayFilter()
        {
	    // The filter's operation does not depend on the
	    // pixel's location, so IndexColorModels can be
	    // filtered directly.
            canFilterIndexColorModel = true;
        }

        public int filterRGB(int x, int y, int rgb)
        {
	    // change the color to gray
            int gray = (int)(((rgb & 0x00ff0000) >> 16) * 0.299
		+ ((rgb & 0x0000ff00) >> 8) * 0.587 
	        + (rgb & 0x000000ff) * 0.114);
	    
            int result = (rgb & 0xff000000) + (gray << 16) 
		+ (gray << 8) + gray;
	    
	    return result;
        } 
    }
}
