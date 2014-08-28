import imagereader.Runner;
import imagereader.*;

/**
 * the main function of the image program
 * usign the Runner of the imagereader
 */
final class ImageRunner {

	public static void main(String[] args){

		ImageIOImp imageioer = new ImageIOImp();
		ImageProcessor processor = new ImageProcessor();

		// using the run method of the Runner
		Runner.run(imageioer, processor);
	}

    private ImageRunner() {};
}
