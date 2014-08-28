import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.runner.RunnerWith;
import org.junit.runners.Parameterized.*;

import java.util.Collection;
import java.util.Arrays;

import java.awt.image.*;
import javax.imageio.*;
import java.awt.*;
import java.io.*;
import java.lang.*;


@RunWith(Parameterized.class)

public class ImageReaderTest
{
    //private string srcFile;
    //private string testFile;
    private FileInputStream srcFile;
    private FileInputStream testFile;
    private Image srcImage;
    private Image testImage;

    
    @Parameters   
    public static Collection<Object[]> prepareData()
    {  
	return Arrays.asList(new Object[][]{
		{"./src/1_red.bmp", "./goal/1_red_goal.bmp"},
		{"./src/1_green.bmp","./goal/1_green_goal.bmp"},
		{"./src/1_blue.bmp", "./goal/1_blue_goal.bmp"},
		{"./src/1_gray.bmp", "./goal/1_gray_goal.bmp"},
		
		{"./src/2_red.bmp", "./goal/2_red_goal.bmp"},
		{"./src/2_green.bmp","./goal/2_green_goal.bmp"},
		{"./src/2_blue.bmp", "./goal/2_blue_goal.bmp"},
		{"./src/2_gray.bmp", "./goal/2_gray_goal.bmp"},
	    });
    }
    
    public ImageReaderTest(String src, String test) throws Exception
    {
	this.srcFile = new FileInputStream(src);
	this.testFile = new FileInputStream(test);
	this.srcImage = ImageIO.read(srcFile);
	this.testImage = ImageIO.read(testFile);

    }
    
    @Before
    public void setUp() throws Exception
    {
	
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void testSize() throws Exception
    {
	assertEquals(srcImage.getWidth(null), testImage.getWidth(null));
	assertEquals(srcImage.getHeight(null), testImage.getHeight(null));

    }
    
    @Test
    public void testPixValue() throws Exception
    {
	int imageSize = srcImage.getWidth(null) * srcImage.getHeight(null);
       
        byte[] srcImageData = new byte[imageSize];
	byte[] testImageData = new byte[imageSize];

	srcFile.skip(54);
	testFile.skip(54);

	srcFile.read(srcImageData, 0, imageSize);
	testFile.read(testImageData, 0, imageSize);
	
	assertEquals(srcImageData, testImageData);
    
    }
}
