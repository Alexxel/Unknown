package MainGame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.awt.Color;


public class MapBackground
{
	
	boolean hotSpot;
	boolean town;
	String townName;
	boolean water;
	int waterXSize;
	int waterYSize;
	boolean mountain;
	int mountainSize;
	Color[][] color;
	boolean road;
	
	
	
	
	
	MapBackground()
	{
		hotSpot = false;
		town = false;
		mountain = false;
		road = false;
		townName = "";
		waterXSize = 0;
		waterYSize = 0;
		color = new Color[0][0];
	}
	
	public boolean getHotSpot()
	{
		return hotSpot;
	}
	public void setHotSpot(boolean h)
	{
		hotSpot = h;
	}
	
	public boolean getTown()
	{
		return town;
	}
	public void setTown(boolean t)
	{
		town = t;
	}
	public String getTownName()
	{
		return townName;
	}
	public void setTownName(String tn)
	{
		townName = tn;
	}
	
	public boolean getWater()
	{
		return water;
	}
	public void setWater(boolean w)
	{
		water = w;
		if(water == true)
		{
			buildWater();
		}
	}
	public int getWaterYSize()
	{
		return waterYSize;
	}
	public void setWaterYSize(int w)
	{
		waterYSize = w;
	}
	public int getWaterXSize()
	{
		return waterXSize;
	}
	public void setWaterXSize(int w)
	{
		waterXSize = w;
	}
	public void buildWater()
	{
		Random r = new Random();
		
		waterXSize = r.nextInt(20)+60; 
		waterYSize = r.nextInt(20)+60;
		color = new Color[waterXSize][waterYSize];
		
		
		
		
		
		
		
	}
	
	public Color getColor(int x,int y)
	{
		return color[x][y];
	}
	public boolean getMountain()
	{
		return mountain;
	}
	public void setMountain(boolean m)
	{
		mountain = m;
	}
	
	public boolean getRoad()
	{
		return road;
	}
	public void setRoad(boolean r)
	{
		road = r;
	}
	
	
	
	
}