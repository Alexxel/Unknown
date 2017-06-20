package MainGame;


import javax.imageio.ImageIO;

import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class Image
{
	List<BufferedImage> Images;
	
	Image()
	{
		Images = new ArrayList<BufferedImage>();
	}
	public void add(String filePath)
	{
		BufferedImage Image;
		try{
	    	Image = ImageIO.read(getClass().getResource(filePath));
	    	Images.add(Image);
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public BufferedImage getImage(int postion)
	{
		return Images.get(postion);
	}
	
	public BufferedImage getImageClip(int p,int x,int y, int width, int height)
	{
		System.out.println(x + " " + y + " " + width + " " + height);
		return Images.get(p).getSubimage(x, y, width, height);
	}
}