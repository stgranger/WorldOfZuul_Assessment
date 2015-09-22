package GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class is create to load and manage an image in the game
 * @author Stevosh
 *
 */
public class ImageLoader
{
	// the bufferedImage to contain the image
	private BufferedImage image;
	
	public ImageLoader()
	{}
	
	/**
	 * this method try to load an image with a read on the filme in parameter
	 * @param name
	 */
	public void loadImage(String name)
	{
		try
		{
			String separator = System.getProperty("file.separator");
			image = ImageIO.read(new File("src" + separator + "Assets" + separator + name));
		}
		catch (IOException ex)
		{
		}		
	}
	
	/**
	 * get the image
	 * @return
	 */
	public BufferedImage getImage()
	{
		return this.image;
	}
}