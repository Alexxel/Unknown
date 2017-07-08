package MainGame;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Item
{
	
	int location;
	int attackChange;
	int speedChange;
	int defenseChange;
	double attackPercentChange;
	double speedPercentChange;
	double defensePercentChange;
	double durability;
	boolean broke;
	boolean itemHere;
	String name;
	BufferedImage pictureLocation;
	
	//Default Item Const
	Item()
	{
		pictureLocation = null;
		location = 0;
		attackChange = 0;
		speedChange = 0;
		defenseChange = 0;
		attackPercentChange = 0;
		speedPercentChange = 0;
		defensePercentChange = 0;
		durability = 0;
		broke = false;
		name = "";
		itemHere = false;
		
	}
	
	/*
	 * Locations:
	 * empty = 0
	 * Head = 1
	 * Body = 2
	 * feet = 3
	 * main hand = 4
	 * off hand = 5
	 * quiver = 6
	 * backup = 7
	 */
	Item(int sChange,int aChange,int dChange, double SPC,double APC,double DPC,double dur,int loc,String pictureLoc,String Name)
	{
		try {
			pictureLocation = ImageIO.read(getClass().getResource(pictureLoc));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		location = loc;
		attackChange = aChange;
		speedChange = sChange;
		defenseChange = dChange;
		attackPercentChange = APC;
		speedPercentChange = SPC;
		defensePercentChange = DPC;
		durability = dur;
		broke = false;
		name = Name;
		itemHere = true;
		
	}
	public boolean getItemHere()
	{
		return itemHere;
	}
	public void setDurability(int Durability)
	{
		durability = Durability;
	}
	public double getDurability()
	{
		return durability;
	}
	public int getAttackChange()
	{
		return attackChange;
	}
	public int getDefenseChange()
	{
		return defenseChange;
	}
	public int getSpeedChange()
	{
		return speedChange;
	}
	public double getAttackPercentChange()
	{
		return attackPercentChange;
	}
	public double getDefensePercentChange()
	{
		return defensePercentChange;
	}
	public double getSpeedPercentChange()
	{
		return speedPercentChange;
	}
	public boolean isBroke()
	{
		if(durability <= 0)
		{
			broke = true;
		}
		else
		{
			broke = false;
		}
		return broke;
	}
	public int getLocation()
	{
		return location;
	}
	public BufferedImage getItemPicture()
	{
		return pictureLocation;
	}
	public void setName(String n)
	{
		name = n;
	}
	public String getName()
	{
		return name;
	}
}