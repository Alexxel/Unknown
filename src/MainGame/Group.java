package MainGame;

import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Group
{
	
	List<Unit> Group;
	BufferedImage Image;
	
	Group()
	{
		Group = new ArrayList<Unit>();
	}
	
	Group(int number)
	{
		Group = new ArrayList<Unit>(number);
	}
	
	//Group methods
	public void add(Unit U)
	{
		Group.add(U);
	}
	public void remove(int postion)
	{
		Group.remove(postion);
	}
	
	//Attack Methods
	public double getUnitAttack(int postion)
	{
		return Group.get(postion).getBaseAttack();
	}
	public void setUnitAttack(int postion, int attack)
	{
		Group.get(postion).setBaseAttack(attack);
	}
	public double getUnitTotalAttack(int postion)
	{
		return Group.get(postion).getItemAttackChange();
	}
	//Defense Methods
	public double getUnitDefense(int postion)
	{
		return Group.get(postion).getBaseDefense();
	}
	public void setUnitDefense(int postion, int Defense)
	{
		Group.get(postion).setBaseDefense(Defense);
	}
	public double getUnitTotalDefense(int postion)
	{
		return Group.get(postion).getItemDefenseChange();
	}
	//Level Methods
	public int getUnitLevel(int postion)
	{
		return Group.get(postion).getLevel();
	}
	public void setUnitLevel(int postion, int level)
	{
		Group.get(postion).setLevel(level);
	}
	public void checkUnitLevel(int postion)
	{
		Group.get(postion).checkLevel();
	}
	//SavedLevels Methods
	public int getUnitSavedLevels(int postion)
	{
		return Group.get(postion).getSavedLevels();
	}
	public void setUnitSavedLevels(int postion, int Slevel)
	{
		Group.get(postion).setSavedLevels(Slevel);
	}
	//Experience Methods
	public double getUnitExperience(int postion)
	{
		return Group.get(postion).getExperience();
	}
	public void setUnitExperience(int postion, int experience)
	{
		Group.get(postion).setExperience(experience);
	}
	public int getUnitNeededExperience(int postion)
	{
		return Group.get(postion).needExperience();
	}
	//Health Methods
	public double getUnitHealth(int postion)
	{
		return Group.get(postion).getHealth();
	}
	public void setUnitHealth(int postion, int health)
	{
		Group.get(postion).setHealth(health);
	}
	public double getUnitMaxHealth(int postion)
	{
		return Group.get(postion).getMaxHealth();
	}
	public void setUnitMaxHealth(int postion, int health)
	{
		Group.get(postion).setMaxHealth(health);
	}
	public void changeUnitHealth(int postion, int health)
	{
		Group.get(postion).changeHealth(health);
	}
	public int getTotalGroupHealth()
	{
		double groupHealth = 0;
		for(int i = 0; i <= Group.size();i++)
		{
			groupHealth = Group.get(i).getHealth();
		}
		return (int)groupHealth;
	}
	
	//Moral Methods
	public double getUnitMoral(int postion)
	{
		return Group.get(postion).getMoral();
	}
	public void setUnitMoral(int postion, int moral)
	{
		Group.get(postion).setMoral(moral);
	}
	public void changeUnitMoral(int postion, int moral)
	{
		Group.get(postion).changeMoral(moral);
	}
	public int getTotalGroupMoral()
	{
		int groupMoral = 0;
		for(int i = 1; i < Group.size();i++)
		{
			groupMoral = groupMoral+  Group.get(i).getMoral();
		}
		System.out.println(groupMoral);
		return groupMoral;
		
	}
	//Speed Methods
	public double getUnitSpeed(int postion)
	{
		return Group.get(postion).getSpeed();
	}
	public double getUnitTotalSpeed(int postion)
	{
		return Group.get(postion).getItemSpeedChange();
	}
	public void setUnitSpeed(int postion, int speed)
	{
		Group.get(postion).setSpeed(speed);
	}
	public void changeUnitSpeed(int postion, int speed)
	{
		Group.get(postion).changeSpeed(speed);
	}
	public int getTotalGroupSpeed()
	{
		int groupSpeed = 0;
		for(int i = 0; i <= Group.size();i++)
		{
			groupSpeed = Group.get(i).getMoral();
		}
		return groupSpeed;
		
	}
	public void sortSpeed()
	{
		int currentPostionInit = 0;
		int currentPostion = 0;
		int targetPostionInit = 0;
		int targetPostion = 0;
		int times = 0;
		List<Unit> tempGroup = new ArrayList<Unit>();
		
		for(int i = 0; i <= Group.size();i++)
		{
			for(int x = 0; x <= Group.size() - times;x++)
			{
				currentPostionInit = Group.get(x).getSpeed();
				currentPostion = x;
				if(currentPostionInit > targetPostionInit)
				{
					targetPostionInit = currentPostionInit;
					targetPostion = currentPostion;
				}
				if(x + 1 <= Group.size())
				{
					tempGroup.add(Group.get(targetPostion));
					remove(targetPostion);
				}
			}
			times++;
		}
		
		Group = tempGroup;
	}
	/*public int getItemPicture(int unitLocation,int itemLocation)
	{
		return Group.get(unitLocation).getItemPictureLocation(itemLocation);
	}*/
	public Item getUnitItem(int unitLocation, int ItemLocation)
	{
		return Group.get(unitLocation).getItem(ItemLocation);
	}
	public int getUnitPicture(int unitLocation)
	{
		return Group.get(unitLocation).getUnitPictureLocation();
	}
	public String getItemName(int unitLocation,int itemLocation)
	{
		return Group.get(unitLocation).getItemName(itemLocation);
	}
	public String getUnitName(int postion)
	{
		return Group.get(postion).getUnitName();
	}
	public void setUnitName(int postion,String name)
	{
		Group.get(postion).setUnitName(name);
	}
	public Item addItem(int postion,Item I)
	{
		return Group.get(postion).addItem(I);
	}
	public void removeItem(int postion, int itemPostion)
	{
		Group.get(postion).remove(itemPostion);
	}
	public void setItemName(int unitPostion,int itemPostion,String n)
	{
		Group.get(unitPostion).setItemName(itemPostion, n);
	}
	public int getSize()
	{
		return Group.size();
	}
	
	public void setImage(String filePath)
	{
		
		try{
	    	Image = ImageIO.read(getClass().getResource(filePath));
	    	
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public BufferedImage getImage()
	{
		return Image;
	}
	
}