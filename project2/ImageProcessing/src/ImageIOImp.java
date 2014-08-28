
import java.awt.image.*;
import javax.imageio.*;
import java.awt.*;
import java.io.*;
import java.lang.*;

import imagereader.IImageIO;
import imagereader.*;

/**
 * implements Myread and Mywrite methods of the IImageIO class
 * to read the bmp image file in binary file and store the file
 * write the file 
 *
 */
public class ImageIOImp implements IImageIO {

    private final int andNumber = 0xff;
    private final int eight = 8;
    private final int sixteen = 16;
    private final int twenFour = 24;
    /**
     * read a image from the binary stream and store
     * @para filePath the path of the file
     * @return the image
     */
    public Image myRead(String filePath)  throws IOException {
	Image image; 

	try {
	    FileInputStream fileStream = new FileInputStream(filePath);

	    // 14 byte BMP file header
	    final int bmpHeaderLength = 14; 
	    // 40-byte BMP infomation
	    final int bmpInfoLength = 40; 

	    byte bmpHeader[] = new byte[bmpHeaderLength];
	    fileStream.read(bmpHeader, 0, bmpHeaderLength);

	    byte bmpInfo[] = new byte[bmpInfoLength];
	    fileStream.read(bmpInfo, 0, bmpInfoLength);

	    int bmpWidth = readBmpInfo(bmpInfo, 4);
	    int bmpHeight = readBmpInfo(bmpInfo, 8);
	    int imageSize = readBmpInfo(bmpInfo, 20);

	    int pixBits = readBmpInfo2(bmpInfo, 14);

	    if (pixBits == 24) {
		
		image = readImageData(fileStream, bmpWidth, bmpHeight, imageSize);
	
	    } else {

		System.out.println("Not a 24-bit Bitmap, aborting...");
		image = (Image)null;
	    }

	    fileStream.close();
	    return image;

	} catch (IOException e) {

	    return (Image)null;
	}

	//return (Image)null;
    }

    /**
     * read the image data into the binary stream
     * @para fileStream the input file stream
     * @para widht the width of the Image
     * @para height the height of the file
     * @para size the size of the image
     */
    public Image readImageData(FileInputStream fileStream, int width, int height, int size) 
	throws IOException {

	try {

	    int numPads = (size / height) - width * 3;

	    if(numPads == 4){
		numPads = 0;
	    }

	    int imageData[] = new int[width * height];
	    byte bmpRgb[] = new byte[size];

	    fileStream.read(bmpRgb, 0, size);
		       
	    int index = 0;
	    for(int c = 0; c < height; c++) {
		for(int r = 0; r < width; r++) {
		    imageData[width * (height - c - 1) + r] =
			(255 & andNumber) << twenFour
			| (((int)bmpRgb[index + 2] & andNumber) << sixteen)
			| (((int)bmpRgb[index + 1] & andNumber) << eight)
			| (int)bmpRgb[index] & andNumber;
		    index += 3;
		}
		index += numPads;
	    }
		       
	    Image image = Toolkit.getDefaultToolkit().createImage
		(new MemoryImageSource( width, height, imageData, 0, width));

	    return image;

	} catch (IOException e) {

	    return (Image)null;
	}

	//return (Image)null;
	
    }
    /**
     *  create a Graphics2D windows and show the Image
     * @para image the source Image
     * @para filePath the file name
     */ 
    public Image myWrite(Image image, String filePath) throws IOException
    {
	try {
	    File imageFile = new File(filePath + ".bmp");

	    final int width = image.getWidth(null);
	    final int height =  image.getHeight(null);

	    BufferedImage bmpInfo = new BufferedImage
		(width, height, BufferedImage.TYPE_INT_RGB);

	    Graphics2D g2 = bmpInfo.createGraphics();
	    g2.drawImage(image, 0, 0, null);
	    g2.dispose();

	    ImageIO.write(bmpInfo, "bmp", imageFile);

	} catch (IOException e) {
	    return (Image)null;
	}

	return image;	
    }

    /**
     * read the infomation of the image 
     * change the little endian number to integer
     * @para info the infomation array
     * @para the start index to read
     */
    public int readBmpInfo(byte[] info, int start) {
	
	if (info == null) {
	    return 0;
	}
	
	int result = (((int)info[start + 3] & andNumber) << twenFour)
	    | (((int)info[start + 2] & andNumber) << sixteen)
	    | (((int)info[start + 1] & andNumber) << eight)
	    | (int)info[start] & andNumber;

	return result;
    }

    /**
     * read the infomation of the image 
     * change the little endian number to integer
     * @para info the infomation array
     * @para the start index to read
     */
    public int readBmpInfo2(byte[] info, int start) {
	
	if (info == null) {
	    return 0;
	}
	
	int result = (((int)info[start + 1] & andNumber) << eight)
	    | (int)info[start] & andNumber;

	return result;
    }

}
