package MainGame;

import java.util.ArrayList;
import java.util.List;

public class Unit
{
	List<Item> Items;
	int Health;
	int MaxHealth;
	int BaseAttack;
	int BaseDefense;
	int Moral;
	int Speed;
	int PictureLocation;
	int Level;
	int SavedLevels;
	int Experience;
	int costToBuy;
	String Name;
	
	
	
	Unit()
	{
		Health = 0;
		MaxHealth = 0;
		Moral = 0;
		Speed = 0;
		PictureLocation = 0;
		BaseAttack = 0;
		BaseDefense = 0;
		Level = 0;
		SavedLevels = 0;
		Experience = 0;
		costToBuy = 0;
		Items = new ArrayList<Item>(7);
		Name = "";
		for(int i = 0;i < 8;i++)
		{
		Items.add(new Item());
		}
		
	}
	Unit(int health,int maxHealth,int moral,int speed,int attack,int Defense,int level,int pictureLoc,String name)
	{
		Health = health;
		MaxHealth = maxHealth;
		Moral = moral;
		Speed = speed;
		PictureLocation = pictureLoc;
		BaseAttack = attack;
		BaseDefense = Defense;
		Level = level;
		SavedLevels = 0;
		Experience = 0;
		Items = new ArrayList<Item>();
		Name = name;
		costToBuy = 0;
		for(int i = 0;i < 8;i++)
		{
		Items.add(new Item());
		}
	}
	Unit(int cost,int health,int maxHealth,int moral,int speed,int attack,int Defense,int level,int pictureLoc,String name)
	{
		Health = health;
		MaxHealth = maxHealth;
		Moral = moral;
		Speed = speed;
		PictureLocation = pictureLoc;
		BaseAttack = attack;
		BaseDefense = Defense;
		Level = level;
		SavedLevels = 0;
		Experience = 0;
		Items = new ArrayList<Item>();
		Name = name;
		costToBuy = cost;
		for(int i = 0;i < 8;i++)
		{
		Items.add(new Item());
		}
	}
	//Unit CostToBuy Methods
	public int getCostToBuy()
	{
		return costToBuy;
	}
	public void setCostToBuy(int g)
	{
		costToBuy = g;
	}
	//Unit name Methods
	public String getUnitName()
	{
		return Name;
	}
	public void setUnitName(String name)
	{
		Name = name;
	}
	
	//Unit health
	public int getHealth()
	{
		return Health;
	}
	public void setHealth(int health)
	{
		Health = health;
	}
	public void changeHealth(int health)
	{
		Health = Health + health;
	}
	public int getMaxHealth()
	{
		return MaxHealth;
	}
	public void setMaxHealth(int health)
	{
		MaxHealth = health;
	}
	
	//Unit Moral
	public int getMoral()
	{
		return Moral;
	}
	public void setMoral(int moral)
	{
		Moral = moral;
	}
	public void changeMoral(int moral)
	{
		Moral = Moral + moral;
	}
	//Unit Level
	public int getLevel()
	{
		return Level;
	}
	public void setLevel(int level)
	{
		Level = level;
	}
	public void changeLevel(int level)
	{
		Level = Level + level;
	}
	public void checkLevel()
	{
		int needExp = 0;
		needExp = (Level + 1) * 100;
		if(Experience >= needExp)
		{
			Experience = Experience - needExp;
			Level++;
			SavedLevels++;
		}
	}
	//Unit SavedLevel
	public int getSavedLevels()
	{
		return SavedLevels;
	}
	public void setSavedLevels(int Slevel)
	{
		SavedLevels = Slevel;
	}
	public void changeSavedLevel(int Slevel)
	{
		SavedLevels = SavedLevels + Slevel;
	}
	//Unit Experience
	public int getExperience()
	{
		return Experience;
	}
	public void setExperience(int Exp)
	{
		Experience = Exp;
	}
	public void changeExperience(int Exp)
	{
		Experience = Experience + Exp;
	}
	public int needExperience()
	{
		return (Level + 1) * 100;
	}
	
	//Unit Speed
	public int getSpeed()
	{
		return Speed;
	}
	public void setSpeed(int speed)
	{
		Speed = speed;
	}
	public void changeSpeed(int speed)
	{
		Speed = Speed + speed;
	}
	public int getItemSpeedChange()
	{
		int itemSpeed = 0;
		double itemSpeedChange = 0.0;
		double totalItemChange = 0;
		for(int i = 0; i < Items.size(); i++)
		{
			itemSpeed = itemSpeed + Items.get(i).getSpeedChange();
		
		}
		
		for(int i = 0; i < Items.size(); i++)
		{
			itemSpeedChange = itemSpeedChange + Items.get(i).getSpeedPercentChange();
		
		}
		totalItemChange = Speed + itemSpeed +(itemSpeedChange * (Speed + itemSpeed));
		
		
		return (int)totalItemChange;
	}
	
	//Unit Attack
	public int getBaseAttack()
	{
		return BaseAttack;
	}
	public void setBaseAttack(int attack)
	{
		BaseAttack = attack;
	}
	public void changeBaseAttack(int attack)
	{
		BaseAttack = BaseAttack + attack;
	}
	public int getItemAttackChange()
	{
		int itemAttack = 0;
		double itemAttackChange = 0.0;
		double totalItemChange = 0;
		for(int i = 0; i < Items.size(); i++)
		{
			itemAttack = itemAttack + Items.get(i).getAttackChange();
			
		}
		
		for(int i = 0; i < Items.size(); i++)
		{
			itemAttackChange = itemAttackChange + Items.get(i).getAttackPercentChange();
			
		}
		totalItemChange = BaseAttack + itemAttack +(itemAttackChange * (BaseAttack + itemAttack));
		
		return (int)totalItemChange;
	}
	//Unit Defense
		public int getBaseDefense()
		{
			return BaseDefense;
		}
		public void setBaseDefense(int Defense)
		{
			BaseDefense = Defense;
		}
		public void changeBaseDefense(int Defense)
		{
			BaseDefense = BaseDefense + Defense;
		}
		public int getItemDefenseChange()
		{
			int itemDefense = 0;
			double itemDefenseChange = 0;
			double totalItemChange = 0;
			for(int i = 0; i < Items.size(); i++)
			{
				itemDefense = itemDefense + Items.get(i).getDefenseChange();

			}
			
			for(int i = 0; i < Items.size(); i++)
			{
				itemDefenseChange = itemDefenseChange + Items.get(i).getDefensePercentChange();
			
			}
			totalItemChange = BaseDefense + itemDefense +(itemDefenseChange * (BaseDefense + itemDefense));
			
			
			return (int)totalItemChange;
		}
	//Item methods
	public Item addItem(Item I)
	{
	
		Items.set(I.getLocation(),I);
		return null;
	}
	
	public void remove(int postion)
	{
		Items.set(postion, new Item());
	}
	public String getItemName(int postion)
	{
		return Items.get(postion).getName();
	}
	public void setItemName(int postion,String n)
	{
		Items.get(postion).setName(n);
	}
	public Item getItem(int postion)
	{
		return Items.get(postion);
	}
	//Picture Methods
	/*public int getItemPictureLocation(int postion)
	{
		return Items.get(postion).getPictureLocation();
	}*/
	public int getUnitPictureLocation()
	{
		return PictureLocation;
	}
	
	
}